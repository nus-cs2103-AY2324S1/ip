package monke;

import monke.commands.Command;

/**
 * The main class of the Monke application. It serves as the entry point
 * for running the Monke program and managing tasks.
 */
public class Monke {
    /** Storage to handle writing and reading data into txt file */
    private Storage storage;
    /** List of tasks */
    private TaskList tasks;
    /** User interface that handles input and output */
    private Ui ui;

    /**
     * Constructs a Monke instance with the specified file path.
     *
     * @param filepath The path to the file where task data is stored.
     */
    public Monke(String filepath) {
        this.ui = new Ui();
        this.storage = new Storage(filepath);
        try {
            tasks = new TaskList(storage.load());
        } catch (MonkeException e) {
            tasks = new TaskList();
        }
    }

    /**
     * Runs the Monke application.
     * Displays welcome message and handles user commands until the program exits.
     */
    public void run() {
        ui.printHorizontalLine();
        ui.showWelcome();
        boolean isExit = false;

        while (!isExit) {
            try {
                String fullCommand = ui.readCommand();
                ui.printHorizontalLine();
                Command c = Parser.parse(fullCommand);
                c.execute(ui, storage, tasks);
                isExit = c.isExit();
            } catch (MonkeException e) {
                ui.print(e.getMessage());
            } finally {
                ui.printHorizontalLine();
            }
        }
    }

    /**
     * The main method to start the Monke chatbot application.
     *
     * @param args The command-line arguments, if any (not used in this program).
     */
    public static void main(String[] args) {
        new Monke("./data/tasks.txt").run();
    }
}
