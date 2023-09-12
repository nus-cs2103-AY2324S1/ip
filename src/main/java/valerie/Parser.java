package valerie;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;

/**
 * The Parser class is responsible for parsing user input and file input
 * to create and manipulate tasks in the Duke application.
 */
public class Parser {
    /**
     * Parses user input and performs corresponding actions on the task list.
     *
     * @param userInput The user's input command.
     * @param taskList  The list of tasks to be manipulated.
     */
    public static ArrayList<String> parseUserInput(String userInput, TaskList taskList) {
        String[] parts = userInput.split(" ");
        String command = parts[0];

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
            return handleFindCommand(userInput, taskList);

        case "help":
            return handleHelpCommand();

        default:
            return Ui.showError("I'm sorry, but I don't know what that means!" +
                    "\nPlease type 'help' to access a list of available commands");
        }
    }

    /**
     * Parses a string representation of a task and creates a Task object.
     *
     * @param line The string representation of a task to be parsed.
     * @return A Task object created from the parsed string representation.
     */
    public static Task parseFileInput(String line) {
        String[] parts = line.split("\\|");
        String type = parts[0].trim();
        boolean isDone = parts[1].trim().equals("1");
        String description = parts[2].trim();

        Task task = null;

        switch (type) {
            case "T":
                task = new ToDos(description);
                break;
            case "D":
                String by = parts[3].trim();

                // Parse the date and time
                LocalDate deadlineDate = LocalDate.parse(by, DateTimeFormatter.ofPattern("MMM d yyyy"));

                task = new Deadlines(description, deadlineDate);
                break;
            case "E":
                String from = parts[3].trim();
                String to = parts[4].trim();

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
        if (parts.length != 2) {
            return Ui.showError("Invalid command format");
        }

        try {
            int index = Integer.parseInt(parts[1]) - 1;
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
        if (parts.length != 2) {
            return Ui.showError("Invalid command format");
        }

        try {
            int index = Integer.parseInt(parts[1]) - 1;
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
        if (userInput.length() < 5) {
            // Incorrect input format for todos
            return Ui.showError("Incorrect input format for todo");
        }

        String description = userInput.substring(5).trim();

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
        String[] parts = userInput.split("/by");

        if (parts.length != 2) {
            return Ui.showError("Incorrect input format for deadline");
        }

        String description = parts[0].substring(9).trim();
        String by = parts[1].trim();

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
        String[] parts = userInput.split("/from|/to");

        if (parts.length != 3) {
            return Ui.showError("Incorrect input format for event");
        }

        String description = parts[0].substring(6).trim();
        String from = parts[1].trim();
        String to = parts[2].trim();

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
        if (parts.length != 2) {
            return Ui.showError("Invalid command format");
        }

        try {
            int index = Integer.parseInt(parts[1]) - 1;
            return taskList.deleteTask(taskList.getTask(index));
        } catch (NumberFormatException e) {
            return Ui.showError("Invalid task number");
        }
    }

    /**
     * Handles the "find" command, searching for tasks containing a keyword.
     *
     * @param userInput The user input.
     * @param taskList  The TaskList containing the tasks.
     * @return An ArrayList of strings with the result of the find operation.
     */
    private static ArrayList<String> handleFindCommand(String userInput, TaskList taskList) {
        String keyword = userInput.substring(5).trim();
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
