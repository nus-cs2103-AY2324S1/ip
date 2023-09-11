package duke;

import duke.exceptions.DukeException;
import duke.exceptions.DukeIndexOutOfBoundsException;
import duke.tasks.Task;
import duke.ui.Ui;

import java.time.format.DateTimeFormatter;

/**
 * The Max bot.
 */
public class Duke {

    /** Time format for the Max bot */
    public static final DateTimeFormatter TIME_FORMAT = DateTimeFormatter.ofPattern("dd MMM yyyy - HH:mm");
    /** TaskList object to store tasks */
    private TaskList taskList;
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
        String response = "";
        try {
            taskList = storage.readFromFile();

            String userInput = input;
            Action action = Action.valueOf(userInput.split(" ")[0].toUpperCase());

            switch (action) {
            case BYE:
                response = Ui.exit();
                break;

            case LIST:
                response = "Here are the tasks in your list:\n" + taskList.toString();
                break;

            case MARK:
                // get index by splitting user input and get task at that index from list
                int markIndex = Integer.parseInt(userInput.split(" ")[1]);

                if (markIndex < 1 || markIndex > taskList.getNumberOfTasks()) {
                    throw new DukeIndexOutOfBoundsException("marked");
                }

                Task toBeMarked = taskList.getTaskAt(markIndex - 1);
                toBeMarked.mark();
                response = "Nice! I've marked this task as done:\n" + toBeMarked.toString();
                break;

            case UNMARK:
                // get index by splitting user input and get task at that index from list
                int unmarkIndex = Integer.parseInt(userInput.split(" ")[1]);

                if (unmarkIndex < 1 || unmarkIndex > taskList.getNumberOfTasks()) {
                    throw new DukeIndexOutOfBoundsException("unmarked");
                }

                Task toBeUnmarked = taskList.getTaskAt(unmarkIndex - 1);
                toBeUnmarked.unmark();
                response = "OK, I've marked this task as not done yet:\n" + toBeUnmarked.toString();
                break;

            case DELETE:
                int deleteIndex = Integer.parseInt(userInput.split(" ")[1]);

                if (deleteIndex < 1 || deleteIndex > taskList.getNumberOfTasks()) {
                    throw new DukeIndexOutOfBoundsException("deleted");
                }

                Task toBeDeleted = taskList.getTaskAt(deleteIndex - 1);
                taskList.deleteTaskAt(deleteIndex - 1);
                response = "Noted. I've removed this task:\n" + toBeDeleted.toString()
                        + "\nNow you have " + taskList.getNumberOfTasks() + " tasks in the list.";
                break;

            case FIND:
                TaskList filtered = parser.getTaskList(userInput, taskList);
                response = "Here are the matching tasks in your list:\n" + filtered.toString();
                break;

            case TODO:
            case DEADLINE:
            case EVENT:
            default:
                Task add = parser.getTask(userInput);
                try {
                    taskList.addToList(add);
                    response = "Got it. I've added this task:\n" + add.toString()
                            + "\nNow you have " + taskList.getNumberOfTasks() + " tasks in the list.";
                } catch (NullPointerException e) {
                    throw new DukeException("OOPS!!! Could not add task to the list");
                }
                break;
            }

            storage.writeToFile(taskList);
        } catch (DukeException e) {
            response = e.getMessage();
        } finally {
            return response;
        }
    }
}
