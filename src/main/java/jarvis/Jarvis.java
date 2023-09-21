package jarvis;

import jarvis.command.Command;
import jarvis.exception.JarvisException;
import jarvis.parser.Parser;
import jarvis.storage.Storage;
import jarvis.tasklist.TaskList;
import jarvis.ui.Ui;

/**
 * Represents the main Jarvis application.
 * Initializes the application and starts the interaction with the user.
 */
public class Jarvis {

    private Ui ui;
    private Storage storage;
    private Parser parser;
    private TaskList tasks;

    /**
     * Constructs a Jarvis object.
     *
     * @param filePath The file path of the file to store the tasks.
     */
    public Jarvis(String filePath) {
        this.ui = new Ui();
        this.storage = new Storage(filePath);
        tasks = new TaskList(storage.loadTasks());
        this.parser = new Parser();
    }

    /**
     * Runs the Jarvis application until the user exits the application.
     */
    public void run() {
        ui.greet();
        boolean isExit = false;
        while (!isExit) {
            try {
                String fullCommand = ui.readCommand();
                Command c = parser.parse(fullCommand);
                c.execute(tasks, ui, storage);
                isExit = c.isExit();
            } catch (JarvisException e) {
                ui.display(e.getMessage());
            }
        }
    }

    /**
     * The main method to launch the Jarvis application.
     *
     * @param args The command-line arguments. Not used in this application.
     */
    public static void main(String[] args) {
        new Jarvis("./data/jarvis.txt").run();
    }
}
