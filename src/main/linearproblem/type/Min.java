package linearproblem.type;

import linearproblem.solver.Solver;

public class Min implements LinearProblemType {

    @Override
    public String getTypeName() {
        return "min";
    }

    @Override
    public void initializeSolverAccordingToLinearProblemType(Solver solver) {
        solver.setLinearProblemAsMinimum();
    }
}
