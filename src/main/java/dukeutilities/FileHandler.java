package dukeutilities;

import task.Task;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.List;

/**
 * The FileHandler class manages file operations such as creating directories,
 * files, and writing task lists to files.
 */
public class FileHandler {
    private String fileName;
    private String directoryName;

    /**
     * Constructs a FileHandler object with the specified file and directory names.
     *
     * @param fileName     The name of the file to be handled.
     * @param directoryName The name of the directory where the file resides.
     */
    public FileHandler(String fileName, String directoryName) {
        this.fileName = fileName;
        this.directoryName = directoryName;
    }

    /**
     * Creates the specified directory if it doesn't exist.
     */
    public void createDirectoryIfNotPresent() {
        File directory = new File(directoryName);
        if (!directory.exists()) {
            if (directory.mkdir()) {
            } else {
                System.err.println("Error creating directory");
            }
        }
    }

    /**
     * Creates the specified file if it doesn't exist.
     */
    public void createFileIfNotPresent() {
        File file = new File(directoryName, fileName);
        if (!file.exists()) {
            try {
                file.createNewFile();
            } catch (IOException e ) {
                System.out.println("Error generating task list");
            }
        }
    }

    /**
     * Writes the list of tasks to the file, each task on a separate line.
     *
     * @param taskList The list of tasks to be written to the file.
     * @throws IOException If unable to write successfully.
     */
    public void writeListToFile(List<Task> tasklist) {
        try (FileWriter writer = new FileWriter(fileName)) {
            for (Task t : tasklist) {
                writer.write(t.toFileString());
                writer.write(System.lineSeparator()); // Add a new line after each string
            }
        } catch (IOException e) {
            System.err.println("Error writing task list to file: " + e.getMessage());
        }
    }
}
