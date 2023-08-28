package duke;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Scanner;

public class FileManager {

    /**
     * Reads a file given the file path
     * @param filePath the path directory to the file
     * @throws FileNotFoundException
     */
    public void readFile(String filePath) throws FileNotFoundException {
        File file = new File(filePath);
        Scanner scanner = new Scanner(file);
        while (scanner.hasNextLine()) {
            String data = scanner.nextLine();
            System.out.println(data);
        }
        scanner.close();
    }

    /**
     * Writes to a file when given the file path
     * @param filePath the path directory to the file
     * @param textToAdd the text to add to the file
     * @return
     * @throws IOException
     */
    public boolean writeToFile(String filePath, String textToAdd) throws IOException {
        try {
            FileWriter fileWriter = new FileWriter(filePath);
            fileWriter.write(textToAdd);
            fileWriter.close();
            return true;
        } catch (IOException e) {
            System.out.println("An error occurred in writing the file.");
            return false;
        }
    }
}
