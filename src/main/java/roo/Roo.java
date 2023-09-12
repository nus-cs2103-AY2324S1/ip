package roo;

import roo.commands.Command;

/**
 * The main class for the Roo application, which is a tasks management program.
 */
public class Roo {

    private Storage storage;
    private TaskList tasks;
    private Ui ui;

    /**
     * Constructs a Roo object with the specified file path.
     * @param filePath The path to the file where tasks are stored.
     */
    public Roo(String filePath) {
        assert filePath != null : "File path cannot be null";
        this.storage = new Storage(filePath);
        this.tasks = new TaskList(storage);
        this.ui = new Ui(tasks);
        this.storage.initialise(tasks);
    }

    /**
     * Starts the Roo application. Initializes the task list, greets the user, and handles user commands.
     */
    public String run(String input) {
        Command c = Parse.parse(input);
        return c.execute(this.ui, this.tasks);
    }

    /**
     * You should have your own function to generate a response to user input.
     * Replace this stub with your completed method.
     */
    public String getResponse(String input) {
        assert input != null : "Input cannot be null";
        return "Roo: \n" + run(input);
    }

    public static void main(String[] args) {
        Roo roo = new Roo("roo.txt");
        roo.run("list");
    }

}

