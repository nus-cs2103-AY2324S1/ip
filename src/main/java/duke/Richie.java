package duke;

import duke.command.Command;
import duke.command.ExitCommand;


public class Richie {

    private Storage storage;
    private TaskList tasks;
    private Ui ui;

    public Richie(String filePath) {
        ui = new Ui();
        storage = new Storage(filePath);
        try {
            tasks = new TaskList(storage.load());
        } catch (RichieException e) {
            ui.showLoadingError();
            tasks = new TaskList();
        }
    }

    public void run() {
        ui.showWelcome();
        boolean isExit = false;
        while (!isExit) {
            try {
                String userInput = ui.scanUserInput();
                Command command = Parser.parse(userInput);
                command.execute(this.ui, this.storage, this.tasks);
                if (command instanceof ExitCommand) {
                    isExit = true;
                }

            } catch (RichieException e) {
                ui.showMessage(e.getMessage());
            }
        }
    }

    public static void main(String[] args) {
        new Richie("src/data.txt").run();
    }
}

