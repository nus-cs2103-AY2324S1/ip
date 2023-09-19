package duke;

import duke.exception.DukeException;

/**
 * duke.Duke is a class in-charge of task management.
 * It allows users to add, delete, mark, unmark, specify, and list tasks.
 */
public class Duke {

    private Storage storage;
    private TaskList tasks;
    private Ui ui;

    /**
     * Constructor for duke.Duke class with no parameters
     */
    public Duke() {
        storage = new Storage("./src/main/java/data/duke.txt");
        ui = new Ui();
        try {
            tasks = new TaskList(storage.load());
        } catch (DukeException e) {
            tasks = new TaskList();
        }
    }

    /**
     * Constructor for duke.Duke class
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
        boolean isDone = true;
        while (isDone) {
            String userInput = ui.readCommand();
            try {
                assert !tasks.equals(null) : "TasksList should be initialised";
                isDone = Parser.parseCommand(userInput, this.tasks, this.ui);
            } catch (DukeException e) {
                ui.showMessage(e.getMessage());
            }
        }
    }

    /**
     * Run Duke application with a given user input
     *
     * @param userInput The user's input
     * @return The String response for the given user's input
     */
    public String runInput(String userInput) {
        try {
            assert !tasks.equals(null) : "Tasks should not be null";
            return Parser.parseInput(userInput, this.tasks, this.ui);
        } catch (DukeException e) {
            return e.getMessage();
        }
    }

    /**
     * The main function of the Duke application.
     *
     * @param args Command line arguments
     */
    public static void main(String[] args) {
        new Duke("./src/main/java/data/duke.txt").run();
    }

}
