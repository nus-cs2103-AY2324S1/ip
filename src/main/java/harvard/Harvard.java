package harvard;
/**
 * Represents the main program.
 */
public class Harvard {
    /**
     * The storage.
     */
    private Storage storage;
    /**
     * The list of tasks.
     */
    private TaskList tasks;
    /**
     * The user interface.
     */
    private Ui ui;
    /**
     * The parser.
     */
    private Parser parser;
    /**
     * Constructs a Harvard object.
     * @param filePath The file path of the file to store the tasks.
     */

    public Harvard(String filePath) {
        ui = new Ui();
        storage = new Storage(filePath);
        tasks = new TaskList(storage.loadTasks());
        parser = new Parser();
    }
    /**
     * Runs the program.
     */

    public void run() {
        ui.displayWelcome();
        boolean isExit = false;
        while (!isExit) {
            try {
                String fullCommand = ui.readCommand();
                Command c = parser.parse(fullCommand);
                c.execute(tasks, ui, storage);
                isExit = c.isExit();
            } catch (DukeException e) {
                ui.displayError(e);
            }
        }
    }
    /**
     * The main method.
     * @param args The command line arguments.
     */
    public static void main(String[] args) {
        new Harvard("data/tasks.txt").run();
    }
}
