package tasket;

import tasket.command.Command;
import tasket.data.TaskList;
import tasket.exception.TasketException;
import tasket.parser.Parser;
import tasket.storage.Storage;
import tasket.ui.Ui;

public class Tasket {
    private final Ui ui;
    private final Storage storage;
    private TaskList taskList;


    public Tasket(String filePath) {
        ui = new Ui();
        storage = new Storage(filePath);
        try {
            taskList = new TaskList(storage.load());
        } catch (TasketException e) {
            ui.showError(e.getMessage());
            taskList = new TaskList();
        }
    }

    public void run() {
        ui.showWelcome();
        boolean isExit = false;
        while (!isExit) {
            try {
                String fullCommand = ui.readCommand();
                Command c = Parser.parseInput(fullCommand);
                c.execute(taskList, ui, storage);
                isExit = c.isExit();
            } catch (TasketException e) {
                ui.showError(e.getMessage());
            }
        }
    }

    public static void main(String[] args) {
        new Tasket("data/tasks.txt").run();
    }
}
