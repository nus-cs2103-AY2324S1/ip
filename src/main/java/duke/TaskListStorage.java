package duke;

import java.io.File;
import java.io.FileNotFoundException;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Scanner;
import java.util.concurrent.PriorityBlockingQueue;

import duke.exceptions.IncorrectCommandFormatException;
import duke.exceptions.InvalidIndexException;
import duke.exceptions.MissingDescriptionException;
import duke.tasks.Deadline;
import duke.tasks.Event;
import duke.tasks.Task;
import duke.tasks.Todo;
import duke.ui.Ui;

/**
 * The TaskListStorage class stores an arraylist of tasks.
 * It contains functionality to manipulate this list (ie, marking, unmarking,
 * adding, deleting tasks)
 * It also handles saving/loading the list of tasks to/from a file.
 *
 * Notes: Combining Task and Storage is a solution adopted from the discussion
 * here https://github.com/nus-cs2103-AY2324S1/forum/issues/30
 */
public class TaskListStorage {
    /**
     * The list of tasks.
     */
    private final ArrayList<Task> taskList = new ArrayList<>();

    /**
     * The file object to save/read the list of tasks to/from.
     */
    private final File file;

    /**
     * The filepath of the file to save the list of tasks to.
     */
    private String filepath;

    /**
     * The priority blocking queue of tasks used for the reminder feature.
     */
    private PriorityBlockingQueue<Task> taskQueue;

    /**
     * The mutex object to prevent concurrent access to the list of tasks.
     */
    private final Object mutex;

    /**
     * Creates a TaskListStorage object.
     * This also loads the list of tasks from the file.
     *
     * @param filepath The filepath of the file to save the list of tasks to.
     */
    public TaskListStorage(String filepath, Object mutex) {
        assert filepath != null : "Filepath is null!";

        this.mutex = mutex;
        this.taskQueue = new PriorityBlockingQueue<>();

        this.filepath = filepath;
        this.file = new File(filepath);
        File parentDir = file.getParentFile();
        if (!parentDir.exists()) {
            parentDir.mkdirs();
        }

        if (!this.file.exists()) {
            try {
                this.file.createNewFile();
            } catch (java.io.IOException e) {
                String errorString = "Error creating file! Make sure you have the correct permissions.\n"
                        + "Otherwise tasks will not be saved.\n"
                        + e.getMessage();
                Ui.printInLine(errorString);
            }
        }

        try {
            this.loadFromFile();
        } catch (FileNotFoundException | IncorrectCommandFormatException | MissingDescriptionException e) {
            Ui.printInLine(e.getMessage());
        }
    }

    private void loadFromFile()
            throws FileNotFoundException, IncorrectCommandFormatException, MissingDescriptionException {
        Scanner sc = new Scanner(this.file);
        while (sc.hasNextLine()) {
            String line = sc.nextLine();
            String[] taskInfo = line.split(" \\| ");
            String taskType = taskInfo[0];
            boolean isDone = taskInfo[1].equals("1");
            String taskDescription = taskInfo[2].replace("\\|", "|"); // Unescape

            assert taskType != null : "Task type is null!";
            assert taskDescription != null : "Task description is null!";

            try {
                switch (taskType) {
                case "T":
                    addTask(new Todo(taskDescription, isDone));
                    break;
                case "D":
                    addTask(new Deadline(taskDescription, LocalDate.parse(taskInfo[3]), isDone));
                    break;
                case "E":
                    addTask(
                            new Event(taskDescription, LocalDate.parse(taskInfo[3]), LocalDate.parse(taskInfo[4]),
                                    isDone));
                    break;
                default:
                    break;
                }
            } catch (java.time.format.DateTimeParseException e) {
                throw new IncorrectCommandFormatException(
                        "Error loading tasks from file! Please check your tasks.txt file for errors.\n"
                                + e.getMessage());
            }
        }
        sc.close();
    }

    private void writeTaskListToFile(ArrayList<Task> taskList, String filepath) {
        if (!this.file.exists()) {
            try {
                this.file.createNewFile();
            } catch (java.io.IOException e) {
                String errorString = "Error creating file! Make sure you have the correct permissions.\n"
                        + "Otherwise tasks will not be saved.\n"
                        + e.getMessage();
                Ui.printInLine(errorString);
            }
        }

        try {
            java.io.FileWriter fw = new java.io.FileWriter(filepath);
            for (Task task : taskList) {
                fw.write(task.toFileString() + "\n");
            }
            fw.close();
        } catch (java.io.IOException e) {
            Ui.printInLine(e.getMessage());
        }
    }

    /**
     * Prints the list of tasks.
     */
    public String printList() {
        String outputString = "";
        for (int i = 0; i < taskList.size(); i++) {
            outputString += (i + 1) + ". " + taskList.get(i) + "\n";
        }
        return outputString;
    }

    /**
     * Marks a task as done.
     *
     * @param index The index of the task to mark as done.
     * @throws InvalidIndexException If the index is invalid.
     */
    public String markAsDone(int index) throws InvalidIndexException {
        if (index >= taskList.size() || index < 0) {
            throw new InvalidIndexException("Cannot mark task, task list of size " + taskList.size()
                    + " does not contain a task at index " + index);
        }
        taskList.get(index).markAsDone();
        writeTaskListToFile(taskList, filepath);
        String outputString = "Nice! I've marked this task as done:\n" + Ui.TAB
                + taskList.get(index).toString();
        return outputString;
    }

    /**
     * Marks a task as undone.
     *
     * @param index The index of the task to mark as undone.
     * @throws InvalidIndexException If the index is invalid.
     */
    public String markAsUndone(int index) throws InvalidIndexException {
        if (index >= taskList.size() || index < 0) {
            throw new InvalidIndexException("Cannot mark task, task list of size " + taskList.size()
                    + " does not contain a task at index " + index);
        }
        taskList.get(index).markAsUndone();
        writeTaskListToFile(taskList, filepath);
        String outputString = "Ok! I've marked this task as not done yet:\n" + Ui.TAB
                + taskList.get(index).toString();
        return outputString;
    }

    /**
     * Method to encompass adding of tasks.
     * @param newTask
     * @return String outputString to be printed to the user
     * @throws MissingDescriptionException
     * @throws IncorrectCommandFormatException
     */
    public String addTask(Task newTask) throws MissingDescriptionException, IncorrectCommandFormatException {
        taskList.add(newTask);
        writeTaskListToFile(taskList, filepath);

        if (!(newTask instanceof Todo)) { // && newTask.getDueTime() < System.currentTimeMillis()
            synchronized (mutex) {
                taskQueue.add(newTask);
                mutex.notifyAll();
            }
        }

        String outputString = "Got it. I've added this task:\n" + Ui.TAB + taskList.get(taskList.size() - 1)
                + "\nNow you have " + taskList.size() + " tasks in the list.";
        return outputString;
    }

    /**
     * Deletes a task from the list of tasks.
     *
     * @param index The index of the task to delete.
     * @throws InvalidIndexException If the index is invalid.
     */
    public String deleteTask(int index) throws InvalidIndexException {
        if (index >= taskList.size() || index < 0) {
            throw new InvalidIndexException("Cannot delete task, task list of size " + taskList.size()
                    + " does not contain a task at index " + index);
        }
        Task task = taskList.get(index);
        taskList.remove(index);
        writeTaskListToFile(taskList, filepath);
        String outputString = "Noted. I've removed this task:\n" + task.toString()
                + "\nNow you have " + taskList.size() + " tasks in the list.";
        return outputString;
    }

    /**
     * Finds and printstasks that contain the keyword in their description
     *
     * @param keyword
     */
    public String findTasks(String keyword) {
        ArrayList<Task> matchingTasks = new ArrayList<>();
        for (Task task : taskList) {
            if (task.getDescription().matches(".*\\b" + keyword + "\\b.*")) {
                matchingTasks.add(task);
            }
        }
        String outputString = "Here are the matching tasks in your list:\n";
        for (int i = 0; i < matchingTasks.size(); i++) {
            outputString += (i + 1) + ". " + matchingTasks.get(i) + "\n";
        }
        return outputString;
    }

    /**
     * Returns the queue of tasks.
     *
     * @return The queue of tasks.
     */
    public PriorityBlockingQueue<Task> getTaskQueue() {
        return this.taskQueue;
    }
}
