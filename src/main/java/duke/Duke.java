package duke;

import duke.taskClasses.TaskList;

/**
 * The main entry point for the Duke application.
 * It initializes the necessary components and runs the application.
 */
public class Duke {
    private Storage storage;
    private TaskList tasks;
    private Ui ui;

    /**
     * Constructor for the Duke application.
     *
     * @param filePath The file path where tasks are saved and loaded.
     */
    public Duke(String filePath) {
        ui = new Ui();
        storage = new Storage(filePath);
        try {
            tasks = storage.load();
        } catch (Exception e) {
            ui.showLoadingError();
            tasks = new TaskList();
        }
    }

    /**
     * Runs the Duke application by invoking the parser.
     */
    public void run() {
        Parser.run(ui, storage, tasks);
    }

    /**
     * The main method to launch the Duke application.
     *
     * @param args Command line arguments (not used).
     */
    public static void main(String[] args) {
        new Duke("./data/tasks.txt").run();
    }
}
