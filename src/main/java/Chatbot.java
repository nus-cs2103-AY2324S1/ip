import commands.Command;
import services.Storage;
import services.TaskList;
import services.UI;
import services.parser.Parser;

import java.io.IOException;

public class Chatbot {
    private TaskList taskList;
    private Storage storage;
    private UI ui;
    private Parser parser;

    public Chatbot() {
        this.taskList = new TaskList();
        this.storage = new Storage();
        this.ui = new UI();
        this.parser = new Parser();
    }

    public void run() {
        try {
            storage.loadTasksFromFile(taskList);
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
