package linearproblem.type;

import linearproblem.solver.Solver;

public interface LinearProblemType {

    String getTypeName();

    void initializeSolverAccordingToLinearProblemType(Solver solver);
}