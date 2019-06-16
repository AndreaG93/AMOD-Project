package linearproblem.type;

import linearproblem.solver.Solver;

public class Max implements LinearProblemType {

    @Override
    public String getTypeName() {
        return "max";
    }

    @Override
    public void initializeSolverAccordingToLinearProblemType(Solver solver) {
        solver.setLinearProblemAsMaximum();
    }
}
