package duke.main;

import java.io.IOException;

import duke.commands.Command;
import duke.exception.DukeException;
import duke.ui.Ui;
import duke.utilities.Parser;
import duke.utilities.Storage;
import duke.utilities.TaskList;

/**
 * Main ChatBot class.
 */
public class Duke {
    private Storage dataBase;
    private TaskList taskList;
    private Ui ui;
    private String filePath = "./tasklist.txt";

    /**
     * ChatBot constructor.
     */
    public Duke() {
        try {
            ui = new Ui();
            this.dataBase = new Storage(filePath);
            this.taskList = dataBase.load();
        } catch (IOException e) {
            this.taskList = new TaskList();
        }
    }

    public String getResponse(String input) {
        try {
            Command cmd = Parser.parse(input, taskList, dataBase, ui);
            return cmd.execute();
        } catch (DukeException e) {
            return ui.printError(e.getMessage());
        }
    }
}
