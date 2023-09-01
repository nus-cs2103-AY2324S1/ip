package noac;

import noac.command.Command;

public class Noac {

    private Storage storage;
    private TaskList tasks;
    private Ui ui;

    public Noac(String filePath) {
        ui = new Ui();
        storage = new Storage(filePath);
        try {
            tasks = new TaskList(storage.load());
        } catch (NoacException e) {
            ui.showErrorMessage(e);
            tasks = new TaskList();
        }
    }

    public void run() {
        ui.showWelcomeMessage();
        boolean isExit = false;
        while (!isExit) {
            try {
                String fullCommand = ui.readCommand();
                Command c = Parser.parse(fullCommand);
                c.execute(tasks, ui, storage);
                isExit = c.isExit();
            } catch (NoacException e) {
                ui.showErrorMessage(e);
            }
        }
    }

    public static void main(String[] args) {
        new Noac("data/tasks.txt").run();
    }

}



