package duke;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Scanner;

import duke.task.Deadline;
import duke.task.Event;
import duke.task.Task;
import duke.task.ToDo;

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
                Task task = dataToTask(dataLine);
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
                writer.write(taskData(task) + "\n");
            }

            writer.close();
        } catch (IOException e) {
            System.out.println("Error saving data.");
        }
    }

    /**
     * Converts a Task object into a data string for storage.
     *
     * @param task Task to convert.
     * @return Data string representing the Task.
     */
    public static String taskData(Task task) {
        String taskType = task instanceof ToDo ? "T" : task instanceof Deadline ? "D" : "E";
        String status = task.getIsDone() ? "1" : "0";
        String description = task.getDescription();

        StringBuilder data = new StringBuilder();
        data.append(taskType).append(" | ").append(status).append(" | ").append(description);

        // Add additional information for Deadline and Event tasks
        if (task instanceof Deadline) {
            LocalDateTime deadlineDateTime = ((Deadline) task).getBy();
            String formattedDateTime = deadlineDateTime.format(DateTimeFormatter
                    .ofPattern("dd/MM/yyyy HHmm"));
            data.append(" | ").append(formattedDateTime);
        } else if (task instanceof Event) {
            data.append(" | ").append(((Event) task).getFrom()).append(" | ")
                    .append(((Event) task).getTo());
        }

        return data.toString();
    }

    /**
     * Parses a data string and creates a Task from it.
     *
     * @param data Data string to parse.
     * @return A Task object parsed from the data string.
     */
    public static Task dataToTask(String data) {
        String[] info = data.split(" \\| ");
        String taskType = info[0];
        String status = info[1];
        String description = info[2];

        Task task;

        if (taskType.equals("T")) {
            task = new ToDo(description);
        } else if (taskType.equals("D")) {
            task = new Deadline(description, info[3]);
        } else if (taskType.equals("E")) {
            task = new Event(description, info[3], info[4]);
        } else {
            throw new IllegalArgumentException("☹ OOPS!!! Invalid task in data");
        }

        if (status.equals("1")) {
            task.markAsDone();
        }

        return task;
    }

}
