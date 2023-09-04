import commands.*;
import functions.*;
import java.io.FileNotFoundException;
import java.io.IOException;

/**
 * The main class for running the CR7 task management application.
 */
public class CR7 {
    private Storage storage;
    private Ui ui;
    private TaskList tasks;

    /**
     * Runs the CR7 application by displaying a welcome message, processing user commands,
     * and executing corresponding actions.
     */
    public void run() {
        ui.showWelcome();
        boolean isExit = false;
        while (!isExit) {
            try {
                String fullCommand = ui.readCommand();
                ui.showLine();
                Command c = Parser.parse(fullCommand);
                c.execute(tasks, ui, storage);
                isExit = c.isExit();
            } catch (IOException e) {
                ui.showErrorMsg("An error occurred while processing the command.");
            } finally {
                ui.showLine();
            }
        }
    }

    /**
     * Constructs a CR7 object with the provided file path for data storage.
     *
     * @param filePath The file path for storing task data.
     */
    public CR7(String filePath) {
        ui = new Ui();
        storage = new Storage(filePath);
        try {
            tasks = new TaskList(storage.loadFiles());
        } catch (FileNotFoundException e) {
            ui.showErrorMsg("Error: Task data file not found.");
            tasks = new TaskList();
        }
    }

    /**
     * The main method to start the CR7 application.
     *
     * @param args The command-line arguments (not used in this context).
     */
    public static void main(String[] args) {
        new CR7("src/main/data/CR7.txt").run();
    }
}
