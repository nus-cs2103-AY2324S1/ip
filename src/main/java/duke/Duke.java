package duke;

import duke.commands.Command;
import duke.exceptions.DukeException;
import duke.parser.Parser;
import duke.storage.Storage;
import duke.tasks.Tasks;
import duke.ui.Ui;

public class Duke {
    private final String FILE_PATH = "./data/duke.txt";
    private Storage storage;
    private Tasks tasks;
    private Ui ui;

    public Duke() {
        this.ui = new Ui();
        this.storage = new Storage(FILE_PATH);
        tasks = storage.load();
    }

    public void run() {
        ui.printGreetings();

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
    public static void main(String[] args) {
        new Duke().run();
    }
}
