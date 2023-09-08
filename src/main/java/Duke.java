import command.Command;
import parser.Parser;
import storage.Storage;
import tasklist.TaskList;
import ui.Ui;

import java.io.*;

/**
 * The Duke class represents a chatbot that manages tasks.
 * It is the main class that contains the methods to run the chatbot.
 */
public class Duke {

    private Storage storage;
    private TaskList tasks;
    private Ui ui;

    /**
     * Creates a Duke instance that represents the chatbot.
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
     * Runs the Duke chatbot. It reads and executes commands from the user until the user exits.
     */
    public void run() {
        ui.showWelcome();
        boolean isExit = false;
        while (!isExit) {
            try {
                String fullCommand = ui.readCommand();
                Command c = Parser.parse(fullCommand);
                c.execute(tasks, ui, storage);
                isExit = c.isExit();
            } catch (Exception e) {
                ui.showError(e.getMessage());
            }
        }
    }

    public static void main(String[] args) {
        new Duke("./data/duke.txt").run();
    }

}
