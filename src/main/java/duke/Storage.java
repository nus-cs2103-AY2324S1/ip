package duke;

import duke.task.Task;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Scanner;

/**
 * This class deals with loading tasks from the file and saving tasks in the file.
 */
public class Storage {
    private String filePath;

    /**
     * Constructor with the specified file path.
     *
     * @param filePath The path to the data file.
     */
    public Storage(String filePath) {
        this.filePath = filePath;
    }

    /**
     * Creates a data file and its parent directory if they do not exist.
     */
    public void createFile() {
        try {
            // Create if it does not exist
            Files.createDirectories(Paths.get("./data/"));
            File dataFile = new File("./data/duke.txt");
            if (!dataFile.exists()) {
                dataFile.createNewFile();
            }
        } catch (IOException e) {
            System.out.println("Error creating data file.");
        }
    }

    /**
     * Retrieves the existing task list from the data file.
     *
     * @return An ArrayList of Task objects read from the data file.
     */
    public static ArrayList<Task> retrieveData() {
        ArrayList<Task> taskList = new ArrayList<>();

        try {
            File file = new File("./data/duke.txt");
            Scanner scanner = new Scanner(file);

            while (scanner.hasNextLine()) {
                String dataLine = scanner.nextLine();
                Task task = Parser.dataToTask(dataLine);
                taskList.add(task);
            }

            scanner.close();
        } catch (FileNotFoundException e) {
            System.out.println("Creating a new file.");
        }

        return taskList;
    }

    /**
     * Saves the task list to the data file.
     *
     * @param taskList The ArrayList of Task objects to be saved.
     */
    public static void save(ArrayList<Task> taskList) {
        try {
            FileWriter writer = new FileWriter("./data/duke.txt");

            for (Task task : taskList) {
                // Write each task with specified format
                writer.write(Parser.taskData(task) + "\n");
            }

            writer.close();
        } catch (IOException e) {
            System.out.println("Error saving data.");
        }
    }


}
