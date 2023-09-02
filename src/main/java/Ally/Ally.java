package Ally;

import Ally.Commands.Commands;
import Ally.Exceptions.AllyException;
import Ally.Tasks.AllyList;

public class Ally {

    private final Storage storage;
    private AllyList tasks;
    private final Ui ui;

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
                Ui.showLine(); // show the divider line ("_______")
                Commands c = Parser.parse(fullCommand);
                c.run(tasks, ui, storage);
                isExit = c.isExit();
            } catch (AllyException e) {
                ui.showError(e.getMessage());
            } finally {
                Ui.showLine();
            }
        }
    }

    public static void main(String[] args) throws AllyException {
        new Ally("./data/saved.txt").run();
    }


}

