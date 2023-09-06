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
     * Gets response from Monke chatbot based on user input.
     *
     * @param input The user input.
     * @return The response given by Monke.
     */
    public String getResponse(String input) {
        try {
            Command c = Parser.parse(input);
            return c.execute(ui, storage, tasks);
        } catch (MonkeException e) {
            return e.getMessage();
        }
    }
}
