package linearproblem.math;

import lpsolve.LpSolve;

public enum MathematicalSymbol {

    EQ, GEQ, LEQ;

    private String latexMarkupString;
    private int lpSolveIndex;

    static {
        EQ.latexMarkupString = " $=$ ";
        LEQ.latexMarkupString = " $\\leq$ ";
        GEQ.latexMarkupString = " $\\geq$ ";

        EQ.lpSolveIndex = LpSolve.EQ;
        LEQ.lpSolveIndex = LpSolve.LE;
        GEQ.lpSolveIndex = LpSolve.GE;
    }

    public String getLatexMarkupString() {
        return this.latexMarkupString;
    }

    public int getLpSolveIndex(){
        return this.lpSolveIndex;
    }
}
