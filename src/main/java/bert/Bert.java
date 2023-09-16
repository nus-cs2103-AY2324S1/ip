package bert;

import bert.commands.Command;
import bert.exceptions.BertException;
import bert.parser.Parser;
import bert.storage.Storage;
import bert.tasks.TaskList;
import bert.ui.Ui;

import java.io.FileNotFoundException;

/**
 * A chatbot named Bert that interacts with the user and keeps track of a task list.
 */
public class Bert {
    private Storage storage;
    private TaskList tasks;
    private Ui ui;

    /**
     * Constructs an instance of the chatbot and loads the file at the specified filePath
     * into the chatbot's task list.
     *
     * @param filePath the file path of the file to read the list of tasks from
     */
    public Bert(String filePath) {
        ui = new Ui();
        storage = new Storage(filePath);
        try {
            tasks = new TaskList(storage.load());
        } catch (FileNotFoundException e) {
            ui.showLoadingError();
            tasks = new TaskList();
        }
    }

    /**
     * Runs the chatbot. While it is running, the user input is read and its associated command is executed.
     * When the user inputs 'bye', the chatbot exits and the task list is saved.
     */
    public void run() {
        ui.showWelcome();
        boolean isExit = false;
        while (!isExit) {
            try {
                String fullCommand = ui.readCommand();
                Command c = new Parser().parse(fullCommand);
                c.execute(tasks, ui, storage);
                isExit = c.isExit();
            } catch (BertException e) {
                ui.showError(e.getMessage());
            }
        }
        storage.save(tasks);
    }

    /**
     * Creates a chatbot instance with a task list loaded from "./data/tasks.txt", and runs it.
     *
     * @param args command line arguments which are not used
     */
    public static void main(String[] args) {
        new Bert("data/tasks.txt").run();
    }
}
