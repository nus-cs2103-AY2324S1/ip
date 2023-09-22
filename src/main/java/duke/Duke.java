package duke;

import java.time.format.DateTimeFormatter;

import duke.exceptions.DukeException;
import duke.exceptions.DukeIndexOutOfBoundsException;
import duke.tasks.Priority;
import duke.tasks.Task;
import duke.ui.Ui;

/**
 * The Max bot.
 */
public class Duke {

    /** Time format for the Max bot */
    public static final DateTimeFormatter TIME_FORMAT = DateTimeFormatter.ofPattern("dd MMM yyyy - HH:mm");
    /** TaskList object to store tasks */
    private TaskList tasks;
    /** Storage object to interact with data file(s) */
    private Storage storage;
    /** Parser object to parse user input */
    private Parser parser;

    /**
     * Constructs a bot.
     *
     * @param filePath Path of data file from root.
     */
    public Duke(String filePath) {
        this.storage = new Storage(filePath);
        this.parser = new Parser();
    }

    /**
     * Gets response from the bot.
     */
    public String getResponse(String input) {
        String botResponse = "";
        try {
            tasks = storage.readFromFile();

            assert tasks != null;

            String userInput = input.trim();
            Action action = Action.valueOf(userInput.split(" ")[0].toUpperCase());

            switch (action) {
            case BYE:
                botResponse = Ui.exit();
                break;

            case LIST:
                botResponse = getListString();
                break;

            case MARK:
                botResponse = getMarkedString(userInput);
                break;

            case UNMARK:
                botResponse = getUnmarkedString(userInput);
                break;

            case DELETE:
                botResponse = getDeletedString(userInput);
                break;

            case FIND:
                botResponse = getFilteredString(userInput);
                break;

            case CHANGE:
                botResponse = getChangedString(userInput);
                break;

            case TODO:
            case DEADLINE:
            case EVENT:
            default:
                botResponse = getTaskString(userInput);
                break;
            }

            storage.writeToFile(tasks);
        } catch (DukeException e) {
            botResponse = e.getMessage();
        } finally {
            return botResponse;
        }
    }

    private String getListString() {
        return "Here are the tasks in your list:\n" + tasks.toString();
    }

    private String getMarkedString(String userInput) {
        // get index by splitting user input and get task at that index from list
        int indexToBeMarked = Integer.parseInt(userInput.split(" ")[1]);

        if (indexToBeMarked < 1 || indexToBeMarked > tasks.getNumberOfTasks()) {
            throw new DukeIndexOutOfBoundsException("mark");
        }

        Task toBeMarked = tasks.getTaskAt(indexToBeMarked - 1);

        assert toBeMarked != null;

        toBeMarked.mark();
        return "Nice! I've marked this task as done:\n" + toBeMarked.toString();
    }

    private String getUnmarkedString(String userInput) {
        // get index by splitting user input and get task at that index from list
        int indexToBeUnmarked = Integer.parseInt(userInput.split(" ")[1]);

        if (indexToBeUnmarked < 1 || indexToBeUnmarked > tasks.getNumberOfTasks()) {
            throw new DukeIndexOutOfBoundsException("unmark");
        }

        Task toBeUnmarked = tasks.getTaskAt(indexToBeUnmarked - 1);

        assert toBeUnmarked != null;

        toBeUnmarked.unmark();
        return "OK, I've marked this task as not done yet:\n" + toBeUnmarked.toString();
    }

    private String getDeletedString(String userInput) {
        int indexToBeDeleted = Integer.parseInt(userInput.split(" ")[1]);

        if (indexToBeDeleted < 1 || indexToBeDeleted > tasks.getNumberOfTasks()) {
            throw new DukeIndexOutOfBoundsException("delete");
        }

        Task toBeDeleted = tasks.getTaskAt(indexToBeDeleted - 1);

        assert toBeDeleted != null;

        tasks.deleteTaskAt(indexToBeDeleted - 1);
        return "Noted. I've removed this task:\n" + toBeDeleted.toString()
                + "\nNow you have " + tasks.getNumberOfTasks() + " tasks in the list.";
    }

    private String getFilteredString(String userInput) {
        TaskList filtered = tasks.getFilteredTaskList(userInput);

        assert filtered != null;

        return "Here are the matching tasks in your list:\n" + filtered.toString();
    }

    private String getChangedString(String userInput) {
        int indexToBeChanged = Integer.parseInt(userInput.split(" ")[2]);

        if (indexToBeChanged < 1 || indexToBeChanged > tasks.getNumberOfTasks()) {
            throw new DukeIndexOutOfBoundsException("change priority");
        }

        Task toBeChanged = tasks.getTaskAt(indexToBeChanged - 1);

        assert toBeChanged != null;

        toBeChanged.changePriority(Priority.valueOf(userInput.split(" ")[3].toUpperCase()));
        return "Noted. I've changed the priority of this task:\n" + toBeChanged.toString();
    }

    private String getTaskString(String userInput) throws DukeException {
        Task taskToAdd = parser.getTask(userInput);
        try {
            tasks.addToList(taskToAdd);
            return "Got it. I've added this task:\n" + taskToAdd.toString()
                    + "\nNow you have " + tasks.getNumberOfTasks() + " tasks in the list.";
        } catch (NullPointerException e) {
            throw new DukeException("OOPS!!! Could not add task to the list");
        }
    }
}
