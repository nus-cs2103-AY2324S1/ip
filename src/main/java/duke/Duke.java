package duke;

import java.io.IOException;

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
    public Duke() throws IOException {
        storage = new Storage("./data/duke.txt");
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
    public Duke(String filePath) throws IOException {
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
     * Runs Duke application with a given user input
     *
     * @param userInput The user's input
     * @return The String response for the given user's input
     */
    public String runInput(String userInput) {
        try {
            assert !tasks.equals(null) : "Tasks should not be null";
            String output = Parser.parseInput(userInput, this.tasks, this.ui);
            storage.saveData(this.tasks);
            return output;
        } catch (DukeException e) {
            return e.getMessage();
        } catch (IOException e) {
            return "File Not Found";
        }
    }

    /**
     * Runs CLI.
     * The main function of the Duke CLI application.
     *
     * @param args Command line arguments
     */
    public static void main(String[] args) throws IOException {
        new Duke("./data/duke.txt").run();
    }

}
