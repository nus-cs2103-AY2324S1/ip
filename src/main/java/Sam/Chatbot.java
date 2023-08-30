package sam;

import sam.commands.Command;
import sam.services.Storage;
import sam.services.TaskList;
import sam.services.Ui;
import sam.services.parser.Parser;

/**
 * Represents a Chatbot.
 */
public class Chatbot {
    private TaskList taskList;
    private Storage storage;
    private Ui ui;
    private Parser parser;
    private static final String FILE_PATH = "./data/duke.txt";

    public Chatbot() {
        this.taskList = new TaskList();
        this.storage = new Storage(FILE_PATH);
        this.ui = new Ui();
        this.parser = new Parser();
    }

    public void run() {
        try {
            storage.loadTasksFromFile(this.taskList);
        } catch (Exception e) {
            ui.showError(e.getMessage());
        }

        ui.showWelcome();
        boolean isExit = false;
        while (!isExit) {
            String fullCommand = ui.readCommand();
            Command c = parser.parseCommand(fullCommand);
            c.execute(taskList, ui, storage);
            isExit = c.isExit();
        }
    }
}
