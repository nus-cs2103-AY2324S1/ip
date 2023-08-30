/**
 * Duke is a class in-charge of task management.
 * It allows users to add, delete, mark, unmark, specify, and list tasks.
 */
public class Duke {
    private Storage storage;
    private TaskList tasks;
    private Ui ui;

    /**
     * Constructor for Duke class
     *
     * @param filePath The filepath where task data is stored.
     */
    public Duke(String filePath) {
        ui = new Ui();
        storage = new Storage(filePath);
        try {
            tasks = new TaskList(storage.load());
        } catch (DukeException e) {
            ui.showLoadingError();
            tasks = new TaskList();
        }
    }

    /**
     * Runs the Duke application
     */
    public void run() {
        ui.showWelcome();
        boolean running = true;
        while (running) {
            String userInput = ui.readCommand();
            try {
                running = Parser.parseCommand(userInput, this.tasks, this.ui);
            } catch (DukeException e) {
                ui.showMessage(e.getMessage());
            }
        }
    }

    /**
     * The main function of the Duke application.
     *
     * @param args Command line arguments
     * @throws DukeException If an error has occurred in the Duke application.
     */
    public static void main(String[] args) {
        new Duke("./src/main/java/data/duke.txt").run();
    }
}
