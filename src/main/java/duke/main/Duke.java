package duke.main;

import duke.command.Command;
import duke.exception.DukeException;

public class Duke {
    private Storage storage;
    private TaskList taskList;
    private Ui ui;
    private static final String DATA_FILE_PATH = "./data/duke.txt";
    public static void main(String[] args) throws DukeException {
        new Duke(DATA_FILE_PATH).executeDuke();
    }

    private Duke(String filePath) throws DukeException {
        storage = new Storage(filePath);
        ui = new Ui();

        try {
            taskList = new TaskList(storage.loadTasksFromFile());
        } catch (DukeException e) {
            taskList = new TaskList();
            throw new DukeException("An error occurred during file loading.");
        }
    }
    private void executeDuke() {
        ui.showWelcome();
        boolean isExit = false;
        while (!isExit) {
            try {
                String fullCommand = ui.readCommand();
                ui.showLine(); // show the divider line ("_______")
                Command c = Parser.parse(fullCommand);
                c.execute(taskList, ui, storage);
                isExit = c.isExit();
            } catch (DukeException e) {
                ui.showError(e.getMessage());
            } finally {
                ui.showLine();
            }
        }
    }


}

