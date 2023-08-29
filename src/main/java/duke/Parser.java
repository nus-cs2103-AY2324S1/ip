package duke;

public class Parser {
    public static final String COMMAND_TODO = "todo";
    public static final String COMMAND_DEADLINE = "deadline";
    public static final String COMMAND_EVENT = "event";
    public static final String COMMAND_DELETE = "delete";
    public static final String COMMAND_MARK = "mark";
    public static final String COMMAND_UNMARK = "unmark";

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

    private static void addTodoTask(String args, TaskList taskList) {
        if (args.isEmpty()) {
            throw new IllegalArgumentException("☹ OOPS!!! The description of a todo cannot be empty.");
        }
        taskList.addTodo(args, false);

    }

    private static void addDeadlineTask(String args, TaskList taskList) {
        String[] parts = args.split(" /by ");
        if (parts.length != 2 || parts[0].isEmpty() || parts[1].isEmpty()) {
            throw new IllegalArgumentException("☹ OOPS!!! Invalid deadline format.");
        }
        taskList.addDeadline(parts[0], false, parts[1]);
    }

    private static void addEventTask(String args, TaskList taskList) {
        String[] parts = args.split(" /at ");
        if (parts.length != 3 || parts[0].isEmpty() || parts[1].isEmpty() || parts[2].isEmpty()) {
            throw new IllegalArgumentException("☹ OOPS!!! Invalid event format.");
        }
        taskList.addEvent(parts[0], false, parts[1], parts[2]);
    }

    private static void parseMarkTask(String args, TaskList taskList) {
        int index = Integer.parseInt(args) - 1;
        taskList.markTask(index);
    }

    private static void parseUnmarkTask(String args, TaskList taskList) {
        int index = Integer.parseInt(args) - 1;
        taskList.unmarkTask(index);
    }

    private static void parseDeleteTask(String args, TaskList taskList) {
        int index = Integer.parseInt(args) - 1;
        taskList.removeTask(index);
    }
}
