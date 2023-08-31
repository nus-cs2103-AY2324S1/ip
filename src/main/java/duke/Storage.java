package duke;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Scanner;

public class Storage {
    private final String filepath;

    public Storage(String filepath) {
        this.filepath = filepath;
    }

    /**
     * Loads tasks from a file and populates the provided list with the loaded tasks.
     * This method reads tasks from a file specified by the DATA_FILE_PATH and adds them to
     * the provided list of tasks. If the file does not exist, it will be created.
     *
     * @param list The list to which loaded tasks will be added.
     */
    public void loadTasks(TaskList list) {
        try {
            // Create a File object representing the data file
            File file = new File(filepath);

            // Create the file if it doesn't exist and print a message
            if (file.createNewFile()) {
                System.out.println("        New File Created");
                Ui.showLine();
            }

            // Use a Scanner to read tasks from the file
            try (Scanner sc = new Scanner(file)) {
                while (sc.hasNextLine()) {
                    // Read each line from the file
                    String line = sc.nextLine();
                    // Convert the line to a Task object using Task.fromString
                    Task task = Parser.parseFileInput(line);
                    // Add the task to the provided list if it's not null
                    if (task != null) {
                        list.addTask(task);
                    }
                }
            }

        } catch (IOException e) {
            // Handle IO exception by printing an error message
            Ui.showError("An error occurred while loading tasks");
        }
    }

    /**
     * Saves tasks from a list to a file.
     * This method writes tasks from the provided list to a file specified by the DATA_FILE_PATH.
     * The tasks are converted to their file representation using the toFileString method of
     * the Task class. If an error occurs during the file writing process, an error message
     * is printed.
     *
     * @param list The list of tasks to be saved to the file.
     */
    public void saveTasks(TaskList list) {
        try {
            // Create a File object representing the data file
            File file = new File(filepath);

            // Create a FileWriter to write tasks to the file
            FileWriter writer = new FileWriter(file);

            // Write each task's file representation to the file
            for (int i = 0; i < list.getSize(); i++) {
                writer.write(list.getTask(i).toFileString() + "\n");
            }

            // Close the FileWriter
            writer.close();

        } catch (IOException e) {
            // Handle IO exception by printing an error message
            Ui.showError("An error occurred while saving tasks");
        }
    }
}
