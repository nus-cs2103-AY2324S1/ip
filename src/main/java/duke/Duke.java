package duke;

import duke.command.Command;
import duke.exception.ChatException;
import duke.parser.Parser;
import duke.storage.Storage;
import duke.task.TaskList;
import duke.ui.Ui;


public class Duke {
    private static Storage storage;
    private static TaskList tasks;
    private static Ui ui;

    public Duke(String filePath) {
        ui = new Ui();
        storage = new Storage(filePath);
        try {
            tasks = storage.loadFile();
        } catch (ChatException e) {
            ui.showLoadingError(e);
            tasks = new TaskList();
        }
    }

    public String getResponse(String input) {
        try {
            Command c = Parser.parse(input);
            return c.execute(tasks, ui, storage);
        } catch (ChatException e) {
            return ui.showLoadingError(e);
        }
    }

}

