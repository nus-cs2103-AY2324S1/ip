package duke;

import duke.command.Command;
import duke.exception.DukeException;

/**
 * Represents a chatbot that helps users to keep track of their tasks.
 */
public class Duke {

    private Ui ui;
    private Storage storage;
    private TaskList taskList;
    private static final String FOLD_PATH = "./data";
    private static final String FILE_NAME = "duke.txt";

    public Duke() {
    }

    public String initialize() {
        this.storage = new Storage(FOLD_PATH, FILE_NAME);
        this.ui = new Ui();
        try {
            this.taskList = new TaskList(storage.load());
            return ui.showWelcome();
        } catch (DukeException e) {
            storage.createFile();
            taskList = new TaskList();
            return ui.showLoadingError();
        }
    }

    /**
     * You should have your own function to generate a response to user input.
     * Replace this stub with your completed method.
     */
    public String getResponse(String input) {
        try {
            Command c = Parser.parse(input);
            String response = c.execute(taskList, ui, storage);
            return response;
            //isExit = c.isExit();
        } catch (DukeException e) {
            return ui.showError(e.getMessage());
        }
    }
}
