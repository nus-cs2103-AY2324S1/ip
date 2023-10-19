package qi;

import qi.command.Command;
import qi.parser.Parser;
import qi.qiexception.QiException;
import qi.storage.Storage;
import qi.tasklist.TaskList;
import qi.ui.Ui;

/**
 * Serves as the driver program for
 * all the functionalities of the chatbot.
 */
public class Qi {

    private final Storage storage;
    private TaskList tasks;
    private final Ui ui;

    /**
     * Takes in the file path as a string.
     * Initializes a chatbot with data from the given file path.
     *
     * @param filePath File path to the data stored
     */
    public Qi(String filePath) {
        ui = new Ui();
        storage = new Storage(filePath);
        try {
            tasks = new TaskList();
            storage.load(tasks);
        } catch (QiException e) {
            ui.showLoadingError();
            tasks = new TaskList();
        }
    }

    public String getResponse(String input) {
        try {
            Command c = Parser.parse(input);
            String message = c.execute(this.tasks, this.ui, this.storage);
            return message;
        } catch (QiException e) {
            return e.getMessage();
        }
    }

    public String showWelcome() {
        return this.ui.showWelcome();
    }
}



