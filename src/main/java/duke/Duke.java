package duke;

import duke.exception.DukeException;
import java.time.format.DateTimeParseException;

/**
 * A chatbot that interacts with users.
 */
public class Duke {
    /** A list to keep track of the tasks. */
    private TaskList tasks;

    /** The Storage that the Duke uses. */
    private Storage storage;

    /** The Ui that the Duke uses. */
    private Ui ui;

    /** The Parser that the Duke uses. */
    private Parser parser;

    /**
     * Constructs a new Duke that deals with the file at the corresponding path.
     *
     * @param filepath Path to the file.
     */
    public Duke(String filepath) {
        this.ui = new Ui();
        try {
            this.storage = new Storage(filepath);
            this.tasks = new TaskList(storage.loadFromFile());
        } catch (DukeException e) {
            this.ui.print(e.getMessage());
            this.tasks = new TaskList();
        }
        this.parser = new Parser(this.tasks, this.ui);
    }

    /**
     * Runs the chatbot.
     */
    public void run() {
        ui.sayHi();
        boolean isExit;
        String command = ui.readCommand();

        while (!command.isEmpty()) {
            try {
                parser.parse(command);
                isExit = parser.isExit();
                storage.storeToFile(tasks.toStringInFile());
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
