package bee;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Scanner;

/**
 * Handles the storage of tasks in a file and loading tasks from the file.
 */
public class Storage {

    private TaskList tasks = new TaskList();
    private String filePath = "";

    /**
     * Constructs a Storage object with the specified file path.
     *
     * @param filePath The path of the storage file.
     * @throws BeeException If there is an issue with file initialization.
     */
    public Storage(String filePath) throws BeeException {
        this.filePath = filePath;
    }

    // Parse string information from file into task
    private void parseTask(String taskData) throws BeeException {
        String[] taskDataSplit = taskData.split("]");
        String taskType = taskDataSplit[0].substring(1);
        Boolean isDone = taskDataSplit[1].substring(1).equals("X");
        String taskDescription = taskDataSplit[2].substring(1);

        switch (taskType) {
            case "T":
                Todo todo = new Todo(taskDescription, isDone);
                tasks.quietlyAddTask(todo);
                break;
            case "D":
                try {
                    String[] splitEditedInput = taskDescription.split("by: ");
                    String deadlineDescription = splitEditedInput[0];
                    deadlineDescription = deadlineDescription.substring(0, deadlineDescription.indexOf("(") - 1);
                    String deadlineDateString = splitEditedInput[1];
                    deadlineDateString = deadlineDateString.substring(0, deadlineDateString.indexOf(")"));

                    DateTimeFormatter storageFormatter = DateTimeFormatter.ofPattern("MMM dd yyyy HH:mm");
                    LocalDateTime deadlineDate = LocalDateTime.parse(deadlineDateString, storageFormatter);

                    Deadline deadlineTask = new Deadline(deadlineDescription, deadlineDate, isDone);
                    tasks.quietlyAddTask(deadlineTask);
                } catch (StringIndexOutOfBoundsException e) {
                    throw new BeeException("OOPS!! The description of a deadline cannot be empty.");
                } catch (ArrayIndexOutOfBoundsException e) {
                    throw new BeeException("OOPS!! The date of the deadline cannot be empty.");
                }
                break;
            case "E":
                try {
                    String[] splitEditedInput = taskDescription.split("from: ");
                    String[] splitEditedInput2 = splitEditedInput[1].split(" to: ");
                    String eventDescription = splitEditedInput[0];
                    eventDescription = eventDescription.substring(0, eventDescription.indexOf("(") - 1);
                    String eventStartDate = splitEditedInput2[0];
                    String eventEndDate = splitEditedInput2[1];
                    eventEndDate = eventEndDate.substring(0, eventEndDate.indexOf(")"));
                    Event event = new Event(eventDescription, eventStartDate, eventEndDate, isDone);
                    tasks.quietlyAddTask(event);
                } catch (StringIndexOutOfBoundsException e) {
                    throw new BeeException("OOPS!! The description of an event cannot be empty.");
                } catch (ArrayIndexOutOfBoundsException e) {
                    throw new BeeException("OOPS!! The date of an event cannot be empty.");
                }
                break;
        }
    }

    /**
     * Saves the tasks to the storage file.
     */
    public void saveTasksToFile() {
        try {
            File file = new File(this.filePath);
            FileWriter writer = new FileWriter(file);

            for (Task task : tasks.getTasks()) {
                writer.write(task.toString() + System.lineSeparator());
            }
            writer.close();
        } catch (IOException e) {
            System.out.println("Error saving tasks to file: " + e.getMessage());
        }
    }

    /**
     * Loads tasks from the storage file.
     *
     * @return The list of loaded tasks.
     * @throws BeeException If there is an issue loading tasks from the file.
     */
    public ArrayList<Task> loadTasksFromFile() throws BeeException {
        try {
            File file = new File(filePath);
            if (!file.exists()) {
                // If file doesn't exist, create an empty one
                file.createNewFile();
                return this.tasks.getTasks();
            }

            Scanner fileScanner = new Scanner(file);
            while (fileScanner.hasNextLine()) {
                String taskData = fileScanner.nextLine();
                parseTask(taskData);
                // Parse taskData and add tasks to the list
                // Example: T | 1 | read book
            }
            fileScanner.close();
            return this.tasks.getTasks();
        } catch (IOException e) {
            System.out.println("Error loading tasks from file: " + e.getMessage());
        }
        return null;
    }
}
