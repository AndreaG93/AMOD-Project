package test.gurobi;

import gurobi.GRB;
import gurobi.GRBLinExpr;
import gurobi.GRBModel;
import gurobi.GRBVar;
import linearproblem.math.MathematicalSymbol;
import test.TestLinearProblem;

import java.util.ArrayList;
import java.util.Arrays;

public class GurobiLinearProblem extends TestLinearProblem {

    private GRBModel linearProblem;

    private GRBVar[] gurobiVariables;


    @Override
    public void setNumberOfVariables(int value) throws Exception {

        this.gurobiVariables = new GRBVar[value];

        for (int i = 0; i < value; i++) {
            GRBVar gurobiVariable = this.linearProblem.addVar(0.0, 1.0, 0.0, GRB.BINARY, String.format("x_%d", i + 1));
            this.gurobiVariables[i] = gurobiVariable;
        }
    }

    //myArray = Arrays.copyOf(myArray, myNewSize);

    @Override
    public void addObjectiveFunction(double[] coefficients, TestLinearProblemType type) throws Exception {

        GRBLinExpr objectiveFunction = new GRBLinExpr();

        objectiveFunction.addTerms(coefficients, this.gurobiVariables);
        this.linearProblem.setObjective(objectiveFunction, (type == TestLinearProblemType.min) ? GRB.MINIMIZE : GRB.MAXIMIZE);
    }

    @Override
    public void addConstraint(double[] coefficients, MathematicalSymbol symbol, double value) throws Exception {

        GRBLinExpr constraint = new GRBLinExpr();

        constraint.addTerms(coefficients, gurobiVariables);
        this.linearProblem.addConstr(constraint, symbol.getGurobiRepresentation(), value, "");
    }
}