package dukeutilities;

import task.Task;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.List;

public class FileHandler {
    private String fileName;
    private String directoryName;

    public FileHandler(String fileName, String directoryName) {
        this.fileName = fileName;
        this.directoryName = directoryName;
    }

    public void createDirectoryIfNotPresent() {
        File directory = new File(directoryName);
        if (!directory.exists()) {
            if (directory.mkdir()) {
            } else {
                System.err.println("Error creating directory");
            }
        }
    }

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
