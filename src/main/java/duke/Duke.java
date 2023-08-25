package duke;

import duke.command.Command;
import duke.command.Parser;
import duke.task.TaskList;
import duke.ui.UI;


/**
 * The main duke class.
 */
public class Duke {

    private TaskList taskList;
    private UI ui;
    private Storage storage;

    /**
     * Constructor for Duke class.
     *
     * @param savePath string of path to the savefile
     */
    public Duke(String savePath) {
        try {
            this.storage = new Storage(savePath);
            this.taskList = this.storage.load();
            this.ui = new UI("Heimdallr");
        } catch (DukeException e) {
            UI.sendError(e.getMessage());
            System.exit(0);
        }
    }

    /**
     * Runs the duke program.
     * Spins up the program until exit command is given by the user
     */
    public void run() {
        boolean isExit = false;
        while (!isExit) {
            try {
                String rawCommand = UI.readMessage();
                Command c = Parser.parseCommand(rawCommand);
                c.execute(this.taskList, this.ui, this.storage);
                isExit = c.isExit();
            } catch (DukeException e) {
                UI.sendError(e.getMessage());
            }

        }
    }

    public static void main(String[] args) {
        new Duke("data/duke.txt").run();
    }


}
