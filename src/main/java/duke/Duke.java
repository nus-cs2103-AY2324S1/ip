package duke;

import java.io.FileNotFoundException;

import duke.command.Command;
import duke.exception.DukeException;

/**
 * Represents the command line chat application Duke
 */
public class Duke {
    private TaskList tasks;
    private Ui ui;
    private Storage storage;

    /**
     * Constructor for Duke class.
     */
    public Duke() {
        this.ui = new Ui();
        this.storage = new Storage("./data/duke.txt");
        try {
            tasks = new TaskList(this.storage.readTasksFromFile());
        } catch (FileNotFoundException e) {
            tasks = new TaskList();
        }
    }

    /**
     * Constructs a Duke instance with the specified file path.
     * @param filePath File path which the Duke instance reads and writes from.
     */
    public Duke(String filePath) {
        this.ui = new Ui();
        this.storage = new Storage(filePath);
        try {
            this.tasks = new TaskList(this.storage.readTasksFromFile());
        } catch (FileNotFoundException e) {
            this.tasks = new TaskList();
        }

        assert this.ui != null : "ui of Duke instance should not be null";
        assert this.storage != null : "storage of Duke instance should not be null";
        assert this.tasks != null : "ui of Duke instance should not be null";

    }
    private void run() {
        Ui.greet();
        boolean isExit = false;
        while (!isExit) {
            try {
                String fullCommand = ui.readCommand();
                Command command = Parser.parse(fullCommand);
                command.execute(tasks, ui, storage);
                isExit = command.isExit();
            } catch (DukeException e) {
                Ui.showError(e);
            }
        }
    }

    String getResponse(String input) {
        try {
            Command command = Parser.parse(input);
            assert command != null : "command returned from parse should not be null";
            String response = command.execute(tasks, ui, storage);
            assert response != null : " response from command.execute should not be null";
            if (command.isExit()) {
                javafx.application.Platform.exit();
            }
            return response;
        } catch (DukeException e) {
            return Ui.showError(e);
        }
    }

    String getWelcomeMessage() {
        return Ui.greet();
    }
}



