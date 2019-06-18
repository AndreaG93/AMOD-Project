import lpsolve.*;

import gurobi.*;

public class gurobi {

    public static void main(String[] args) {
        try {
            // Create a problem with 4 variables and 0 constraints
            LpSolve solver = LpSolve.makeLp(5, 5);

            // add constraints
            solver.strAddConstraint("5 0 0 0 0", LpSolve.EQ & LpSolve.GE, 48);
            solver.strAddConstraint("0 2 0 0 0", LpSolve.EQ & LpSolve.GE, 35);
            solver.strAddConstraint("0 0 2 0 0", LpSolve.EQ & LpSolve.GE, 24);
            solver.strAddConstraint("0 0 0 2 0", LpSolve.EQ & LpSolve.GE, 10);
            solver.addConstraint(new double[]{0.0d,0.0d,0.0d,0.0d,1.0d}, LpSolve.EQ & LpSolve.GE, 8d);

            // set objective function
            solver.strSetObjFn("1 1 1 1 1");

            double[] ff = solver.getPtrConstraints();




            //linearproblem.solver.setBinary(1, true);
            //linearproblem.solver.setBinary(2, true);
            //linearproblem.solver.setBinary(3, true);


            solver.setMinim();

            // LinearProblemSolver the problem
            solver.solve();



            // print solution
            System.out.println("Value of objective function: " + solver.getObjective());
            double[] var = solver.getPtrVariables();
            for (int i = 0; i < var.length; i++) {
                System.out.println("Value of var[" + i + "] = " + var[i]);
            }

            // delete the problem and free memory
            solver.deleteLp();
        }
        catch (LpSolveException e) {
            e.printStackTrace();
        }
    }

}