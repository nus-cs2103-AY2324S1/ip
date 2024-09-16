package duke;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;

import duke.task.Deadline;
import duke.task.Event;
import duke.task.Todo;

/**
 * Handles saving and loading tasks to and from a file.
 */
public class Storage {
    private static String filePath = "./data/duke.txt";

    /**
     * Constructs a Storage object with the specified file path.
     *
     * @param filePath The file path for storing tasks.
     */
    public Storage(String filePath) {
        assert filePath != null : "File path cannot be null";
        this.filePath = filePath;
    }

    // Solution below adapted and inspired by https://chat.openai.com/share/7f037351-3be6-4105-b138-77f68d428c84

    /**
     * Saves a list of tasks to the file specified by the file path.
     *
     * @param tasks The list of tasks to be saved.
     */
    public static void saveTasks(ArrayList<Task> tasks) {
        try (FileWriter writer = new FileWriter(filePath)) {
            for (Task task : tasks) {
                writer.write(task.toFileString());
            }
        } catch (IOException e) {
            System.out.println("Error writing to: " + filePath);
        }
    }

    // Solution adapted and inspired by
    // https://stackoverflow.com/questions/3090761/how-to-create-a-new-file-together-with-missing-parent-directories
    // Solution below adapted and inspired by https://www.guru99.com/buffered-reader-in-java.html
    // Solution below adapted and inspired by https://chat.openai.com/share/4f6c03e6-99d5-47c0-8887-1762a36b15fb

    /**
     * Loads tasks from the file specified by the file path.
     *
     * @return An ArrayList of Task objects loaded from the file.
     * @throws IOException If there is an issue reading from the file.
     */
    static ArrayList<Task> loadTasks() throws IOException {
        ArrayList<Task> tasks = new ArrayList<>();

        File file = getFileOrCreate();
        if (file != null) {
            try (BufferedReader reader = new BufferedReader(new FileReader(file))) {
                String dataToWrite;
                while ((dataToWrite = reader.readLine()) != null) {
                    System.out.println(dataToWrite); // Debug line
                    parseAndAddTask(dataToWrite, tasks);
                }
            } catch (IOException e) {
                System.out.println("Error reading from: " + filePath);
            }
        }
        return tasks;
    }

    private static File getFileOrCreate() throws IOException {
        File file = new File(filePath);
        assert file.exists() : "File does not exist: " + filePath;

        if (!file.exists()) {
            createFileAndDirectories(file);
        }

        return file;
    }

    private static void createFileAndDirectories(File file) throws IOException {
        if (file.getParentFile() != null) {
            // Solution below adapted from
            // https://www.oreilly.com/library/view/java-cookbook/0596001703/ch10s10.html#:~:
            // text=Of%20the%20two%20methods%20used,%2Fian%2Fbin%22).
            file.getParentFile().mkdirs();
        }
        file.createNewFile();
    }

    private static void parseAndAddTask(String dataToWrite, ArrayList<Task> tasks) {
        String[] sections = dataToWrite.split(" \\| ");

        if (sections.length >= 3) {
            String taskType = sections[0];
            String taskStatus = sections[1];
            String taskDescription = sections[2];
            String taskOtherInfo = sections.length > 3 ? sections[3] : "";

            Task task = createTask(taskType, taskDescription, taskOtherInfo);
            boolean isDone = taskStatus.equals("1");

            if (task != null) {
                if (isDone) {
                    task.markAsDone();
                } else {
                    task.markAsNotDone();
                }
                tasks.add(task);
            }
        }
    }

    private static Task createTask(String taskType, String taskDescription, String taskOtherInfo) {
        Task task = null;

        switch (taskType) {
        case "E":
            task = createEventTask(taskDescription, taskOtherInfo);
            break;
        case "D":
            task = createDeadlineTask(taskDescription, taskOtherInfo);
            break;
        case "T":
            task = createTodoTask(taskDescription);
            break;
        default:
            throw new IllegalArgumentException("Unsupported task type: " + taskType);
        }
        return task;
    }

    private static Task createEventTask(String taskDescription, String taskOtherInfo) {
        String[] eventParts = taskOtherInfo.trim().split("-");
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("MMMM d yyyy ha");
        LocalDateTime parsedFromStartDateTime = LocalDateTime.parse(eventParts[0].trim(), formatter);
        LocalDateTime parsedToDateTime = LocalDateTime.parse(eventParts[1].trim(), formatter);
        return new Event(taskDescription, parsedFromStartDateTime, parsedToDateTime);
    }

    private static Task createDeadlineTask(String taskDescription, String taskOtherInfo) {
        DateTimeFormatter formatterD = DateTimeFormatter.ofPattern("dd MMM yyyy h:mma");
        LocalDateTime dateTimeD = LocalDateTime.parse(taskOtherInfo, formatterD);
        return new Deadline(taskDescription, dateTimeD);
    }

    private static Task createTodoTask(String taskDescription) {
        return new Todo(taskDescription);
    }
}
