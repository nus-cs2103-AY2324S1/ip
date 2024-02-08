package valerie;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;

/**
 * The Parser class is responsible for parsing user input and file input
 * to create and manipulate tasks in the Duke application.
 */
public class Parser {
    private static final int COMMAND_INDEX = 0;
    private static final int COMMAND_ID_INDEX = 1; // Index of command's additional information (e.g. number or keyword)
    private static final int COMMAND_LENGTH = 2;
    private static final String DATE_FORMAT_PATTERN = "MMM d yyyy";
    private static final int TASK_PARTS_DESCRIPTION_INDEX = 0;

    /**
     * Parses user input and performs corresponding actions on the task list.
     *
     * @param userInput The user's input command.
     * @param taskList  The list of tasks to be manipulated.
     */
    public static ArrayList<String> parseUserInput(String userInput, TaskList taskList) {
        String[] parts = userInput.split(" ");
        String command = parts[COMMAND_INDEX];

        switch (command) {
        case "list":
            return handleListCommand(taskList);

        case "mark":
            return handleMarkCommand(parts, taskList);

        case "unmark":
            return handleUnmarkCommand(parts, taskList);

        case "todo":
            return handleTodoCommand(userInput, taskList);

        case "deadline":
            return handleDeadlineCommand(userInput, taskList);

        case "event":
            return handleEventCommand(userInput, taskList);

        case "delete":
            return handleDeleteCommand(parts, taskList);

        case "find":
            return handleFindCommand(parts, taskList);

        case "help":
            return handleHelpCommand();

        default:
            return Ui.showError("I'm sorry, but I don't know what that means!\n"
                    + "Please type 'help' to access a list of available commands");
        }
    }

    /**
     * Parses a string representation of a task and creates a Task object.
     *
     * @param line The string representation of a task to be parsed.
     * @return A Task object created from the parsed string representation.
     */
    public static Task parseFileInput(String line) {
        final int FILE_TASK_DONE_STATUS_INDEX = 1;
        final int FILE_TASK_DESCRIPTION_INDEX = 2;
        final int FILE_DEADLINE_DATE_INDEX = 3;
        final int FILE_EVENT_FROM_INDEX = 3;
        final int FILE_EVENT_TO_INDEX = 4;

        String[] parts = line.split("\\|");
        String type = parts[COMMAND_INDEX].trim();
        boolean isDone = parts[FILE_TASK_DONE_STATUS_INDEX].trim().equals("1");
        String description = parts[FILE_TASK_DESCRIPTION_INDEX].trim();

        Task task = null;

        switch (type) {
        case "T":
            task = new ToDos(description);
            break;
        case "D":
            String by = parts[FILE_DEADLINE_DATE_INDEX].trim();

            // Parse the date and time
            LocalDate deadlineDate = LocalDate.parse(by, DateTimeFormatter.ofPattern(DATE_FORMAT_PATTERN));

            task = new Deadlines(description, deadlineDate);
            break;
        case "E":
            String from = parts[FILE_EVENT_FROM_INDEX].trim();
            String to = parts[FILE_EVENT_TO_INDEX].trim();

            task = new Events(description, from, to);
            break;
        default:
            break;
        }

        if (task != null && isDone) {
            task.markAsDone();
        }

        return task;
    }

    /**
     * Handles the "list" command, displaying the list of tasks.
     *
     * @param taskList The TaskList containing the tasks.
     * @return An ArrayList of strings representing the task list.
     */
    private static ArrayList<String> handleListCommand(TaskList taskList) {
        return Ui.showList(taskList);
    }

    /**
     * Handles the "mark" command, marking a task as done.
     *
     * @param parts    The array of command parts.
     * @param taskList The TaskList containing the tasks.
     * @return An ArrayList of strings with the result of the mark operation or an error message.
     */
    private static ArrayList<String> handleMarkCommand(String[] parts, TaskList taskList) {
        if (parts.length != COMMAND_LENGTH) {
            return Ui.showError("Invalid command format");
        }

        try {
            int index = Integer.parseInt(parts[COMMAND_ID_INDEX]) - 1;
            return taskList.markTask(taskList.getTask(index));
        } catch (NumberFormatException e) {
            return Ui.showError("Invalid task number");
        }
    }

    /**
     * Handles the "unmark" command, unmarking a task as done.
     *
     * @param parts    The array of command parts.
     * @param taskList The TaskList containing the tasks.
     * @return An ArrayList of strings with the result of the unmark operation or an error message.
     */
    private static ArrayList<String> handleUnmarkCommand(String[] parts, TaskList taskList) {
        if (parts.length != COMMAND_LENGTH) {
            return Ui.showError("Invalid command format");
        }

        try {
            int index = Integer.parseInt(parts[COMMAND_ID_INDEX]) - 1;
            return taskList.unmarkTask(taskList.getTask(index));
        } catch (NumberFormatException e) {
            return Ui.showError("Invalid task number");
        }
    }

    /**
     * Handles the "todos" command, adding a new ToDos task.
     *
     * @param userInput The user input.
     * @param taskList  The TaskList containing the tasks.
     * @return An ArrayList of strings with the result of adding the task or an error message.
     */
    private static ArrayList<String> handleTodoCommand(String userInput, TaskList taskList) {
        final int TODOS_MIN_LENGTH = 5;
        if (userInput.length() < TODOS_MIN_LENGTH) {
            // Incorrect input format for todos
            return Ui.showError("Incorrect input format for todo");
        }

        String description = userInput.substring(TODOS_MIN_LENGTH).trim();

        if (description.isBlank()) {
            return Ui.showError("The description of a todo cannot be empty");
        }

        Task newTask = new ToDos(description);
        return taskList.addTask(newTask);
    }

    /**
     * Handles the "deadline" command, adding a new Deadline task.
     *
     * @param userInput The user input.
     * @param taskList  The TaskList containing the tasks.
     * @return An ArrayList of strings with the result of adding the task or an error message.
     */
    private static ArrayList<String> handleDeadlineCommand(String userInput, TaskList taskList) {
        final int DEADLINE_MIN_LENGTH = 9;
        final int DEADLINE_PARTS_LENGTH = 2;
        final int DEADLINE_PARTS_BY_INDEX = 1;

        String[] parts = userInput.split("/by");

        if (parts.length != DEADLINE_PARTS_LENGTH) {
            return Ui.showError("Incorrect input format for deadline");
        }

        String description = parts[TASK_PARTS_DESCRIPTION_INDEX].substring(DEADLINE_MIN_LENGTH).trim();
        String by = parts[DEADLINE_PARTS_BY_INDEX].trim();

        if (description.isBlank() || by.isBlank()) {
            return Ui.showError("The description or by of a deadline cannot be empty");
        }

        LocalDate deadlineDate = LocalDate.parse(by);
        Task newTask = new Deadlines(description, deadlineDate);
        return taskList.addTask(newTask);
    }

    /**
     * Handles the "event" command, adding a new Event task.
     *
     * @param userInput The user input.
     * @param taskList  The TaskList containing the tasks.
     * @return An ArrayList of strings with the result of adding the task or an error message.
     */
    private static ArrayList<String> handleEventCommand(String userInput, TaskList taskList) {
        final int EVENT_MIN_LENGTH = 6;
        final int EVENT_PARTS_LENGTH = 3;
        final int EVENT_PARTS_FROM_INDEX = 1;
        final int EVENT_PARTS_TO_INDEX = 2;

        String[] parts = userInput.split("/from|/to");

        if (parts.length != EVENT_PARTS_LENGTH) {
            return Ui.showError("Incorrect input format for event");
        }

        String description = parts[TASK_PARTS_DESCRIPTION_INDEX].substring(EVENT_MIN_LENGTH).trim();
        String from = parts[EVENT_PARTS_FROM_INDEX].trim();
        String to = parts[EVENT_PARTS_TO_INDEX].trim();

        if (description.isBlank() || from.isBlank() || to.isBlank()) {
            return Ui.showError("The description or from or to of an event cannot be empty");
        }

        Task newTask = new Events(description, from, to);
        return taskList.addTask(newTask);
    }

    /**
     * Handles the "delete" command, deleting a task.
     *
     * @param parts    The array of command parts.
     * @param taskList The TaskList containing the tasks.
     * @return An ArrayList of strings with the result of the delete operation or an error message.
     */
    private static ArrayList<String> handleDeleteCommand(String[] parts, TaskList taskList) {
        if (parts.length != COMMAND_LENGTH) {
            return Ui.showError("Invalid command format");
        }

        try {
            int index = Integer.parseInt(parts[COMMAND_ID_INDEX]) - 1;
            return taskList.deleteTask(taskList.getTask(index));
        } catch (NumberFormatException e) {
            return Ui.showError("Invalid task number");
        }
    }

    /**
     * Handles the "find" command, searching for tasks containing a keyword.
     *
     * @param parts     The array of command parts.
     * @param taskList  The TaskList containing the tasks.
     * @return An ArrayList of strings with the result of the find operation.
     */
    private static ArrayList<String> handleFindCommand(String[] parts, TaskList taskList) {
        if (parts.length != COMMAND_LENGTH) {
            return Ui.showError("Invalid command format");
        }

        String keyword = parts[COMMAND_ID_INDEX].trim();

        if (keyword.isBlank()) {
            return Ui.showError("Please provide a keyword for the 'find' command.");
        }

        return taskList.findTasks(keyword);
    }

    /**
     * Handles the "help" command, displaying information about available commands.
     *
     * @return An ArrayList of strings with information about available commands.
     */
    private static ArrayList<String> handleHelpCommand() {
        ArrayList<String> helpText = new ArrayList<>();
        helpText.add("Here are the available commands:");

        String[] commands = {
            "list - Show list of tasks",
            "mark - Mark a task",
            "unmark - Unmark a task",
            "todo [task description] - Create a todos task",
            "deadline [task description] /by [YYYY-MM-DD] - Create a deadline task due by [YYYY-MM-DD]",
            "event [task description] /from [start] /to [end] - Create an event task from [start] to [end]",
            "find - Find a task with matching description",
            "delete - Delete a task",
            "bye - Save list of tasks and exit application"
        };

        for (int i = 0; i < commands.length; i++) {
            helpText.add((i + 1) + ". " + commands[i]);
        }

        return helpText;
    }
}
