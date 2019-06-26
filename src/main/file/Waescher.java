package file;

import csp.CuttingStockInstance;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

public class Waescher {

    private final static String TXT_EXTENSION = "txt";
    private File myFile;

    public CuttingStockInstance getData() throws Exception {

        String line;


        this.myFile = FileManager.getFile(TXT_EXTENSION);

        Scanner sc2 = null;
        try {
            sc2 = new Scanner(this.myFile);
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }

        sc2.nextLine();
        line = sc2.nextLine();
        String[] words = line.split(" ");


        CuttingStockInstance cuttingStockInstance = new CuttingStockInstance(Double.valueOf(words[0]));

        while (sc2.hasNextLine()) {
            line = sc2.nextLine();
            words = line.split("\t");

            cuttingStockInstance.addItems(Double.valueOf(words[1]),Double.valueOf(words[0]));
        }

        return cuttingStockInstance;
    }






}
