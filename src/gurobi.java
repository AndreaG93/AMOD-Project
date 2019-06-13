import lpsolve.*;

public class gurobi {

    public static void main(String[] args) {
        try {
            // Create a problem with 4 variables and 0 constraints
            LpSolve solver = LpSolve.makeLp(0, 3);

            // add constraints
            solver.strAddConstraint("81 70 68", LpSolve.EQ & LpSolve.LE, 218);

            // set objective function
            solver.strSetObjFn("1 0.33 0.33");

            solver.setInt(1, true);
            solver.setInt(2, true);
            solver.setInt(3, true);

            //solver.setBinary(1, true);
            //solver.setBinary(2, true);
            //solver.setBinary(3, true);


            solver.setMaxim();

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