package duke;

import java.io.File;
import java.time.LocalDate;
import java.time.format.DateTimeParseException;
import java.util.ArrayList;
import java.util.Collections;


/**
 * The TaskList class manages a list of tasks and provides methods to interact with and manipulate the tasks.
 */
public class TaskList {
    private static File taskList;
    private static int taskCount = 0;
    private static ArrayList<Task> tasks = new ArrayList<>();
    private final Ui ui = new Ui();
    private Storage storage;

    /**
     * Constructs a TaskList instance with the specified file containing task data.
     *
     * @param storage The file containing task data.
     */
    public TaskList(Storage storage) {
        this.storage = storage;
    }

    /**
     * Constructs a TaskList instance with a default file path for task data.
     */
    public TaskList() {
        taskList = new File("./src/main/data/tasklist.txt");
    }
    public ArrayList<Task> getTasks() {
        return tasks;
    }

    /**
     * Prints the contents of the task list.
     */
    public String handleList() {
        return storage.printFileContents();
    }

    /**
     * Displays the task list as a formatted string.
     *
     * @return The formatted string representation of the task list.
     */
    public String displayList() {
        StringBuilder res;
        try {
            if (taskCount == 0) {
                throw new DukeException("Error: There are no items in the list!");
            }
            res = new StringBuilder();
            for (int i = 0; i < taskCount; i++) {
                Task task = tasks.get(i);
                int index = i + 1;
                res.append(index).append(task.getTask()).append("\n");
            }
        } catch (DukeException emptyList) {
            res = new StringBuilder(emptyList.getMessage());
        }
        return res.toString();
    }

    /**
     * Displays the task list as a formatted string.
     *
     * @param tasks the task list to be displayed
     * @return The formatted string representation of the task list.
     */
    public static String displayList(ArrayList<Task> tasks) {
        String res = "";
        for (int i = 0; i < tasks.size(); i++) {
            Task task = tasks.get(i);
            int index = i + 1;
            res = res + index + task.getTask() + "\n";
        }
        return res;
    }

    /**
     * Deletes a task given its index as a string.
     *
     * @param input The string representation of the index of the task to be deleted.
     */
    public String delete(String input) {
        int taskIndex = Integer.parseInt(input.substring(7)) - 1;
        try {
            if (taskIndex >= taskCount || taskIndex < 0) {
                throw new DukeException("Error: Invalid Task Index!");
            } else {
                int remainingTasks = taskCount - 1;
                String res = ui.getDeleteTaskMessage(tasks, taskIndex, remainingTasks);
                tasks.remove(taskIndex);
                if (taskCount > 0) {
                    taskCount--;
                }
                storage.writeToFile(this);
                return res;
            }
        } catch (DukeException exception) {
            return exception.getMessage();
        }
    }

    /**
     * Marks a task as completed given its index as a string.
     *
     * @param input The string representation of the index of the task to be marked completed.
     */
    public String mark(String input) {
        int taskIndex = Integer.parseInt(input.substring(5)) - 1;
        try {
            if (taskIndex >= taskCount || taskIndex < 0) {
                throw new DukeException("Error: Invalid Task Index!");
            } else if (tasks.get(taskIndex).isMarked()) {
                throw new DukeException("Error: Task is already completed!");
            } else {
                tasks.get(taskIndex).mark();
                assert tasks.get(taskIndex).isMarked() : "Unable to mark Task!";
                storage.writeToFile(this);
                return ui.getMarkedMessage(tasks, taskIndex);
            }
        } catch (DukeException exception) {
            return exception.getMessage();
        }
    }

    /**
     * Unmarks a task as completed given its index as a string.
     *
     * @param input The string representation of the index of the task to be unmarked as deleted.
     */
    public String unMark(String input) {
        int taskIndex = Integer.parseInt(input.substring(7)) - 1;
        try {
            if (taskIndex >= taskCount || taskIndex < 0) {
                throw new DukeException("Error: Invalid Task Index!");
            } else if (!tasks.get(taskIndex).isMarked()) {
                throw new DukeException("Error: Task is already marked as incomplete!");
            } else {
                tasks.get(taskIndex).unMark();
                storage.writeToFile(this);
                assert !tasks.get(taskIndex).isMarked() : "Unable to unmark Task!";
                return ui.getUnmarkedMessage(tasks, taskIndex);
            }
        } catch (DukeException exception) {
            return exception.getMessage();
        }
    }

    /**
     * Adds a new task to the task list and provides a response message.
     *
     * @param task    The task to be added to the list.
     * @param taskId  The unique identifier for the task.
     * @return A formatted string response confirming the addition of the task and the updated task count.
     */
    public String addToList(Task task, int taskId) {
        int numTasks = taskCount + 1;
        tasks.add(taskId, task);
        assert tasks.size() == numTasks : "Error with tallying tasks";
        if (taskCount < tasks.size()) {
            taskCount++;
        }
        storage.writeToFile(this);
        return ui.getAddTaskMessage(task, numTasks);
    }

    /**
     * handles a todo task.
     *
     * @param input  The string representation of the todo task.
     */
    public String handleTodo(String input) {
        String nameOfTask = input.substring(5);
        ToDo task = new ToDo(nameOfTask);
        return addToList(task, taskCount);
    }

    /**
     * handles a deadline task.
     *
     * @param input The string representation of the todo task and deadline.
     */
    public String handleDeadline(String input) throws DukeException {
        if (!input.contains("/by ")) {
            throw new DukeException("Input does not contain '/by ' to indicate the deadline!");
        }
        String[] parts = input.split("/by ");
        String nameOfTask = parts[0].trim().substring(9);
        try {
            LocalDate deadline = LocalDate.parse(parts[1].trim());
            Deadline task = new Deadline(nameOfTask, deadline);
            return addToList(task, taskCount);
        } catch (DateTimeParseException e) {
            return ui.getInvalidDateMessage();
        }
    }

    /**
     * handles a event task.
     *
     * @param input The string representation of the event, start time, and end time.
     */
    public String handleEvent(String input) throws DukeException {
        if (!input.contains("/from ") || !input.contains("/to ")) {
            throw new DukeException("Input does not contain '/from' and/or "
                    + "'/to' to indicate event start and end dates.");
        }
        String[] taskAndTime = input.split("/from ");
        String[] fromAndTo = taskAndTime[1].split("/to ");
        try {
            LocalDate start = LocalDate.parse(fromAndTo[0].trim());
            LocalDate end = LocalDate.parse(fromAndTo[1].trim());
            String nameOfTask = taskAndTime[0].trim().substring(6);
            Event task = new Event(nameOfTask, start, end);
            return addToList(task, taskCount);
        } catch (DateTimeParseException e) {
            return ui.getInvalidDateMessage();
        }
    }

    /**
     * Searches for tasks containing a specified keyword and returns a formatted list of matching tasks.
     *
     * @param input The user's input string, which should include the keyword to search for.
     * @return A formatted string displaying a list of tasks that contain the specified keyword.
     */
    public String handleFind(String input) {
        ArrayList<Task> findTasks = new ArrayList<>();
        String itemToFind = input.substring(5);
        for (Task value : tasks) {
            String task = value.getTask();
            if (task.contains(itemToFind)) {
                findTasks.add(value);
            }
        }
        return displayList(findTasks);
    }
    /**
     * Sorts the list of tasks in ascending alphabetical order.
     */
    public String returnSortedList() {
        Collections.sort(tasks);
        return ui.getSortedMessage() + displayList();
    }
    public File getFile() {
        return taskList;
    }
}
