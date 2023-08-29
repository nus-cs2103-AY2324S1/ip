package duke;

import duke.exception.DukeException;
import java.time.format.DateTimeParseException;

/**
 * A class that creates a chatbot.
 */
public class Duke {
    /** A list to keep track of the tasks. */
    private TaskList tasks;
    private Storage storage;
    private Ui ui;
    private Parser parser;

    public Duke(String filepath) {
        this.ui = new Ui();
        try {
            this.storage = new Storage(filepath);
            this.tasks = new TaskList(storage.load());
        } catch (DukeException e) {
            this.ui.print(e.getMessage());
            this.tasks = new TaskList();
        }
        this.parser = new Parser(this.tasks, this.ui);
    }

    public void run() {
        ui.welcome();
        boolean isExit;
        String command = ui.readCommand();

        while (!command.isEmpty()) {
            try {
                parser.parse(command);
                isExit = parser.isExit();
                storage.store(tasks.toStringInFile());
                if (isExit) {
                    break;
                } else {
                    command = ui.readCommand();
                }
            } catch (DukeException e) {
                ui.print(e.getMessage());
                command = ui.readCommand();
            } catch (DateTimeParseException f) {
                ui.print("Please follow this format: YYYY-MM-DD or YYYY-MM-DD HH:mm");
                command = ui.readCommand();
            }
        }
    }

    public static void main(String[] args) {
        new Duke("./duke.txt").run();
    }
}
