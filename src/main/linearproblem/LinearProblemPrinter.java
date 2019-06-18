package linearproblem;

public class LinearProblemPrinter {


    public static String getLPRepresentationAsLatexString(LinearProblem linearProblem) {
        /*
        int numberOfVariables = this.c.size();
        int numberOfConstraints = this.A.height();


        StringBuilder latexOutput = new StringBuilder();

        latexOutput.append("\\documentclass[border=0.50001bp,convert={convertexe={convert},outext=.png}]{standalone}");
        latexOutput.append("\\usepackage{amsfonts}\n\\usepackage{amsmath}\n");
        latexOutput.append("\\begin{document}\n\\begin{tabular}{");
        latexOutput.append("c".repeat(this.c.size() + 3)).append("}\n");
        latexOutput.append("min &");


        // Objective -----------------------

        for (int i = 0; i < numberOfVariables - 1; i++)

            latexOutput.append(String.format(Locale.US, " $%.2fx_{%d}$ &", this.c.getValue(i), i + 1));

        latexOutput.append(String.format(Locale.US, "$%.2fx_{%d}$ & & \\\\\n", this.c.getValue(numberOfVariables - 1), numberOfVariables));


        // Matrix coefficient -----------------------

        for (int rowIndex = 0; rowIndex < numberOfConstraints; rowIndex++) {


            latexOutput.append("&");


            for (int columnIndex = 0; columnIndex < numberOfVariables - 1; columnIndex++) {


                double value = this.A.getValue(rowIndex, columnIndex);


                if (value != 0)

                    latexOutput.append(String.format(Locale.US, " $%.2fx_{%d}$ &", value, columnIndex + 1));

                else

                    latexOutput.append(" & ");

            }


            double value = this.A.getValue(rowIndex, numberOfVariables - 1);


            if (value != 0)

                latexOutput.append(String.format(Locale.US, " $%.2fx_{%d}$ &", value, numberOfVariables));

            else

                latexOutput.append(" & ");


            latexOutput.append(String.format(Locale.US, " %s & $%.2f$ \\\\\n", this.constraintInequalitySymbols.get(rowIndex).getLatexMarkupString(), this.b.getValue(rowIndex)));

        }


        latexOutput.append("\\end{tabular}\n\\end{document}\n");


        System.out.println(latexOutput.toString());

    }
*/
        return null;
    }

}
