package duke;

/**
 * The Parser class handles parsing of user commands and input for the Duke application.
 * It provides methods to recognize and process different types of commands and tasks.
 */
public class Parser {

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
     * Checks if the input is a "bye" command.
     *
     * @param input The user input.
     * @return True if the input is a "bye" command, false otherwise.
     */
    public static boolean isByeCommand(String input) {
        return input.equals("bye");
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
     * Parses the user command and performs the corresponding action.
     *
     * @param input The user input.
     * @param tasks The list of tasks.
     * @param ui    The user interface.
     * @return True if the application should continue running, false if the user wants to exit.
     * @throws DukeException If there is an error in processing the command.
     */
    public static boolean parseCommand(String input, TaskList tasks, Ui ui) throws DukeException {
=======
    public static boolean isValidCommand(String input, TaskList tasks, Ui ui) throws DukeException {
>>>>>>> branch-A-CodingStandard

        int taskIndex;
        if (isDeleteCommand(input)) {
            taskIndex = Integer.parseInt(input.substring(7).trim()) - 1;
            if (taskIndex >= 0 && taskIndex < tasks.getSize()) {
                tasks.removeTask(taskIndex);
            }
            return true;
        } else if (isMarkCommand(input)) {
            taskIndex = Integer.parseInt(input.substring(5).trim()) - 1;
            if (taskIndex >= 0 && taskIndex < tasks.getSize()) {
                tasks.markTask(taskIndex);
            }
            return true;
        } else if (isUnmarkCommand(input)) {
            taskIndex = Integer.parseInt(input.substring(7).trim()) - 1;
            if (taskIndex >= 0 && taskIndex < tasks.getSize()) {
                tasks.unmarkTask(taskIndex);
            }
            return true;
        }

        if (isByeCommand(input)) {
            return false;
        } else if (isListCommand(input)) {
            ui.showTaskList(tasks);
        } else if (isCreateTaskCommand(input)) {
            Task newTask = parseCreateTaskInput(input);
            tasks.addTask(newTask);
        } else {
            throw new DukeException("I'm sorry, but I don't know what that means :-(");
        }
        return true;
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
        String done = parts[1].trim();
        String taskDescription = parts[2].trim();
        System.out.println(taskDescription);
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
            if (done.equals("0") && task != null) {
                task.isDone = false;
            }
        } catch (DukeException e) {
            System.err.println(e.getMessage());
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
    public static Task parseCreateTaskInput(String input) throws DukeException {
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
        }
        return newTask;
    }
}