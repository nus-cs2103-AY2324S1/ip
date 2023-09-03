package rat.storage;

import static rat.io.RatPrinter.printWithLines;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Scanner;

/**
 * This class encapsulates the logic of handling the storage of Rat,
 * which includes reading and writing to a file.
 * @author Keagan
 */
public class RatStorage {

    /**
     * The file path of the file to be read from and written to.
     */
    private static File ratData;

    /**
     * Constructor for RatStorage.
     * @param filePath The file path of the file to be read from and written to.
     */
    public RatStorage(String filePath) {
        try {
            ratData = new File(filePath);
            if (!ratData.exists()) {
                ratData.getParentFile().mkdirs();
                ratData.createNewFile();
            }
        } catch (IOException e) {
            printWithLines("Error initialising RatStorage: " + e.getMessage());
        }
    }

    /**
     * Checks if the file is empty.
     * @return True if the file is empty, false otherwise.
     */
    public boolean isFileEmpty() {
        return ratData.length() == 0;
    }

    /**
     * Overwrites the file with the given input.
     * @param input The input to overwrite the file with.
     */
    public void overwriteFile(String input) {
        try {
            FileWriter fw = new FileWriter(ratData);
            fw.write(input);
            fw.close();
        } catch (IOException e) {
            printWithLines("Error overwriting file: " + e.getMessage());
        }
    }

    /**
     * Reads the file and returns its contents as a String.
     * @return The contents of the file as a String.
     */
    public String readFile() {
        try {
            Scanner sc = new Scanner(ratData);
            StringBuilder str = new StringBuilder();
            while (sc.hasNext()) {
                str.append(sc.nextLine()).append("\n");
            }
            sc.close();
            return str.toString();
        } catch (IOException e) {
            printWithLines("Error reading file: " + e.getMessage());
            return "";
        }
    }

}
