package Ally;

import Ally.Commands.Commands;

import java.util.Scanner;

public class Ally {

    private Storage storage;
    private AllyList tasks;
    private Ui ui;

    public Ally(String filePath) throws AllyException {
        ui = new Ui();
        storage = new Storage(filePath);
        try {
            tasks = new AllyList(storage.load());
        } catch (AllyException e) {
            ui.showLoadingError();
        }
    }

    public void run() {
        ui.start();
        boolean isExit = false;
        while (!isExit) {
            try {
                String fullCommand = ui.readCommand();
                ui.showLine(); // show the divider line ("_______")
                Commands c = Parser.parse(fullCommand);
                c.run(tasks, ui, storage);
                isExit = c.isExit();
            } catch (AllyException e) {
                ui.showError(e.getMessage());
            } finally {
                ui.showLine();
            }
        }
    }

    public static void main(String[] args) throws AllyException {
        new Ally("./data/saved.txt").run();
    }


}

