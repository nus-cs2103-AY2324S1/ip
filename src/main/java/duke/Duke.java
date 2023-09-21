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
     * The Message to display when the Chat is Ended.
     */
    private static final String END_CHAT_MESSAGE = "Chat has ended! Please Exit.";

    /**
     * The Error Message to display when Invalid command is given.
     */
    private static final String INVALID_ERROR_MESSAGE = "I don't understand what you're saying.";

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
     *
     * @param input The raw String data.
     * @return The String response of the command.
     */
    public String getResponse(String input) {

        if (isChatEnd) {
            Platform.exit();
        }
        Parser parseLine = new Parser(input);
        Command command = parseLine.getCommand();

        if (command == null) {
            return INVALID_ERROR_MESSAGE;
        }

        try {
            String res = executeCommand(parseLine, command);
            return res;
        } catch (DukeException e) {
            return e.getMessage();
        }
    }

    /**
     * Executes the command from the Parser.
     *
     * @param parseLine The Parser for the String input.
     * @param command The command for the input from the Parser.
     * @return The String response from the command.
     * @throws DukeException If a problem occurs when executing the command.
     */
    private String executeCommand(Parser parseLine, Command command) throws DukeException {
        switch (command) {
        case BYE:
            isChatEnd = true;
            return END_CHAT_MESSAGE;
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
        case EDIT:
            String[] editArgs = parseLine.parseEditArguments();
            return tasks.editTask(editArgs[0], editArgs[1], editArgs[2]);
        default:
            return INVALID_ERROR_MESSAGE;
        }
    }

    /**
     * Runs the Chatbot program.
     */
    public void run() {
        assert !isChatEnd : "Chat should not be Ended when first run!";
        storage = new Storage(FILE_PATH);

        try {
            tasks = new TaskList(storage.load(), storage);
        } catch (DukeException e) {
            tasks = new TaskList(new ArrayList<>(), storage);
        }
    }


    /**
     * Runs the program.
     *
     * @param args The command line arguments.
     */
    public static void main(String[] args) {
        new Duke().run();
    }
}
