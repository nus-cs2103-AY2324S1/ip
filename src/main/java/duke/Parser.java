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
     * Checks if the input is a "list" command.
     *
     * @param input The user input.
     * @return True if the input is a "list" command, false otherwise.
     */
    public static boolean isListCommand(String input) {
        return input.equals("list");
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
     * Checks if the input is a "unmark" command.
     *
     * @param input The user input.
     * @return True if the input is a "mark" command, false otherwise.
     */
    public static boolean isUnmarkCommand(String input) {
        return input.startsWith("unmark");
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
     * Performs a corresponding action based on the user's input.
     *
     * @param input The user input.
     * @param tasks The list of tasks.
     * @param ui    The user interface.
     * @return A string of the task or task list to be displayed on the UI.
     * @throws DukeException If there is an error in processing the command.
     */
    public static String parseCommand(String input, TaskList tasks, Ui ui) throws DukeException {

        if (!isValidCommand(input)) {
            throw new DukeException("I'm sorry, but I don't know what that means :-(");
        }

        if (isListCommand(input)) {
            return ui.showTaskList(tasks);
        }

        if (isFindCommand(input)) {
            String keyword = input.replace("find", "").trim();
            ArrayList<Task> matchingTasks = tasks.findTasksWithKeyword(keyword);
            return ui.showMatchingTasks(matchingTasks);
        }

        if (isCreateTaskCommand(input)) {
            Task newTask = parseStringToTask(input);
            return tasks.addTask(newTask, ui);
        }

        int taskIndex;
        if (isDeleteCommand(input)) {
            taskIndex = Integer.parseInt(input.replace("delete", "").trim()) - 1;
            if (taskIndex >= 0 && taskIndex < tasks.getSize()) {
                return tasks.removeTask(taskIndex, ui);
            }
        } else if (isMarkCommand(input)) {
            taskIndex = Integer.parseInt(input.replace("mark", "").trim()) - 1;
            if (taskIndex >= 0 && taskIndex < tasks.getSize()) {
                return tasks.markTask(taskIndex, ui);
            }
        } else if (isUnmarkCommand(input)) {
            taskIndex = Integer.parseInt(input.replace("unmark", "").trim()) - 1;
            if (taskIndex >= 0 && taskIndex < tasks.getSize()) {
                return tasks.unmarkTask(taskIndex, ui);
            }
        }
        throw new DukeException("Valid command, but invalid task input :(");
    }

    /**
     * Checks if the command entered is a valid command.
     *
     * @param input The user input.
     * @return True if command is recognisable, false if command is not recognisable.
     */
    public static boolean isValidCommand(String input) {
        return isDeleteCommand(input) || isMarkCommand(input) || isUnmarkCommand(input)
                || isListCommand(input) || isCreateTaskCommand(input)
                || isFindCommand(input);
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
        Task task = null;

        try {
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
            }
            if (taskStatus.equals("0") && task != null) {
                task.isDone = false;
            }
        } catch (DukeException e) {

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
        TaskType taskType;
        String[] parts = input.split("/");
        String taskDetails = parts[0].trim();

        if (taskDetails.startsWith("todo")) {
            taskType = TaskType.TODO;
            taskDetails = taskDetails.substring(4).trim();
        } else if (taskDetails.startsWith("deadline")) {
            taskType = TaskType.DEADLINE;
            taskDetails = taskDetails.substring(8).trim();
        } else {
            taskType = TaskType.EVENT;
            taskDetails = taskDetails.substring(5).trim();
        }

        Task newTask = null;
        switch (taskType) {
        case TODO:
            newTask = new ToDo(taskDetails);
            break;
        case DEADLINE:
            if (parts.length != 2 || parts[1].length() < 2) {
                // prevent java.lang.StringIndexOutOfBoundsException
                throw new DukeException("Invalid input for a task with deadline. " +
                        "Please input 'deadline <task name> /by <end>'");
            }
            String date = parts[1].substring(2).trim();
            newTask = new Deadline(taskDetails, date);
            break;
        case EVENT:
            if (parts.length != 3 || parts[1].length() < 5 || parts[2].length() < 3) {
                // prevent java.lang.StringIndexOutOfBoundsException
                throw new DukeException("Invalid input for an event. " +
                        "Please input 'event <event name> /from <start> /to <end>'");
            }
            String start = parts[1].substring(5).trim();
            String end = parts[2].substring(3).trim();
            newTask = new Event(taskDetails, start, end);
            break;
        default:
            throw new DukeException("Invalid task");
        }

        return newTask;
    }
}