import lpsolve.*;

public class gurobi {

    public static void main(String[] args) {
        try {
            // Create a problem with 4 variables and 0 constraints
            LpSolve solver = LpSolve.makeLp(0, 5);

            // add constraints
            solver.strAddConstraint("5 0 0 0 0", LpSolve.EQ & LpSolve.GE, 48);
            solver.strAddConstraint("0 2 0 0 0", LpSolve.EQ & LpSolve.GE, 35);
            solver.strAddConstraint("0 0 2 0 0", LpSolve.EQ & LpSolve.GE, 24);
            solver.strAddConstraint("0 0 0 2 0", LpSolve.EQ & LpSolve.GE, 10);
            solver.strAddConstraint("0 0 0 0 1", LpSolve.EQ & LpSolve.GE, 8);


            // set objective function
            solver.strSetObjFn("1 1 1 1 1");

            solver.setInt(1, false);
            solver.setInt(2, false);
            solver.setInt(3, false);
            solver.setInt(4, false);
            solver.setInt(5, false);


            //solver.setBinary(1, true);
            //solver.setBinary(2, true);
            //solver.setBinary(3, true);


            solver.setMinim();

            // solve the problem
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