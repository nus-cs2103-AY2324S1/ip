/**
 * Parser class that handles user input and calls the appropriate methods.
 */
public class Parser {
    private static final String HORLINE = "_____________________________________________________\n";
    private Storage storage;
    private TaskList tasks;

    /**
     * Constructor for Parser class.
     * 
     * @param storage Storage object that handles file I/O.
     * @param tasks   TaskList object that handles the list of tasks.
     */
    public Parser(Storage storage, TaskList tasks) {
        this.storage = storage;
        this.tasks = tasks;
    }

    /**
     * Parses the user input and calls the appropriate methods.
     * 
     * @param input String of user input.
     * @return String of output to be printed.
     */
    public String parseInput(String input) {
        String low = input.toLowerCase();
        if (low.equals("list")) {
            return tasks.lst();
        }
        
        int index = Math.max(0, low.indexOf(" "));
        String firstWord = low.substring(0, index);
        String end = "";

        try {
            end = input.substring(++index);
        } catch (StringIndexOutOfBoundsException e) {
        }

        if (firstWord.equals("mark") || firstWord.equals("unmark")) {
            return handleMarkUnmark(firstWord, end);
        } else if (firstWord.equals("find")) {
            return tasks.find(end);
        } else if (firstWord.equals("deadline")) {
            return handleDeadline(end);
        } else if (firstWord.equals("event")) {
            return handleEvent(end);
        } else if (firstWord.equals("todo")) {
            return handleTodo(end);
        } else if (firstWord.equals("delete")) {
            return handleDelete(end);
        } else {
            return HORLINE + "\u2639 OOPS!!! I'm sorry, but I don't know what that means :-(\n" + HORLINE;
        }
    }

    /**
     * Handles the mark/unmark command.
     * 
     * @param end String of the task index.
     * @return String of output to be printed.
     */
    private String handleMarkUnmark(String firstWord, String end) {
        int taskIndex = -1;

        try {
            taskIndex = Integer.valueOf(end) - 1;
        } catch (NumberFormatException e) {
        }

        if (taskIndex >= 0 && taskIndex < tasks.size()) {
            return tasks.toggle(storage, firstWord, taskIndex);
        } else {
            return HORLINE + "\u2639 OOPS!!! Please select a valid item to mark/unmark.\n" + HORLINE;
        }
    }

    /**
     * Handles the deadline command.
     * 
     * @param end String of the deadline.
     * @return String of output to be printed.
     */
    private String handleDeadline(String end) {
        try {
            int separatorIndex = end.indexOf("/by");

            String string1 = end.substring(0, separatorIndex).trim();
            String string2 = end.substring(separatorIndex + 3).trim();

            return tasks.addDeadline(storage, string1, string2);
        } catch (Exception e) {
            return HORLINE + "\u2639 OOPS!!! Your request needs to be formatted as:\ndeadline <task name> /by <time>\n" + HORLINE;
        }
    }

    /**
     * Handles the event command.
     * 
     * @param end String of the event.
     * @return String of output to be printed.
     */
    private String handleEvent(String end) {
        try {
            int fromIndex = end.indexOf("/from");
            int toIndex = end.indexOf("/to");

            String string1 = end.substring(0, fromIndex).trim();
            String string2 = end.substring(fromIndex + 6, toIndex).trim();
            String string3 = end.substring(toIndex + 4).trim();

            return tasks.addEvent(storage, string1, string2, string3);
        } catch (Exception e) {
            return HORLINE + "\u2639 OOPS!!! Your request needs to be formatted as:\nevent <event name> /from <start time> /to <end time>\n" + HORLINE;
        }
    }

    /**
     * Handles the todo command.
     * 
     * @param end String of the todo.
     * @return String of output to be printed.
     */
    private String handleTodo(String end) {
        String trimmed = end.trim();

        if (trimmed.equals("")) {
            return HORLINE + "\u2639 OOPS!!! The description of a todo cannot be empty.\n" + HORLINE;
        } else {
            return tasks.addTodo(storage, trimmed);
        }
    }

    /**
     * Handles the delete command.
     * 
     * @param end String of the task index.
     * @return String of output to be printed.
     */
    private String handleDelete(String end) {
        int taskIndex = -1;

        try {
            taskIndex = Integer.valueOf(end) - 1;
        } catch (NumberFormatException e) {
        }

        if (taskIndex >= 0 && taskIndex < tasks.size()) {
            return tasks.del(storage, taskIndex);
        } else {
            return HORLINE + "\u2639 OOPS!!! Please select a valid item to delete.\n" + HORLINE;
        }
    }
}
