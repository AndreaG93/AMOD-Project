package linearproblem.math;

import gurobi.GRB;
import lpsolve.LpSolve;

public enum MathematicalSymbol {

    EQ, GEQ, LEQ;

    public char toGurobiRepresentation;
    private String latexMarkupString;
    private int lpSolveIndex;

    static {
        EQ.latexMarkupString = " $=$ ";
        LEQ.latexMarkupString = " $\\leq$ ";
        GEQ.latexMarkupString = " $\\geq$ ";

        EQ.lpSolveIndex = LpSolve.EQ;
        LEQ.lpSolveIndex = LpSolve.LE;
        GEQ.lpSolveIndex = LpSolve.GE;

        EQ.toGurobiRepresentation = GRB.LESS_EQUAL;


    }

    public String getLatexMarkupString() {
        return this.latexMarkupString;
    }

    public int getLpSolveIndex(){
        return this.lpSolveIndex;
    }

    public char getGurobiRepresentation(){
        return this.toGurobiRepresentation;
    }
}
