package duke;

import duke.command.Command;
import duke.command.CommandParser;

public class Duke {

    private final Storage storage;
    private TaskList tasks;
    private final Ui ui;

    String fileName = "duke.Duke.txt";
    String[] directories = {"data"};

    public Duke() {
        ui = new Ui();
        storage = new Storage(fileName, directories);
        try {
            tasks = new TaskList(storage.load());
        } catch (CreateNewSaveException | NewSaveFailedException e) {
            ui.showLoadingError();
            tasks = new TaskList();
        }
    }

    public void run() {
        ui.showWelcome();
        boolean isExit = false;
        while (!isExit) {
            try {
                String fullCommand = ui.readCommand();
                ui.showSoftLine(); // show the divider line ("_______")
                Command c = CommandParser.parse(fullCommand);
                c.execute(tasks, ui, storage);
                isExit = c.isExit();
            } catch (DukeException e) {
                ui.showError(e.getMessage());
            } finally {
                if (!isExit) {
                    ui.showHardLine();
                }
            }
        }
        ui.showGoodbye();
    }

    public static void main(String[] args) {
        new Duke().run();
    }

}
