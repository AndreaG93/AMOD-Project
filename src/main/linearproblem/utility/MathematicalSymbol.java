package linearproblem.utility;

import gurobi.GRB;

public enum MathematicalSymbol {

    EQUAL, GREATER_EQUAL, LESS_EQUAL;

    public char gurobiRepresentation;
    private String latexRepresentation;

    static {
        EQUAL.latexRepresentation = " $=$ ";
        LESS_EQUAL.latexRepresentation = " $\\leq$ ";
        GREATER_EQUAL.latexRepresentation = " $\\geq$ ";

        EQUAL.gurobiRepresentation = GRB.EQUAL;
        LESS_EQUAL.gurobiRepresentation = GRB.LESS_EQUAL;
        GREATER_EQUAL.gurobiRepresentation = GRB.GREATER_EQUAL;
    }

    public char getGurobiRepresentation() {
        return gurobiRepresentation;
    }

    public String getLatexRepresentation() {
        return latexRepresentation;
    }
}