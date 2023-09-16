import commands.Command;
import parser.Parser;
import storage.Storage;
import tasks.TaskList;
import ui.Ui;

/**
 * The Main class for the Duke application.
 * This class initialises a new Duke instance that handles the
 * control and flow of the application.
 */
public class Duke {

    private Storage storage;
    private TaskList tasks;
    private Ui ui;


    /**
     * Constructs a new Duke object, along with a Storage object and an Ui object.
     */
    public Duke() {
        storage = new Storage();
        this.tasks = new TaskList(storage.load());
    }

    /**
     * You should have your own function to generate a response to user input.
     * Replace this stub with your completed method.
     */
    public String getResponse(String input) {
        Command c;
        Parser parser = new Parser();
        c = parser.parse(input);

        String response = c.execute(tasks);
        storage.save(tasks.getTaskList());
        return response;
    }
}



