import java.awt.FlowLayout;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JLabel;

class StreamPrinter implements Runnable {

    // Source: http://labs.excilys.com/2012/06/26/runtime-exec-pour-les-nuls-et-processbuilder/
    private final InputStream inputStream;

    private boolean print;

    StreamPrinter(InputStream inputStream, boolean print) {
        this.inputStream = inputStream;
        this.print = print;
    }

    private BufferedReader getBufferedReader(InputStream is) {
        return new BufferedReader(new InputStreamReader(is));
    }

    @Override
    public void run() {
        BufferedReader br = getBufferedReader(inputStream);
        String ligne = "";
        try {
            while ((ligne = br.readLine()) != null) {
                if (print) {
                    System.out.println(ligne);
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}

public class ppp {

    public static void main(String[] args) {

        String TEMP_DIRECTORY = "./_tmp";
        String TEMP_TEX_FILE_NAME = "New22"; // for New22.tex

        // 1. Prepare the .tex file
        String newLineWithSeparation = System.getProperty("line.separator")+System.getProperty("line.separator");
        String math = "";
        math += "\\documentclass[border=0.50001bp,convert={convertexe={convert},outext=.png}]{standalone}" + newLineWithSeparation;
        math += "\\usepackage{amsfonts}" + newLineWithSeparation;
        math += "\\usepackage{amsmath}" + newLineWithSeparation;
        math += "\\begin{document}" + newLineWithSeparation;
        math += "$\\begin{array}{l}" + newLineWithSeparation;
        math += "x^2 + x^4 + \\dfrac{2}{4} + \\oint_4^3 x^2";
        math += "\\end{array}$" + newLineWithSeparation;
        math += "\\end{document}";

        // 2. Create the .tex file
        FileWriter writer = null;
        try {
            writer = new FileWriter(TEMP_DIRECTORY + "\\" + TEMP_TEX_FILE_NAME + ".tex", false);
            writer.write(math, 0, math.length());
            System.out.println("dsadasdas");
            writer.close();
        } catch (IOException ex) {
            ex.printStackTrace();
        }


        // 3. Execute LaTeX from command line  to generate picture
        ProcessBuilder pb = new ProcessBuilder("pdflatex", "-shell-escape", TEMP_TEX_FILE_NAME + ".tex");
        pb.directory(new File(TEMP_DIRECTORY));
        try {
            Process p = pb.start();
            StreamPrinter fluxSortie = new StreamPrinter(p.getInputStream(), false);
            StreamPrinter fluxErreur = new StreamPrinter(p.getErrorStream(), false);
            new Thread(fluxSortie).start();
            new Thread(fluxErreur).start();
            p.waitFor();
        } catch (Exception ex) {
            ex.printStackTrace();
        }


        ProcessBuilder pb2 = new ProcessBuilder("convert", "-density 400 -quality 100", TEMP_TEX_FILE_NAME + ".tex", "output.png");
        pb2.directory(new File(TEMP_DIRECTORY));
        try {
            Process d = pb2.start();
            StreamPrinter fluxSortie = new StreamPrinter(d.getInputStream(), false);
            StreamPrinter fluxErreur = new StreamPrinter(d.getErrorStream(), false);
            new Thread(fluxSortie).start();
            new Thread(fluxErreur).start();
            d.waitFor();
        } catch (Exception ex) {
            ex.printStackTrace();
        }




        // 4. Display picture
        JFrame maFrame = new JFrame();
        maFrame.setResizable(true);
        maFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        maFrame.setSize(400, 400);
        maFrame.getContentPane().setLayout(new FlowLayout());
        maFrame.getContentPane().add(new JLabel(new ImageIcon(TEMP_DIRECTORY + "\\" + TEMP_TEX_FILE_NAME + ".png")));
        maFrame.pack();
        maFrame.setVisible(true);

        // 5. Delete files

        for (File file : (new File(TEMP_DIRECTORY).listFiles())) {
            if (file.getName().startsWith(TEMP_TEX_FILE_NAME + "")) {
                file.delete();
            }
        }


    }
}