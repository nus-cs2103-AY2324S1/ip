package duke;

import java.io.IOException;

import duke.command.Command;
import duke.parser.Parser;
import duke.storage.Storage;
import duke.tasklist.TaskList;
import duke.ui.Ui;

/**
 * The duke.Duke class represents a chatbot that manages tasks.
 * It is the main class that contains the methods to run the chatbot.
 */
public class Duke {
    private Storage storage;
    private TaskList tasks;
    private Ui ui;

    /**
     * Creates a duke.Duke instance that represents the chatbot.
     *
     * @param filePath The path to the file where tasks are saved and loaded from.
     */
    Duke(String filePath) {
        this.ui = new Ui();
        this.storage = new Storage(filePath);
        this.storage.createFileIfNotExists();
        try {
            this.tasks = new TaskList(this.storage.loadTasksFromFile());
        } catch (IOException e) {
            this.ui.showError("Unable to load saved task. Starting with a new empty task list");
            tasks = new TaskList();
        }
    }

    /**
     * Creates a duke.Duke instance that represents the chatbot without any parameters.
     */
    public Duke() {
        this.ui = new Ui();
        this.storage = new Storage("./data/duke.txt");
        this.storage.createFileIfNotExists();
        try {
            this.tasks = new TaskList(this.storage.loadTasksFromFile());
        } catch (IOException e) {
            this.ui.showError("Unable to load saved task. Starting with a new empty task list");
            tasks = new TaskList();
        }
    };

    /**
     * Runs the duke.Duke chatbot. It reads and executes commands from the user until the user exits.
     */
    public void run() {
        ui.showWelcome();
        boolean isExit = false;

        while (!isExit) {
            try {
                String fullCommand = ui.readCommand();
                Command command = Parser.parse(fullCommand);
                command.execute(tasks, ui, storage);
                isExit = command.isExit();
            } catch (Exception e) {
                ui.showError(e.getMessage());
            }
        }
    }

    /**
     * You should have your own function to generate a response to user input.
     * Replace this stub with your completed method.
     */
    public String getResponse(String input) {
        Command command = Parser.parse(input);
        String output = command.execute(tasks, ui, storage);
        return output;
    }


    public static void main(String[] args) {
        new Duke("./data/duke.txt").run();
    }

}
