package duke;

import java.io.FileNotFoundException;

/**
 * Duke class to receive the response from the program.
 */
public class Duke {
    private Storage storage;
    private TaskList taskList;
    private Ui ui;


    /**
     * Initialises a new Duke instance.
     */
    public Duke() {
        ui = new Ui();
        storage = new Storage();
        try {
            taskList = new TaskList(storage.loadPreviousTasks());
        } catch (FileNotFoundException e) {
            ui.displayLoadErrorMessage(e.getMessage());
            taskList = new TaskList();
        }
        assert taskList != null : "taskList should not be null.";
    }

    /**
     * Returns the response of Sivraj to the user-input.
     *
     * @param input Input given by the user.
     * @return String value to be displayed on the chat screen.
     */
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


