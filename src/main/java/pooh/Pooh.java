package pooh;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

/**
 * Main application class for the Pooh chatbot.
 * <p>
 * This class is responsible for initializing storage, loading tasks, and running
 * the main event loop of the application. It delegates command parsing to the Parser
 * class and storage operations to the Storage class.
 * </p>
 */
public class Pooh {

    private final Storage taskStorage;
    private final Parser cmdParser;
    private TaskList taskList;
    private final Ui ui;

    /**
     * Constructs a new Pooh chatbot.
     *
     * @param filePath The file path where tasks are stored.
     */
    public Pooh(String filePath) {

        assert !filePath.isEmpty() : "filePath cannot be empty";

        cmdParser = new Parser();
        taskStorage = new Storage(filePath);
        ui = new Ui();
        try {
            taskList = new TaskList(taskStorage.loadTasks());
        } catch (LoadTasksException ex) {
            ui.respond(ex.toString());
            List<Task> listOfTasks = new ArrayList<Task>();
            taskList = new TaskList(listOfTasks);
        }
    }

    /**
     * Processes a given user command, parses it, and returns an appropriate response.
     * <p>
     * This method attempts to parse the user's command through the {@code cmdParser}. If the command is
     * valid, it performs the necessary operations and returns the response. Otherwise, if an exception
     * occurs, it handles the exception and returns a user-friendly error message.
     * </p>
     *
     * @param userCmd The command string inputted by the user.
     * @return A response string that results from processing the user's command.
     * This could be a successful operation message or an error message.
     */
    public String getResponse(String userCmd) {
        try {
            return cmdParser.parseInput(taskStorage, taskList, ui, userCmd);
        } catch (UnrecognizedCommandException ex) {
            return ui.respond(ex.toString());
        } catch (WriteTasksException ex) {
            return ui.respond(ex.toString());
        } catch (InvalidTaskException ex) {
            return ui.respond(ex.toString());
        }
    }
}