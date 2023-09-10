package roo;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

import roo.commands.*;
import roo.task.Deadline;
import roo.task.Event;
import roo.task.Todo;

/**
 * The main class for the Roo application, which is a tasks management program.
 */
public class Roo {

    private Storage storage;
    private TaskList tasks;
    private Ui ui;

    public Roo() {
    }

    /**
     * Constructs a Roo object with the specified file path.
     * @param filePath The path to the file where tasks are stored.
     */
    public Roo(String filePath) {
        this.storage = new Storage(filePath);
        this.tasks = new TaskList(storage);
        this.ui = new Ui(tasks);
        this.storage.initialise(tasks);
    }

    /**
     * Starts the Roo application. Initializes the task list, greets the user, and handles user commands.
     */
    public String run(String input) {

        Commands c;
        c = Parse.parse(input);
        return c.execute(this.ui, this.tasks);
    }



    /**
     * You should have your own function to generate a response to user input.
     * Replace this stub with your completed method.
     */
    public String getResponse(String input) {
        return "Roo: \n" + run(input);
    }

    public static void main(String[] args) {
        Roo roo = new Roo("roo.txt");
        roo.run("list");
    }

}

