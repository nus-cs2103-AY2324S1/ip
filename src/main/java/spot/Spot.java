package spot;

import spot.command.Command;
import spot.exception.SpotException;

public class Spot {

    private Ui ui;
    private Storage storage;
    private TaskList tasks;

    public Spot() {
        try {
            ui = new Ui();
            storage = new Storage();
            tasks = new TaskList(storage.loadTasks());
        } catch (SpotException e) {
            tasks = new TaskList();
        }
    }

    public void run() {
        ui.sayHello();
        boolean isExit = false;
        while (!isExit) {
            try {
                String fullCommand = ui.readCommand();
                Command c = Parser.parse(fullCommand);
                c.execute(tasks, ui, storage);
                isExit = c.isExit();
            } catch (SpotException e) {
                ui.showError(e.getMessage());
            }
        }
    }

    public static void main(String[] args) {
        new Spot().run();
    }
}
