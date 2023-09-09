import command.Command;
import exception.DukeException;
import storage.Storage;
import task.TaskList;
import ui.Ui;
import parser.Parser;

/**
 * Represents the main class for the Duke application.
 * This class is responsible for initializing the system and starting the main loop.
 */
public class Duke {

    private final Storage storage;
    private TaskList tasks;
    private final Ui ui;

    /**
     * Constructs a Duke instance.
     *
     * @param filePath The file path where tasks are loaded from and saved to.
     * @throws DukeException If there is an error loading tasks from the file.
     */
    public Duke(String filePath) throws DukeException {
        ui = new Ui();
        storage = new Storage(filePath);
        try {
            tasks = new TaskList(storage.load());
        } catch (DukeException e) {
            tasks = new TaskList();
            throw new DukeException(Ui.loadingError(e.getMessage()));
        }
    }

    /**
     * Executes the main loop of the application.
     * This method will continuously prompt the user for commands until they exit the program.
     */
    public void run() {
        ui.showWelcome();
        ui.showCommandGuide();
        ui.showLine();
        boolean isExit = false;
        while (!isExit) {
            try {
                String fullCommand = ui.readCommand();
                ui.showLine(); // show the divider line ("_______")
                Command c = Parser.parse(fullCommand);
                c.execute(tasks, ui, storage);
                isExit = c.isExit();
            } catch (DukeException e) {
                System.out.println(Ui.error(e.getMessage()));
            } finally {
                ui.showLine();
            }
        }
    }

    /**
     * The main method to run the Duke application.
     *
     * @param args Command line arguments.
     * @throws DukeException If there's an error initializing the application.
     */
    public static void main(String[] args) throws DukeException {
        new Duke("./data/duke.txt").run();
    }
}
