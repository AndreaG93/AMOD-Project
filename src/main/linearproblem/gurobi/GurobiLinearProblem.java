package linearproblem.gurobi;

import gurobi.*;
import linearproblem.LinearProblem;
import linearproblem.LinearProblemSolution;
import linearproblem.LinearProblemType;
import linearproblem.utility.MathematicalSymbol;
import linearproblem.utility.VariableType;

public class GurobiLinearProblem implements LinearProblem {

    private GRBModel linearProblem;

    public GurobiLinearProblem(){

        try {
            GRBEnv env = new GRBEnv(true);
            env.start();

            this.linearProblem = new GRBModel(env);
            this.linearProblem.set(GRB.IntParam.OutputFlag, 0);

        } catch (GRBException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void setVariables(int totalNumberOfVariables, double lowerBound, double upperBound, VariableType varType) throws Exception {

        for (int i = 0; i < totalNumberOfVariables; i++)
            this.linearProblem.addVar(lowerBound, upperBound, 0.0, varType.gurobiRepresentation, String.format("x_%d", i + 1));

        this.linearProblem.update();
    }

    @Override
    public void setObjectiveFunction(double[] coefficients, LinearProblemType type) throws Exception {

        GRBLinExpr objectiveFunction = new GRBLinExpr();

        objectiveFunction.addTerms(coefficients, this.linearProblem.getVars());
        this.linearProblem.setObjective(objectiveFunction, (type == LinearProblemType.min) ? GRB.MINIMIZE : GRB.MAXIMIZE);

        this.linearProblem.update();
    }

    @Override
    public void setObjectiveFunctionType(LinearProblemType type) throws Exception {
        this.linearProblem.set(GRB.IntAttr.ModelSense, (type == LinearProblemType.min) ? GRB.MINIMIZE : GRB.MAXIMIZE);
    }


    @Override
    public void changeObjectiveFunctionCoefficients(double[] newCoefficients) throws Exception{

        GRBLinExpr objectiveFunction = new GRBLinExpr();

        objectiveFunction.addTerms(newCoefficients, this.linearProblem.getVars());

        this.linearProblem.setObjective(objectiveFunction);
        this.linearProblem.update();
    }

    @Override
    public void addConstraint(double[] coefficients, MathematicalSymbol symbol, double value) throws Exception {

        GRBLinExpr constraint = new GRBLinExpr();

        constraint.addTerms(coefficients, this.linearProblem.getVars());
        this.linearProblem.addConstr(constraint, symbol.getGurobiRepresentation(), value, "");

        this.linearProblem.update();
    }

    @Override
    public void addNewColumn(double newVariableLowerBound, double newVariableUpperBound, double value, VariableType varType, double[] columnCoefficient) throws Exception {

        GRBColumn col = new GRBColumn();
        for (int i = 0; i < columnCoefficient.length; i++)
            col.addTerm(columnCoefficient[i], this.linearProblem.getConstr(i));

        int newVariableIndex = this.linearProblem.getVars().length;

        this.linearProblem.addVar(newVariableLowerBound, newVariableUpperBound, value, varType.gurobiRepresentation, col, String.format("x_%d", newVariableIndex + 1));
        this.linearProblem.update();
    }

    @Override
    public LinearProblemSolution getDualSolution() {

        GRBConstr[] constrains = this.linearProblem.getConstrs();
        double[] output = new double[constrains.length];

        try {
            for (int i = 0; i < constrains.length; i++) {
                output[i] = constrains[i].get(GRB.DoubleAttr.Pi);
            }

        } catch (GRBException e) {
            e.printStackTrace();
        }
        return new LinearProblemSolution(output, 0);
    }

    @Override
    public LinearProblemSolution getSolution() throws Exception {

        try {
            this.linearProblem.optimize();

            if (this.linearProblem.get(GRB.IntAttr.Status) == GRB.INFEASIBLE){
                throw new Exception("The problem is infeasible.");
            }

            GRBVar[] variables = this.linearProblem.getVars();
            double[] solution = new double[variables.length];

            for (int index = 0; index < variables.length; index++)
                solution[index] = variables[index].get(GRB.DoubleAttr.X);

            return new LinearProblemSolution(solution, this.linearProblem.get(GRB.DoubleAttr.ObjVal));
        } catch (GRBException e) {
            e.printStackTrace();
        }
        return null;
    }


    @Override
    public double[] getColumnCoefficient(int index) throws Exception {

        GRBConstr[] constrains = this.linearProblem.getConstrs();
        double[] output = new double[constrains.length];

        for (int i = 0; i < constrains.length; i++)
            output[i] = this.linearProblem.getCoeff(constrains[i], this.linearProblem.getVar(index));

        return output;
    }
}