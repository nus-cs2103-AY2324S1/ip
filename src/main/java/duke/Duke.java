package duke;

import java.io.File;
import java.io.IOException;

import duke.command.Command;
import duke.exception.DukeException;
import duke.util.Parser;
import duke.util.Storage;
import duke.util.TaskList;
import duke.util.Ui;

/**
 * This program is a chatbot, B055man, used to mark completion of tasks
 * marking the completion of tasks. This class contains constructor of duke
 * and getResponse method containing logic for generating user response.
 */
public class Duke {
    private final Storage storage;
    private TaskList tasklst;
    private final Ui ui;

    /**
     * Default duke constructor.
     * @param filePath path to read and write files from
     */
    public Duke(String filePath) {
        ui = new Ui();
        storage = new Storage(filePath);
        try {
            tasklst = new TaskList(storage.load());
        } catch (DukeException e) {
            ui.showLoadingError();
            tasklst = new TaskList();
            File f = new File("./data");
            if (f.mkdir()) {
                ui.showNewFile();
            }
        }
    }

    /**
     * Generates response based on user input.
     * @param input user input
     * @return generated response
     */
    public String getResponse(String input) {
        boolean isExit = false;

        assert input != null;
        //ensure variables are initialised
        assert tasklst != null;
        assert ui != null;
        assert storage != null;

        while (!isExit) {
            try {
                Command c = Parser.parseUserInput(input);
                return c.execute(tasklst, ui, storage);
            } catch (DukeException | IOException e) {
                return e.getMessage();
            }
        }
        return ui.showGoodbye();
    }
}
