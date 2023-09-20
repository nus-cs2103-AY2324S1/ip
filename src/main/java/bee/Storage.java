package bee;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Scanner;

/**
 * Handles the storage of tasks and interaction with data files.
 * Provides methods for saving tasks to a file and loading tasks from a file.
 */
public class Storage {

    private TaskList tasks = new TaskList();
    private String filePath = "";

    /**
     * Constructs a Storage instance with the specified file path.
     *
     * @param filePath The path to the data file for storing tasks.
     */
    public Storage(String filePath) {
        assert filePath != null : "File path cannot be null."; // Assumption: File path should not be null
        this.filePath = filePath;
        try {
            File file = new File(filePath);
            if (!file.exists()) {
                file.getParentFile().mkdirs();
                file.createNewFile();
            }
        } catch (IOException e) {
            System.out.println("Bzzz! An error occured when creating the file.");
        }
    }

    /**
     * Parses task data from a string and creates corresponding task objects.
     *
     * @param taskData The task data string to parse.
     * @throws BeeException If there's an issue with parsing the task data.
     */
    void parseTask(String taskData) throws BeeException {
        assert taskData != null : "Task data string cannot be null."; // Assumption: Task data should not be null

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
        default:
        }
    }

    /**
     * Saves tasks to the data file.
     */
    public void saveTasksToFile() {
        try {
            File file = new File(this.filePath);
            assert file != null : "File object cannot be null."; // Assumption: File should not be null
            FileWriter writer = new FileWriter(file);

            for (Task task : tasks.getTasks()) {
                assert task != null : "Task object cannot be null."; // Assumption: Task should not be null
                writer.write(task.toString() + System.lineSeparator());
            }
            writer.close();
        } catch (IOException e) {
            System.out.println("Error saving tasks to file: " + e.getMessage());
        }
    }

    /**
     * Loads tasks from the data file.
     *
     * @return The list of tasks loaded from the file.
     * @throws BeeException If there's an issue with loading tasks from the file.
     */
    public ArrayList<Task> loadTasksFromFile() throws BeeException {
        try {
            File file = new File(filePath);
            assert file != null : "File object cannot be null."; // Assumption: File should not be null
            if (!file.exists()) {
                // If file doesn't exist, create an empty one
                boolean created = file.createNewFile();
                assert created : "Failed to create a new file."; // Assumption: File creation should succeed
                return this.tasks.getTasks();
            }

            Scanner fileScanner = new Scanner(file);
            while (fileScanner.hasNextLine()) {
                String taskData = fileScanner.nextLine();
                assert taskData != null : "Task data string cannot be null.";
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
