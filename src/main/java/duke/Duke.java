package duke;

import java.util.ArrayList;

import duke.command.Command;
import duke.exception.DukeException;
import duke.parser.Parser;
import duke.storage.Storage;
import duke.tasks.TaskList;
import javafx.application.Platform;

/**
 * Duke is a Chatbot that helps you keep track of your tasks.
 * In this version, Duke is renamed to Nano.
 */
public class Duke {

    /**
     * The file path to store the User's Task Data.
     */
    private static final String FILE_PATH = "./data/duke.txt";

    /**
     * The storage that is used to save and load the user's tasks.
     */
    private static Storage storage;

    /**
     * The task list that is used to store the user's tasks.
     */
    private TaskList tasks;


    /**
     * Boolean value to check if the chat has ended.
     */
    private boolean isChatEnd = false;

    /**
     * Handles the Chatbot Response.
     */
    public String getResponse(String input) {

        if (isChatEnd) {
            Platform.exit();
        }

        Parser parseLine = new Parser(input);
        Command command = parseLine.getCommand();

        if (command == null) {
            return "I don't understand what you're saying.";
        }

        try {
            switch (command) {
            case BYE:
                isChatEnd = true;
                return "Chat has ended! Please Exit.";
            case LIST:
                return tasks.formatList();
            case MARK:
            case UNMARK:
                return tasks.handleMarking(parseLine.getArguments(), command.getCommandName());
            case DELETE:
                return tasks.handleDelete(parseLine.getArguments());
            case TODO:
                String todoData = parseLine.parseToDoArguments();
                return tasks.handleToDo(todoData);
            case DEADLINE:
                String[] deadlineData = parseLine.parseDeadlineArguments();
                return tasks.handleDeadline(deadlineData[0], deadlineData[1]);
            case EVENT:
                String[] eventData = parseLine.parseEventArguments();
                return tasks.handleEvent(eventData[0], eventData[1], eventData[2]);
            case FIND:
                String findQuery = parseLine.parseFindQuery();
                return tasks.findTasks(findQuery).formatList();
            default:
                return "I don't understand what you're saying.";
            }
        } catch (DukeException e) {
            return e.getMessage();
        }
    }

    /**
     * Runs the Chatbot program.
     */
    public void run() {
        assert !isChatEnd: "Chat should not be Ended when first run!";
        storage = new Storage(FILE_PATH);

        try {
            tasks = new TaskList(storage.load(), storage);
        } catch (DukeException e) {
            System.out.println("--- No Data Stored ---");
            tasks = new TaskList(new ArrayList<>(), storage);
        }
    }


    /**
     * The main method is used to run the program.
     *
     * @param args The command line arguments.
     */
    public static void main(String[] args) {
        new Duke().run();
    }
}
