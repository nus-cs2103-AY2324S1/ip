import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Scanner;
import java.util.logging.Level;
import java.util.logging.Logger;

public class Storage {
    private String filePath;

    public Storage(String filePath) {
        this.filePath = filePath;
    }

    public ArrayList<Task> loadTasks() {
        // Load tasks from the file and return them
        ArrayList<Task> tasks = new ArrayList<>();
        File file = new File(filePath);
        File folder = file.getParentFile();

        // Create the parent folder if it doesn't exist
        if (!folder.exists() && !folder.mkdirs()) {
            System.err.println("Unable to create directory: " + folder.getAbsolutePath());
            return tasks; // Return an empty list
        }

        try {
            if (!file.exists() && !file.createNewFile()) {
                System.err.println("Unable to create file: " + filePath);
                return tasks; // Return an empty list
            }

            Scanner scanner = new Scanner(file);
            while (scanner.hasNextLine()) {
                String formattedTask = scanner.nextLine();

                if (formattedTask.isEmpty()) {
                    continue; // Skip empty lines
                }

                try {
                    Task task = Task.fromFileString(formattedTask);
                    tasks.add(task);
                } catch (NumberFormatException e) {
                    // Handle corrupted data - logging the issue
                    Logger logger = Logger.getLogger(EchoBot.class.getName());
                    logger.log(Level.SEVERE, "Corrupted data: " + formattedTask, e);
                } catch (IllegalArgumentException e) {
                    System.out.println("Invalid data: " + formattedTask);
                }
            }
            scanner.close();
        } catch (IOException e) {
            System.err.println("An error occurred while handling file operations: " + e.getMessage());
        } catch (Exception e) {
            Logger logger = Logger.getLogger(EchoBot.class.getName());
            logger.log(Level.SEVERE, "An error occurred while loading tasks", e);
        }

        return tasks;
    }

    public void saveTasks(ArrayList<Task> tasks) {
        // Save tasks to the file
        try (PrintWriter writer = new PrintWriter(filePath)) {
            for (Task task : tasks) {
                writer.println(task.toFileString());
            }
        } catch (FileNotFoundException e) {
            System.out.println("Error saving tasks: " + e.getMessage());
        }
    }

}
