import bongo.command.Command;
import bongo.helper.BongoException;
import bongo.helper.Parser;
import bongo.helper.Storage;
import bongo.helper.Ui;
import bongo.task.TaskList;

import java.io.*;

public class Bongo {
    private Storage storage;
    private TaskList tasks;
    private Ui ui;
    private Parser parser;

    public Bongo(String filepath) {
        this.ui = new Ui();
        this.storage = new Storage(filepath);
        this.parser = new Parser();
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
                Command c = this.parser.parse(command);
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

