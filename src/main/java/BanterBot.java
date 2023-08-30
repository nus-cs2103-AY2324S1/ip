import command.Command;

import exception.DukeException;
import helper.Parser;
import helper.Storage;
import helper.Ui;
import task.TaskList;

public class BanterBot {
    private Storage storage;
    private TaskList tasks;
    private Ui ui;

    public BanterBot(String filePath) {
        ui = new Ui();
        storage = new Storage(filePath);
        tasks = new TaskList(storage.load());
    }

    public void run() {
        ui.showWelcome();
        boolean isExit = false;
        while (!isExit) {
            try {
                String fullCommand = ui.readCommand();
                Command c = Parser.parse(fullCommand);
                c.execute(tasks, ui, storage);
                isExit = c.isExit();
            } catch (DukeException e) {
                ui.print(e.getMessage());
            }
        }
    }

    public static void main(String[] args) {
        new BanterBot("data/tasks.txt").run();
    }
}