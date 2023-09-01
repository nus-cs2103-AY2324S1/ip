package duke;

import duke.commands.Command;
import duke.exceptions.DukeException;
import duke.parser.Parser;
import duke.storage.Storage;
import duke.tasks.Tasks;
import duke.ui.Ui;

/**
 * Entry point of the Duke application.
 * Initializes the application and starts the interaction with the user.
 */
public class Duke {
    private final Storage storage;
    private final Tasks tasks;
    private final Ui ui;

    /**
     * Public constructor for Duke.
     *
     * <p>Initialises data structures used by the application.</p>
     */
    public Duke() {
        this.ui = new Ui();
        this.storage = new Storage("./data/duke.txt");
        tasks = storage.load();
    }

    /**
     * Runs the program and starts to listen to user input.
     */
    public void run() {
        ui.showGreetings();

        while (true) {
            try {
                String text = ui.readCommand();
                Command c = Parser.parse(text, false);

                // Empty or unknown command
                if (c == null) {
                    ui.showUnknownCommand();
                    continue;
                }

                // Execute command
                c.execute(this.tasks, this.ui, this.storage, false);

                // Check if is duke.commands.ExitCommand
                if (c.isExit()) {
                    break;
                }
            } catch (DukeException ex) {
                ui.showError(ex.getMessage());
            }

        }
    }

    /**
     * Main method of the program, entry point of the application.
     *
     * @param args arguments supplied by the user at program launch
     */
    public static void main(String[] args) {
        new Duke().run();
    }
}
