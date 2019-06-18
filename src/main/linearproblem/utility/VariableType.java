package linearproblem.utility;

import gurobi.GRB;

public enum VariableType {

    BINARY, INTEGER, REAL;

    public char gurobiRepresentation;

    static {
        REAL.gurobiRepresentation = GRB.CONTINUOUS;
        BINARY.gurobiRepresentation = GRB.BINARY;
        INTEGER.gurobiRepresentation = GRB.INTEGER;
    }

    public char getGurobiRepresentation() {
        return gurobiRepresentation;
    }
}
