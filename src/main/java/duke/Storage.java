package duke;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;


/**
 * This class is used to represent the storage of the chat bot
 */
public class Storage {
    public static final String FILESEPERATORCHARACTER = " | ";
    private final String filePath;

    /**
     * Constructor for creating a Storage object
     *
     * @param filePath the path of the file
     */
    private Storage(String filePath) {
        this.filePath = filePath;
    }

    /**
     * Factory method to creates a Storage object
     * @param filePath  the path of the file
     * @return Storage containing the file path and file
     */
    public static Storage createStorage(String filePath) {
        if (!isValidFile(filePath)) {
            System.out.println("File not detected, creating file");
            createDirectory("./data");
            File file = createFile(filePath);
            return new Storage(filePath);
        }
        System.out.println("File detected, loading file");
        return new Storage(filePath);
    }

    /**
     * Checks if a file exists and is not a directory
     * @param filePath  the path of the file
     * @return  true if the file exists and is not a directory, false otherwise
     */
    public static boolean isValidFile(String filePath) {
        File file = new File(filePath);
        return file.exists() && !file.isDirectory();
    }

    /**
     * Creates a directory if it does not exist
     * @param directoryPath the path of the directory
     */
    public static void createDirectory(String directoryPath) {
        File directory = new File(directoryPath);
        if (directory.exists() && directory.isDirectory()) {
            System.out.println("Directory already exists");
            return;
        } else if (directory.mkdir()) {
            System.out.println("Directory has been created");
            return;
        }
        System.out.println("Directory could not be created");
    }

    /**
     * Creates a file if it does not exist
     * @param filePath
     * @return File object
     * @throws IOException if the file could not be created
     */
    public static File createFile(String filePath) {
        File file = new File(filePath);
        try {
            file.createNewFile();
            System.out.println("File created sucessfully");
        } catch (IOException e) {
            System.out.println("File could not be created");
        }
        return file;
    }

    /**
     * Writes to the file in the Storage object
     * @param textToAdd the text to add to the file
     * @return true if the write was successful, false otherwise
     */
    public boolean writeToFile(String textToAdd) {
        try {
            FileWriter fileWriter = new FileWriter(filePath, true);
            fileWriter.write(textToAdd + System.lineSeparator());
            fileWriter.flush();
            return true;
        } catch (IOException e) {
            System.out.println("An error occurred in writing the file.");
            return false;
        }
    }

    /**
     * Clears the file in the Storage object
     * @return  true if the clear was successful, false otherwise
     */
    public boolean clearFile() {
        try {
            FileWriter fileWriter = new FileWriter(filePath);
            fileWriter.write("");
            fileWriter.close();
            return true;
        } catch (IOException e) {
            System.out.println("An error occurred in cleaning the file.");
            return false;
        }
    }





}
