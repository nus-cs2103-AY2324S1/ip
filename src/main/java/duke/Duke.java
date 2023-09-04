package duke;

import duke.command.Command;
import duke.exception.DukeException;
import duke.object.TaskList;
import duke.parser.Parser;
import duke.storage.Storage;
import duke.ui.Ui;
import duke.ui.VerboseUi;

/**
 * The main chatbot.
 */
public class Duke {

    private Storage storage;
    private TaskList tasks;
    private Ui ui;

    /**
     * Constructor for Duke.
     *
     * @param name Name of the bot.
     */
    public Duke(String name) {
        this.ui = new VerboseUi(name);
        this.storage = new Storage(this.ui);
        this.tasks = this.storage.loadTasks();
    }

    /**
     * Runs the chatbot, receiving commands and terminating only on bye.
     */
    public void run() {
        ui.greet();
        boolean isExit = false;
        while (!isExit) {
            try {
                Command cmd = Parser.parse(ui.readCommand());
                cmd.execute(tasks, ui, storage);
                isExit = cmd.isExit();
            } catch (DukeException e) {
                ui.print(e.getMessage());
            }
        }
    }

    public static void main(String[] args) {
        Duke bot = new Duke("chatBot");
        bot.run();
    }

}
