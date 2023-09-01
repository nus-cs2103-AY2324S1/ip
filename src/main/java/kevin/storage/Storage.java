package kevin.storage;

import kevin.exception.KevinException;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.Scanner;

/**
 * A class that is responsible for file input output.
 */
public class Storage {
    public static final String FILE_FOLDER_NAME = "data";

    /**
     * Creates a new file to local computer if it does not exit.
     * @param fileLocation This is the location of the file.
     * @throws KevinException On the detection of errors.
     */
    public void createFile(String fileLocation) throws KevinException {
        try {
            File fileFolder = new File(FILE_FOLDER_NAME);
            if (!fileFolder.isDirectory()) {
                fileFolder.mkdir();
            }

            File dataFile = new File(fileLocation);
            if (!dataFile.exists()) {
                dataFile.createNewFile();
            }
        } catch (Exception err) {
            throw new KevinException("Fail to make new file: " + err.getMessage());
        }
    }


    /**
     * Appends text to the local file.
     * @param fileLocation This is the location of the file.
     * @param text This is the text that will be appended to the file.
     * @throws KevinException On the detection of errors.
     */
    public void appendFile(String fileLocation, String text) throws KevinException {
        try {
            FileWriter fileWriter = new FileWriter(fileLocation, true);
            fileWriter.write(text);
            fileWriter.close();
        } catch (java.io.IOException err) {
            throw new KevinException("Fail to append text to file: " + err.getMessage());
        }
    }

    /**
     * Overwrites a single line with text based on line number.
     * @param fileLocation This is the location of the file.
     * @param text This is the text that will overwrite the old line.
     * @param index The line number to be overwritten.
     * @throws KevinException On the detection of errors.
     */
    public void overwriteLine(String fileLocation, String text, int index) throws KevinException {
        try {
            Scanner sc = new Scanner(new File(fileLocation));
            StringBuffer buffer = new StringBuffer();
            String toBeReplaced = "";
            for (int i = 0; i < index; i++) {
                toBeReplaced = sc.nextLine() + System.lineSeparator();
                buffer.append(toBeReplaced);
            }
            while (sc.hasNextLine()) {
                buffer.append(sc.nextLine() + System.lineSeparator());
            }
            String fileContents = buffer.toString();
            sc.close();
            fileContents = fileContents.replaceAll(toBeReplaced, text);
            FileWriter writer = new FileWriter(fileLocation);
            writer.append(fileContents);
            writer.flush();
        } catch (IOException e) {
            throw new KevinException(e.getMessage());
        }
    }

    /**
     * Reads from the local file.
     * @param fileLocation This is the location of the file.
     * @return Returns a String of the file content.
     * @throws KevinException On the detection of errors.
     */
    public String readFile(String fileLocation) throws  KevinException {
        try {
            String data = "";
            data = new String(Files.readAllBytes(Paths.get(fileLocation)));
            return data;
        } catch (Exception err) {
            throw new KevinException("Fail to read text to file: " + err.getMessage());
        }
    }
}
