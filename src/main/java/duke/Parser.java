package duke;

import java.util.ArrayList;

/**
 * The Parser class handles parsing of user commands and input for the Duke application.
 * It provides methods to recognize and process different types of commands and tasks.
 */
public class Parser {

    /**
     * Checks if the input is a command to find a task (todo, event, or deadline).
     *
     * @param input The user input.
     * @return True if the input is a find task command, false otherwise.
     */
    public static boolean isFindCommand(String input) {
        return input.startsWith("find");
    }

    /**
     * Parses a "find" command.
     *
     * @param input   The user input containing the "find" command and keyword.
     * @param tasks   The list of tasks to search within.
     * @param ui      The user interface responsible for displaying the matching tasks.
     * @return A string representation of the matching tasks.
     */
    public static String parseFindCommand(String input, TaskList tasks, Ui ui) {
        String keyword = input.replace("find", "").trim();
        ArrayList<Task> matchingTasks = tasks.findTasksWithKeyword(keyword);
        return ui.showMatchingTasks(matchingTasks);
    }

    /**
     * Checks if the input is a command to create a task (todo, event, or deadline).
     *
     * @param input The user input.
     * @return True if the input is a create task command, false otherwise.
     */
    public static boolean isCreateTaskCommand(String input) {
        String trimmedInput = input.trim();
        return trimmedInput.startsWith("todo")
                || trimmedInput.startsWith("event")
                || trimmedInput.startsWith("deadline");
    }

    /**
     * Parses a command for creating a new task.
     *
     * @param input The user input containing the command and task details.
     * @param tasks The list of tasks.
     * @param ui    The user interface.
     * @return A string message indicating the result of the create task operation.
     * @throws DukeException If there is an error in parsing the input or creating the task.
     */
    private static String parseCreateTaskCommand(String input, TaskList tasks, Ui ui) throws DukeException {
        Task newTask = parseStringToTask(input);
        return tasks.addTask(newTask, ui);
    }

    /**
     * Checks if the input is a "list" command.
     *
     * @param input The user input.
     * @return True if the input is a "list" command, false otherwise.
     */
    public static boolean isListCommand(String input) {
        return input.equals("list");
    }

    /**
     * Displays the list of tasks using the user interface.
     *
     * @param tasks The list of tasks to be displayed.
     * @param ui    The user interface responsible for rendering the task list.
     * @return A string representation of the task list.
     */
    private static String showTaskList(TaskList tasks, Ui ui) {
        return ui.showTaskList(tasks);
    }

    /**
     * Checks if the input is a "mark" command.
     *
     * @param input The user input.
     * @return True if the input is a "mark" command, false otherwise.
     */
    public static boolean isMarkCommand(String input) {
        return input.startsWith("mark");
    }

    /**
     * Parses a "mark" command.
     *
     * @param input The user input containing the "mark" command and task index.
     * @param tasks The list of tasks.
     * @param ui    The user interface.
     * @return A string message indicating the result of the mark operation.
     * @throws AssertionError If the task index is out of bounds.
     */
    private static String parseMarkCommand(String input, TaskList tasks, Ui ui) {
        int taskIndex;
        taskIndex = Integer.parseInt(input.replace("mark", "").trim()) - 1;

        assert (taskIndex >= 0 && taskIndex < tasks.getSize());

        return tasks.markTask(taskIndex, ui);
    }

    /**
     * Checks if the input is a "unmark" command.
     *
     * @param input The user input.
     * @return True if the input is a "mark" command, false otherwise.
     */
    public static boolean isUnmarkCommand(String input) {
        return input.startsWith("unmark");
    }

    /**
     * Parses an "unmark" command.
     *
     * @param input The user input containing the "unmark" command and task index.
     * @param tasks The list of tasks.
     * @param ui    The user interface.
     * @return A string message indicating the result of the unmark operation.
     * @throws AssertionError If the task index is out of bounds.
     */
    private static String parseUnmarkCommand(String input, TaskList tasks, Ui ui) {
        int taskIndex;
        taskIndex = Integer.parseInt(input.replace("unmark", "").trim()) - 1;

        assert (taskIndex >= 0 && taskIndex < tasks.getSize());

        return tasks.unmarkTask(taskIndex, ui);
    }

    /**
     * Checks if the input is a "delete" command.
     *
     * @param input The user input.
     * @return True if the input is a "delete" command, false otherwise.
     */
    public static boolean isDeleteCommand(String input) {
        return input.startsWith("delete");
    }

    /**
     * Parses a "delete" command.
     *
     * @param input The user input containing the "delete" command and task index.
     * @param tasks The list of tasks.
     * @param ui    The user interface.
     * @return A string message indicating the result of the delete operation.
     * @throws AssertionError If the task index is out of bounds.
     */
    private static String parseDeleteCommand(String input, TaskList tasks, Ui ui) {
        int taskIndex;
        taskIndex = Integer.parseInt(input.replace("delete", "").trim()) - 1;

        assert (taskIndex >= 0 && taskIndex < tasks.getSize());

        return tasks.removeTask(taskIndex, ui);
    }

    /**
     * Performs a corresponding action based on the user's input.
     *
     * @param input The user input.
     * @param tasks The list of tasks.
     * @param ui    The user interface.
     * @return A string of the task or task list to be displayed on the UI.
     * @throws DukeException If there is an error in processing the command.
     */
    public static String parseCommand(String input, TaskList tasks, Ui ui) throws DukeException {

        if (isListCommand(input)) {
            return showTaskList(tasks, ui);
        } else if (isFindCommand(input)) {
            return parseFindCommand(input, tasks, ui);
        } else if (isCreateTaskCommand(input)) {
            return parseCreateTaskCommand(input, tasks, ui);
        } else if (isDeleteCommand(input)) {
            return parseDeleteCommand(input, tasks, ui);
        } else if (isMarkCommand(input)) {
            return parseMarkCommand(input, tasks, ui);
        } else if (isUnmarkCommand(input)) {
            return parseUnmarkCommand(input, tasks, ui);
        } else {
            throw new DukeException("Invalid input :(");
        }
    }

    /**
     * Converts a string representation of a task into a Task object.
     *
     * @param line The string representation of the task.
     * @return The Task object.
     * @throws DukeException If there is an error in parsing the task.
     */
    public static Task convertToTask(String line) throws DukeException {
        String[] parts = line.split("\\|\\|");

        if (parts.length < 5) {
            throw new DukeException("Invalid task format in file: " + line);
        }

        String taskType = parts[0].trim();
        String taskStatus = parts[1].trim();
        String taskDescription = parts[2].trim();
        String start = parts[3].trim();
        String end = parts[4].trim();
        Task task;

        switch (taskType) {
        case "T":
            task = new ToDo(taskDescription);
            break;
        case "D":
            task = new Deadline(taskDescription, end);
            break;
        case "E":
            task = new Event(taskDescription, start, end);
            break;
        default:
            throw new DukeException("Invalid task type!");
        }

        if (taskStatus.equals("0")) {
            task.markAsUndone();
        }
        return task;
    }

    /**
     * Parses the user input for creating a new task and returns the corresponding Task object.
     *
     * @param input The user input for creating a task.
     * @return The Task object created from the input.
     * @throws DukeException If there is an error in parsing the input or creating the task.
     */
    public static Task parseStringToTask(String input) throws DukeException {
        String[] parts = input.split("/");
        String taskDetails = parts[0].trim();
        TaskType taskType = determineTaskType(taskDetails);

        return createTask(taskType, taskDetails, parts);
    }

    /**
     * Determines the type of task based on the provided task details.
     *
     * @param taskDetails The description of the task.
     * @return The TaskType corresponding to the task details.
     * @throws DukeException If the task type is not recognized.
     */
    private static TaskType determineTaskType(String taskDetails) throws DukeException {
        if (taskDetails.startsWith("todo")) {
            return TaskType.TODO;
        } else if (taskDetails.startsWith("deadline")) {
            return TaskType.DEADLINE;
        } else if (taskDetails.startsWith("event")) {
            return TaskType.EVENT;
        } else {
            throw new DukeException("Invalid task type");
        }
    }

    /**
     * Creates a new task of the specified type with the given task details.
     *
     * @param taskType    The type of the task (TODO, DEADLINE, or EVENT).
     * @param taskDetails The description of the task.
     * @param parts       An array containing the task details and additional information.
     * @return A new task of the specified type.
     * @throws DukeException If there is an error in creating the task or if the input format is invalid.
     */
    private static Task createTask(TaskType taskType, String taskDetails, String[] parts) throws DukeException {
        String trimmedTaskDetails;

        switch (taskType) {
        case TODO:
            trimmedTaskDetails = taskDetails.substring(4).trim();
            return createToDoTask(trimmedTaskDetails);
        case DEADLINE:
            trimmedTaskDetails = taskDetails.substring(8).trim();
            return createDeadlineTask(trimmedTaskDetails, parts);
        case EVENT:
            trimmedTaskDetails = taskDetails.substring(5).trim();
            return createEventTask(trimmedTaskDetails, parts);
        default:
            throw new DukeException("Invalid task type");
        }
    }

    /**
     * Creates a new ToDo task with the given task details.
     *
     * @param taskDetails The description of the ToDo task.
     * @return A new ToDo task.
     * @throws DukeException If there is an error in creating the task.
     */
    private static Task createToDoTask(String taskDetails) throws DukeException {
        return new ToDo(taskDetails);
    }

    /**
     * Creates a new Deadline task with the given task details and deadline date.
     *
     * @param taskDetails The description of the Deadline task.
     * @param parts       An array containing the task details and deadline.
     * @return A new Deadline task.
     * @throws DukeException If there is an error in creating the task or if the input format is invalid.
     */
    private static Task createDeadlineTask(String taskDetails, String[] parts) throws DukeException {
        if (parts.length != 2 || parts[1].length() < 2) {
            throw new DukeException("Invalid input for a task with deadline. "
                    + "Please input 'deadline <task name> /by <end>'");
        }
        String date = parts[1].substring(2).trim();
        return new Deadline(taskDetails, date);
    }

    /**
     * Creates a new Event task with the given task details, start time, and end time.
     *
     * @param taskDetails The description of the Event task.
     * @param parts       An array containing the task details, start time, and end time.
     * @return A new Event task.
     * @throws DukeException If there is an error in creating the task or if the input format is invalid.
     */
    private static Task createEventTask(String taskDetails, String[] parts) throws DukeException {
        if (parts.length != 3 || parts[1].length() < 5 || parts[2].length() < 3) {
            throw new DukeException("Invalid input for an event. "
                    + "Please input 'event <event name> /from <start> /to <end>'");
        }
        String start = parts[1].substring(5).trim();
        String end = parts[2].substring(3).trim();
        return new Event(taskDetails, start, end);
    }
}
