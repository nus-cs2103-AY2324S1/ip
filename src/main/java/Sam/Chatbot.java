package sam;

import sam.commands.Command;
import sam.services.Storage;
import sam.services.TaskList;
import sam.services.UI;
import sam.services.parser.Parser;

import java.io.IOException;

/**
 * Represents a Chatbot.
 */
public class Chatbot {
    private TaskList taskList;
    private Storage storage;
    private UI ui;
    private Parser parser;
    private static final String FILE_PATH = "./data/duke.txt";

    public Chatbot() {
        this.taskList = new TaskList();
        this.storage = new Storage(FILE_PATH);
        this.ui = new UI();
        this.parser = new Parser();
    }

    public void run() {
        try {
            storage.loadTasksFromFile(this.taskList);
        } catch (IOException e) {
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
