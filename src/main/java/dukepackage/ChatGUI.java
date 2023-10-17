package dukepackage;

import java.util.Objects;

/**
 * The ChatGUI class represents the graphical user interface (GUI)
 * for the iPBot chat bot application.
 * It handles user input, task management, and interactions with a storage system.
 */
public class ChatGUI {
    private final String INTRO = "    Hello! I'm iPbot \n" +
            "    What can I do for you?";
    protected Storage storage;

    /**
     * Constructs a ChatGUI object.
     *
     * @param storage The storage object used to load and save tasks.
     */
    public ChatGUI(Storage storage) {
        // Constructor body (if needed)
        // start the gui with loaded storage
        storage.loadListFromFile();
        // assertion
        assert storage != null : "Storage should be initialised";
        // set up storage for GUI
        this.storage = storage;
    }

    /**
     * Handles marking a task as done or undone.
     *
     * @param result The result string to append the task information or error message.
     * @param isDone Whether to mark the task as done or undone.
     * @param id     The ID of the task to be marked.
     */
    public String handleMarking(String result, Boolean isDone, int id) {
        try {
            storage.changeTaskMarking(id, isDone);
            result += storage.printTaskMarking(id);
        } catch (DukeException e) {
            result += e.getMessage();
        }
        return result;
    }

    /**
     * Handles adding a new todo task.
     *
     * @param input  The user input command.
     * @param result The result string to append the task information or error message.
     */
    public String handleTodo(String input, String result) {
        int indexOfTodo = input.indexOf("todo");
        String taskDesc = input.substring(indexOfTodo + 5);
        if (Objects.equals(taskDesc, "")) {
            String noDescError = "    OOPS!!! The description of a todo cannot be empty.";
            result += noDescError;
            return result;
        }
        Task task = new Task(taskDesc, TaskType.TODO, "", "");
        storage.addList(task);
        result += storage.printTaskEntry(task);
        return result;
    }

    /**
     * Handles adding a new deadline task.
     *
     * @param input  The user input command.
     * @param result The result string to append the task information or error message.
     */
    public String handleDeadline(String input, String result) {
        int indexOfDeadline = input.indexOf("deadline");
        int indexOfBy = input.indexOf("/by");
        String taskDesc = input.substring(indexOfDeadline + 9, indexOfBy);
        String deadlinePart = input.substring(indexOfBy + 3).trim();
        Task task = new Task(taskDesc, TaskType.DEADLINE, deadlinePart, "");
        storage.addList(task);
        result += storage.printTaskEntry(task);
        return result;
    }

    /**
     * Handles adding a new event task.
     *
     * @param input  The user input command.
     * @param result The result string to append the task information or error message.
     */
    public String handleEvent(String input, String result) {
        int indexOfEvent = input.indexOf("event");
        int indexOfFrom = input.indexOf("/from");
        int indexOfTo = input.indexOf("/to");
        String taskDesc = input.substring(indexOfEvent + 6, indexOfFrom);
        String fromPart = input.substring(indexOfFrom + 5, indexOfTo).trim();
        String toPart = input.substring(indexOfTo + 3).trim();
        Task task = new Task(taskDesc, TaskType.EVENT, fromPart, toPart);
        storage.addList(task);
        result += storage.printTaskEntry(task);
        return result;
    }

    public static String handleHelp(String result) {
        result += "Welcome to iPbot\n" +
                "\n" +
                "Here are the features supported by iPbot:\n" +
                "\n" +
                "1. `bye`: Exit the chat bot and save data to local storage.\n" +
                "   Example: \"bye\"\n" +
                "\n" +
                "2. `list`: Display the list of tasks.\n" +
                "   Example: \"list\"\n" +
                "\n" +
                "3. `mark`: Mark a task as done.\n" +
                "   Example: \"mark 1\" (Mark task number 1 as done)\n" +
                "\n" +
                "4. `unmark`: Unmark a task as done.\n" +
                "   Example: \"unmark 2\" (Unmark task number 2 as undone)\n" +
                "\n" +
                "5. `delete`: Delete a task from the list.\n" +
                "   Example: \"delete 3\" (Delete task number 3)\n" +
                "\n" +
                "6. `find`: Find tasks containing a specific keyword.\n" +
                "   Example: \"find homework\" (Find tasks with \"homework\" in their description)\n" +
                "\n" +
                "7. `todo`: Add a todo task.\n" +
                "   Example: \"todo Buy groceries\" (Adds a todo task with the description \"Buy groceries\")\n" +
                "\n" +
                "8. `deadline`: Add a deadline task.\n" +
                "   Example: \"deadline Submit report /by 2023-09-30 18:00\"\n" +
                "            (Adds a deadline task with the description \"Submit report\" and due date \"2023-09-30 18:00\")\n" +
                "\n" +
                "9. `event`: Add an event task.\n" +
                "   Example: \"event Team meeting /from 2023-09-25 14:00 /to 2023-09-25 16:00\"\n" +
                "            (Adds an event task with the description \"Team meeting,\" start time \"2023-09-25 14:00,\" and end time \"2023-09-25 16:00\")\n" +
                "\n" +
                "10. `help`: Display this help message.\n" +
                "    Example: \"help\"\n" +
                "\n" +
                "Tip: Do note that the acceptable format for date is YYYY-MM-DD HH-MM. \n" +
                "\nFeel free to use these commands to manage your tasks and interact with iPbot. Enjoy chatting!\n";
        return result;
    }

    /**
     * Processes the user input and returns the response.
     *
     * @param input The user input command.
     * @return The response string.
     */
    public String process(String input) {
        // Constants and instance variables
        String HORIZONTAL_LINE = " ------------------------------------------------------------\n";
        String result = HORIZONTAL_LINE;
        String[] parts = input.split(" ");
        String noCommandError = "    OOPS!!! I'm sorry, but I don't know what that means :-(";
        String OUTRO = "    Bye. Hope to see you again soon!\n";
        switch (parts[0]) {
            case "bye":
                result += OUTRO;
                // write the changes into the file duke.txt
                storage.writeTasksToFile();
                break;
            case "list":
                result += storage.printTaskList();
                break;
            case "mark":
                int id = Integer.parseInt(parts[1]) - 1;
                result = handleMarking(result, true, id);
                break;
            case "unmark":
                int id2 = Integer.parseInt(parts[1]) - 1;
                result = handleMarking(result, false, id2);
                break;
            case "delete":
                int id3 = Integer.parseInt(parts[1]) - 1;
                result += storage.deleteTask(id3);
                break;
            case "find":
                int indexOfFind = input.indexOf("find");
                String toFindString = input.substring(indexOfFind + 5);
                result += storage.printMatchingList(toFindString);
                break;
            case "todo":
                result = handleTodo(input, result);
                break;
            case "deadline":
                result = handleDeadline(input, result);
                break;
            case "event":
                result = handleEvent(input, result);
                break;
            case "help":
                result = handleHelp(result);
                break;
            default:
                result += noCommandError;
        }
        result += HORIZONTAL_LINE;
        return result;
    }
}
