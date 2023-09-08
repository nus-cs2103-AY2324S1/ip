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
            tasks = new TaskList(this.storage.readTasksFromFile());
        } catch (FileNotFoundException e) {
            tasks = new TaskList();
        }
    }
    private void run() {
        Ui.greet();
        boolean isExit = false;
        while (!isExit) {
            try {
                String fullCommand = ui.readCommand();
                Command c = Parser.parse(fullCommand);
                c.execute(tasks, ui, storage);
                isExit = c.isExit();
            } catch (DukeException e) {
                Ui.showError(e);
            }
        }
    }

    String getResponse(String input) {
        try {
            Command command = Parser.parse(input);
            String response = command.execute(tasks, ui, storage);
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



