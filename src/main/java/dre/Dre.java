package dre;

import dre.exception.DreException;
import dre.ui.Ui;
import dre.storage.Storage;
import dre.parser.Parser;
import dre.task.TaskList;
import dre.command.Command;

public class Dre {

    private Storage storage;
    private TaskList tasks;
    private Ui ui;

    public Dre(String filePath) {
        ui = new Ui();
        storage = new Storage(filePath);
        try {
            tasks = new TaskList(storage.load());
        } catch (Exception e) {
            ui.showLoadingError();
            tasks = new TaskList();
            System.out.println(e);
        }
    }

    public void run() {
        ui.showWelcome();
        boolean isExit = false;
        while (!isExit) {
            try {
                String fullCommand = ui.readCommand();
                ui.showLine();
                Command c = Parser.parse(fullCommand);
                c.execute(tasks, ui, storage);
                isExit = c.isExit();
            } catch (DreException e) {
                ui.showError(e.getMessage());
            } finally {
                ui.showLine();
            }
        }
    }

    public static void main(String[] args) {
        new Dre("data/dre.txt").run();
    }
}