package kevin.storage;

import kevin.exception.KevinException;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.Scanner;

public class Storage {
    public static final String FILE_FOLDER_NAME = "data";

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

    public void appendFile(String fileLocation, String text) throws KevinException {
        try {
            FileWriter fileWriter = new FileWriter(fileLocation, true);
            fileWriter.write(text);
            fileWriter.close();
        } catch (java.io.IOException err) {
            throw new KevinException("Fail to append text to file: " + err.getMessage());
        }
    }

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
