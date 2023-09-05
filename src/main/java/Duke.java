import commands.Command;
import commands.ExitCommand;
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

    public static final String VERSION = "OwO Bot ─ a CS2103T iP ─ Week 3 Update";

    private Storage storage;
    private TaskList tasks;
    private Ui ui;

    /**
     * Constructs a new Duke object, along with a Storage object and an Ui object.
     */
    public Duke() {
        storage = new Storage();
        ui = new Ui();
    }

    /**
     * Runs the logic of the Duke application.
     */
    public void run() {

        this.tasks = new TaskList(storage.load());

        ui.printWelcomeMessage(VERSION);
        ui.printInstructions();

        runCommandLoop();

        storage.save(tasks.getTaskList());

        ui.printExitMessage();

    }

    /**
     * Main method of the Duke application.
     *
     * @param args
     */
    public static void main(String[] args) {
        new Duke().run();
    }

    /**
     * Runs the main loop of the Duke application.
     */
    public void runCommandLoop() {
        Command c;
        Parser parser = new Parser();
        do {
            String userInput = ui.getUserCommand();
            c = parser.parse(userInput);
            c.execute(tasks);
        } while (!ExitCommand.isExit(c));
    }
}
