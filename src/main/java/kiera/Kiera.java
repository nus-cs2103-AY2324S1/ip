package kiera;

import kiera.command.Command;
import kiera.exception.KieraException;

public class Kiera {

    protected static String filePath = "./data/storage.txt";
    private Storage storage;
    private Ui ui;
    private TaskList tasks;

    public Kiera(String filePath) {

        try {
            ui = new Ui();
            storage = new Storage(filePath);
            tasks = new TaskList(storage.load());
        } catch (KieraException e) {
            ui.showLoadingError();
            tasks = new TaskList();
        }

    }

    public void run() {
        ui.showHello();
        boolean isExit = false;
        while (!isExit) {
            try {
                String fullCommand = ui.readCommand();
                ui.showLine();
                Command c = Parser.parse(fullCommand);
                c.execute(tasks, ui, storage);
                isExit = c.isExit();
            } catch (KieraException e) {
                ui.showError(e.toString());
            } finally {
                ui.showLine();
            }
        }
    }
    public static void main(String[] args) {
            new Kiera(filePath).run();
    }
}
