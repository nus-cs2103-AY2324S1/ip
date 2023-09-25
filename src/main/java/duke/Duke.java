package duke;

import java.io.FileNotFoundException;

public class Duke {
    private Storage storage;
    private TaskList taskList;
    private Ui ui;
    private static final String FILE_PATH = "./data/duke.txt";

    public Duke() {
        ui = new Ui();
        storage = new Storage(FILE_PATH);
        try {
            taskList = new TaskList(storage.loadPreviousTasks());
        } catch (FileNotFoundException e) {
            ui.displayLoadErrorMessage(e.getMessage());
            taskList = new TaskList();
        }
    }

    public String getResponse(String input) {

        try {
            String response = Parser.userCommand(input, taskList, ui, storage);
            return response;
        } catch (DukeException e) {
            String response = ui.displayErrorMessage(e.getMessage());
            return response;
        }
    }
}



