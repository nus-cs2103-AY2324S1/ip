package duke;

import duke.commands.Command;
import duke.exceptions.DukeException;
import duke.parser.Parser;
import duke.storage.Storage;
import duke.tasks.TaskList;
import duke.ui.Ui;

/**
 * The duke.Duke class contains the code for interacting
 * with Jarvis, a task manager bot.
 *
 * @author: Shishir
 **/
public class Duke {
    /** Contains the list of all tasks. **/
    private TaskList tasks;

    /** Contains the stored data for duke.Duke Class. **/
    private Storage storage;

    /** Contains the UI for duke.Duke Class **/
    private Ui ui;

    /** Constructor for duke.Duke Class. **/
    public Duke(String filePath) {
        this.storage = new Storage(filePath);
        this.tasks = new TaskList(this.storage.readData());
        this.ui = new Ui();
    }

    /** The function where user interacts with Jarvis using Scanner. **/
    public void interact() {
        this.ui.greet();
        boolean isExit = false;
        while (!isExit) {
            try {
                String command = ui.readCommand();
                Command c = Parser.parse(command);
                c.execute(this.tasks, this.ui, this.storage);
                isExit = c.isExit();
            } catch (DukeException exc) {
                this.ui.showLine();
                System.out.println(exc.getMessage());
                this.ui.showLine();
            }
        }
    }

    /** The main function where Jarvis is initialised.
     * @param args Input args.
     **/
    public static void main(String[] args) {
        Duke bot = new Duke("data/tasks.txt");
        bot.interact();
    }
}
