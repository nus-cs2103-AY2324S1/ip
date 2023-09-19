package ekud.storage;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.time.LocalDateTime;
import java.util.Scanner;

import ekud.exceptions.EkudIOException;
import ekud.parser.Parser;
import ekud.tasks.*;

/**
 * The Storage class deals with handling the saved tasks file on the hard disk, by loading its contents
 * into the chatbot upon startup (or creating a new dir/file if it doesn't exist), and updating its
 * contents after the user is done with the chatbot.
 */
public class Storage {
    private static final String TASK_DONE_SYMBOL = "X";
    private final String path;
    private final File savedTasks;

    /**
     * Constructor for Storage, which initialises the directory and file to save tasks in.
     * @param path Filepath for the saved tasks file.
     * @throws EkudIOException Exception involving the creation of the saved tasks file.
     */
    public Storage(String path) {
        this.path = path;
        File file = new File(path);
        try {
            if (!file.getParentFile().exists()) { // Create directory if it doesn't exist
                file.getParentFile().mkdirs();
            }
            if (!file.exists()) { // Create file if it doesn't exist
                System.out.println("Creating task file...");
                file.createNewFile();
                System.out.println("Task file created successfully");
            }
        } catch (IOException e) {
            System.out.println("Error with creating task file: " + e);
        } finally {
            this.savedTasks = file;
        }
    }

    /**
     * Loads saved tasks from the file into the chatbot's TaskList.
     * @param taskList The chatbot's TaskList to load tasks into.
     * @throws EkudIOException Exception involving improper loading of saved tasks into TaskList.
     */
    public String loadData(TaskList taskList) throws EkudIOException {
        Parser parser = new Parser(); // For parsing dateTime
        taskList.clear();
        assert taskList.getSize() == 0 : "Task list should be empty before loading data";
        try {
            Scanner scanner = new Scanner(this.savedTasks);
            int curTaskIndex = 0;
            int numDoneTasks = 0;
            int numHighPriorityUndoneTasks = 0;
            while (scanner.hasNextLine()) {
                // Saved tasks format eg:
                // T |   | task1 | medium
                // D | X | task2 | 1st Sep | high
                // E |   | task3 | 1st Sep 2pm | 3rd Sep 2pm | low
                String[] taskDetails = scanner.nextLine().split(" \\| ");
                TaskType taskType = TaskType.getTaskType(taskDetails[0]);
                if (taskType == null) {
                    throw new EkudIOException("Error with parsing saved tasks: Invalid task type");
                }
                boolean isDoneTask = taskDetails[1].equals(TASK_DONE_SYMBOL);
                String description = taskDetails[2];
                Priority priority = Priority.getPriority(taskDetails[taskDetails.length - 1]);
                if (priority == null) {
                    throw new EkudIOException("Error with parsing saved tasks: Invalid priority");
                }
                if (priority.equals(Priority.HIGH) && !isDoneTask) {
                    numHighPriorityUndoneTasks++;
                }
                switch (taskType) {
                case TODO:
                    taskList.addTask(new ToDo(description, priority));
                    break;
                case DEADLINE:
                    LocalDateTime dateTime = parser.parseSavedDateTime(taskDetails[3]);
                    taskList.addTask(new Deadline(description, dateTime, priority));
                    break;
                case EVENT:
                    LocalDateTime fromDateTime = parser.parseSavedDateTime(taskDetails[3]);
                    LocalDateTime toDateTime = parser.parseSavedDateTime(taskDetails[4]);
                    taskList.addTask(new Event(description, fromDateTime, toDateTime, priority));
                    break;
                default:
                    throw new EkudIOException("Error with parsing saved tasks: Invalid task type");
                }
                if (isDoneTask) {
                    taskList.markDoneOnStart(curTaskIndex);
                    numDoneTasks++;
                }
                curTaskIndex++;
            }
            String response;
            if (curTaskIndex == 0) {
                response = "[No previous tasks saved]";
            } else {
                response = numHighPriorityUndoneTasks == 0
                        ? String.format(
                        "[You currently have (%d) unfinished task(s)]",
                        curTaskIndex - numDoneTasks)
                        : String.format(
                        "[You currently have (%d) HIGH priority task(s) out of (%d) unfinished task(s)]",
                        numHighPriorityUndoneTasks, curTaskIndex - numDoneTasks);
            };
            return response;
        } catch (IOException e) {
            throw new EkudIOException("Error with loading saved tasks: " + e);
        } catch (IndexOutOfBoundsException e) {
            throw new EkudIOException("Error with parsing saved tasks: " + e);
        }
    }

    /**
     * Updates the saved task file with the latest tasks when the program ends.
     * @param taskList The chatbot's TaskList to save tasks from.
     * @throws EkudIOException Exception involving improper saving of tasks.
     */
    public String saveData(TaskList taskList) throws EkudIOException {
        try {
            FileWriter fw = new FileWriter(this.path);
            int len = taskList.getSize();
            for (int i = 0; i < len; i++) {
                fw.write(taskList.getSaveTaskFormat(i) + "\n");
            }
            fw.close();
            return String.format("[(%d) task(s) saved successfully]", len);
        } catch (IOException e) {
            throw new EkudIOException("Error with saving tasks: " + e);
        }
    }

}
