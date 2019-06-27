package file;

import csp.CuttingStockInstance;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

public class WaescherFile {

    private final static String TXT_EXTENSION = "txt";

    public static CuttingStockInstance parsingCSPInstance() throws Exception {

        CuttingStockInstance cuttingStockInstance = null;
        Scanner scanner;
        File file;
        String[] token;

        try {

            file = FileManager.getFile(TXT_EXTENSION);
            scanner = new Scanner(file);

            // Ignore first line!!
            scanner.nextLine();

            // Read max length
            token = scanner.nextLine().split(" ");
            cuttingStockInstance = new CuttingStockInstance(Double.valueOf(token[0]));

            // Get items...
            while (scanner.hasNextLine()) {
                token = scanner.nextLine().split("\t");

                cuttingStockInstance.addItems(Double.valueOf(token[1]), Double.valueOf(token[0]));
            }

        } catch (FileNotFoundException e) {
            e.printStackTrace();
            System.exit(1);
        }

        return cuttingStockInstance;
    }
}