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
        } catch (DukeException e) {
            System.out.println(e);
            System.exit(0);
        }
    }

    public void setUi(UI ui) {
        this.ui = ui;
    }

    public void getResponse(String rawUserInput) {
        try {
            Command c = Parser.parseCommand(rawUserInput);
            c.execute(taskList, ui, storage);
        } catch (DukeException e) {
            ui.sendError(e.getMessage());
        }
    }

}
