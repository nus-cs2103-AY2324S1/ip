package duke;

/**
 * The Parser class handles the parsing of user input commands and performs
 * corresponding actions.
 */
public class Parser {
    public static final String COMMAND_TODO = "todo";
    public static final String COMMAND_DEADLINE = "deadline";
    public static final String COMMAND_EVENT = "event";
    public static final String COMMAND_DELETE = "delete";
    public static final String COMMAND_MARK = "mark";
    public static final String COMMAND_UNMARK = "unmark";

    /**
     * Parses the user input and adds the corresponding task to the task list.
     *
     * @param userInput The user input command.
     * @param taskList  The task list to which the task will be added.
     * @throws IllegalArgumentException If the input command is invalid.
     */
    public static void parseAndAddTask(String userInput, TaskList taskList) {
        String[] words = userInput.split(" ", 2);

        switch (words[0].toLowerCase()) {
            case COMMAND_TODO:
                addTodoTask(words[1], taskList);
                break;
            case COMMAND_DEADLINE:
                addDeadlineTask(words[1], taskList);
                break;
            case COMMAND_EVENT:
                addEventTask(words[1], taskList);
                break;
            case COMMAND_MARK:
                parseMarkTask(words[1], taskList);
                break;
            case COMMAND_UNMARK:
                parseUnmarkTask(words[1], taskList);
                break;
            case COMMAND_DELETE:
                parseDeleteTask(words[1], taskList);
                break;
            default:
                throw new IllegalArgumentException("Invalid command: " + words[0]);
        }
    }

    /**
     * Parses the user input and adds a todo task to the task list.
     *
     * @param args     The user input arguments.
     * @param taskList The task list to which the task will be added.
     * @throws IllegalArgumentException If the description of the todo task is
     *                                  empty.
     */
    private static void addTodoTask(String args, TaskList taskList) {
        if (args.isEmpty()) {
            throw new IllegalArgumentException("☹ OOPS!!! The description of a todo cannot be empty.");
        }
        taskList.addTodo(args, false);

    }

    /**
     * Parses the user input and adds a deadline task to the task list.
     *
     * @param args     The user input arguments.
     * @param taskList The task list to which the task will be added.
     * @throws IllegalArgumentException If the deadline format is invalid.
     */
    private static void addDeadlineTask(String args, TaskList taskList) {
        String[] parts = args.split(" /by ");
        if (parts.length != 2 || parts[0].isEmpty() || parts[1].isEmpty()) {
            throw new IllegalArgumentException("☹ OOPS!!! Invalid deadline format.");
        }
        taskList.addDeadline(parts[0], false, parts[1]);
    }

    /**
     * Parses the user input and adds an event task to the task list.
     *
     * @param args     The user input arguments.
     * @param taskList The task list to which the task will be added.
     * @throws IllegalArgumentException If the event format is invalid.
     */
    private static void addEventTask(String args, TaskList taskList) {
        String[] parts = args.split(" /from ");
        String from = parts[1].split(" /to ")[0];
        String to = parts[1].split(" /to ")[1];

        taskList.addEvent(parts[0], false, from, to);
    }

    /**
     * Parses the user input and marks a task as done.
     *
     * @param args     The user input arguments.
     * @param taskList The task list containing the tasks.
     */
    private static void parseMarkTask(String args, TaskList taskList) {
        int index = Integer.parseInt(args) - 1;
        taskList.markTask(index);
    }

    /**
     * Parses the user input and unmarks a task.
     *
     * @param args     The user input arguments.
     * @param taskList The task list containing the tasks.
     */
    private static void parseUnmarkTask(String args, TaskList taskList) {
        int index = Integer.parseInt(args) - 1;
        taskList.unmarkTask(index);
    }

    /**
     * Parses the user input and deletes a task.
     *
     * @param args     The user input arguments.
     * @param taskList The task list containing the tasks.
     */
    private static void parseDeleteTask(String args, TaskList taskList) {
        int index = Integer.parseInt(args) - 1;
        taskList.removeTask(index);
    }
}
