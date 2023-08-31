package bongo;

import bongo.command.Command;
import bongo.helper.BongoException;
import bongo.helper.Parser;
import bongo.helper.Storage;
import bongo.helper.Ui;
import bongo.task.TaskList;

import java.io.*;

public class Bongo {
    private final Storage storage;
    private final Ui ui;
    private TaskList tasks;

    public Bongo(String filepath) {
        this.ui = new Ui();
        this.storage = new Storage(filepath);
        try {
            this.tasks = new TaskList(storage.load());
        } catch (FileNotFoundException e) {
            ui.showLoadingError();
            storage.checkIfFilesExist();
            this.tasks = new TaskList();
        } catch (BongoException e) {
            ui.showError(e.getMessage());
        }
    }

    public void run() {
        ui.showWelcome();
        boolean isExit = false;
        while (!isExit) {
            try {
                String command = ui.readCommand();
                ui.showLine();
                Command c = Parser.parse(command);
                c.execute(this.tasks, this.ui, this.storage);
                isExit = c.isExit();
            } catch (BongoException e) {
                ui.showError(e.getMessage());
            } finally {
                ui.showLine();
            }
        }
    }

    public static void main(String[] args) {
        Bongo bongo = new Bongo("data/bongo.txt");
        bongo.run();
    }
}

