package iris;

import java.util.ArrayList;
import java.util.Scanner;

/**
 * The main class for the Iris task management application.
 */
public class Iris {
    private final Storage taskStorage;
    private final Parser commandParser;
    private TaskList taskList;
    private final Ui ui;

    /**
     * Constructor for the Iris class.
     *
     * @param filePath The file path for storing task data.
     */
    public Iris(String filePath) {
        assert !filePath.isEmpty() : "filePath cannot be empty";
        commandParser = new Parser();
        taskStorage = new Storage(filePath);
        ui = new Ui();
        try {
            taskList = new TaskList(taskStorage.loadTask());
        } catch (LoadTaskException e) {
            ui.respond(e.toString());
            ArrayList<Task> listOfTasks = new ArrayList<Task>();
            taskList = new TaskList(listOfTasks);
        }
    }

    public String getResponse(String userCmd) {
        try {
            return commandParser.parseCommand(taskStorage, taskList, ui, userCmd);
        } catch (UnrecognizedCommandException e) {
            return ui.respond(e.toString());
        } catch (WriteTaskException e) {
            return ui.respond(e.toString());
        } catch (InvalidTaskException e) {
            return ui.respond(e.toString());
        }
    }
}
