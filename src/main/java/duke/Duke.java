package duke;

import command.Command;

import dukeutilities.Parser;

import exceptions.DukeException;

import java.time.format.DateTimeParseException;

/**
 * The Duke class initializes the Duke application and manages its execution.
 * It handles user commands, interacts with the file 'duke.txt', and maintains the task list.
 */
public class Duke {

    private Storage storage;
    private TaskList tasks;
    private Ui ui;

    /**
     * Duke constructor initializes all required fields such as Ui, Storage and TaskList
     */
    public Duke() {
        ui = new Ui();
        storage = new Storage("duke.txt", "data");
        tasks = TaskList.readFromFile("data/duke.txt");
    }

    /**
     * Runs the Duke application, handling user input and executing commands.
     */
    public void run() {
        ui = new Ui();
        storage = new Storage("duke.txt", "data");
        tasks = new TaskList();
    }

    /**
     * Dukey's chatbot logic, generates a response based on input
     *
     * @param String input
     */
    public String getResponse(String input) {
        if (input.equals("sexy time")) {
            return "Oh Johnny boy is always ready for sexy times.";
        }
        try {
            Command c = Parser.parse(input);
            return c.execute(tasks, storage, ui);
        } catch (DukeException e) {
            return e.getMessage();
        } catch (DateTimeParseException e) {
            return "Incorrect format input";
        }
    }

    /**
     * Entry point of the Duke application.
     * Creates a new instance of Duke and starts the application.
     *
     * @param args Command-line arguments (unused in this context).
     */
    public static void main(String[] args) {
        new Duke().run();
    }
}






