package duke;

import exceptions.JamesBondException;
import parser.Parser;
import storage.Storage;
import tasks.TaskList;
import ui.Ui;
import commands.*;

public class Duke {

    private Storage storage;
    private TaskList tasks;
    private Ui ui;
    
    public Duke(String filePath) {
        ui = new Ui();
        storage = new Storage(filePath);
        try {
            TaskList tasks = new TaskList();
            tasks = storage.loadTasksFromFile();
            this.tasks = tasks;
        } catch (JamesBondException e) {
            ui.showLoadingError();
            tasks = new TaskList();
        }
    }

    public void run() {
        ui.showWelcomeMessage();
        while (ui.isThereNext()) {
            String input = ui.readLine();
            Command c = Parser.parseCommand(input);
            c.runCommand(tasks, ui, storage);
        }
    }

    public static void main(String[] args) {
        new Duke("/Users/jamesbond/ip/src/main/data/jamesbond.txt").run();
    }


}
