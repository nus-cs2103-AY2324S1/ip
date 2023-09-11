package dude;

import dude.command.Command;

import java.io.FileNotFoundException;
import java.io.IOException;

public class Dude {

    private TaskList taskList;
    private Storage storage;
    private Ui ui;
    static int nTasks = 0;

    public Dude(String filePath) {
        this.ui = new Ui();
        this.storage = new Storage(filePath);
        try {
            taskList = new TaskList(storage.loadTasksFromDisk());
        } catch (FileNotFoundException e) { // DudeException
            ui.showLoadingError();
            taskList = new TaskList();
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
                c.execute(taskList, ui, storage);
                isExit = c.isExit();
            } finally {
                ui.showLine();
            }
        }
    }

    public static void main(String[] args) throws IOException {
        new Dude("data/dude.txt").run();
    }
}