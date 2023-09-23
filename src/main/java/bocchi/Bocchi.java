package bocchi;

import exceptions.BocchiException;
import javafx.scene.layout.VBox;
import parser.Parser;
import storage.BocchiLoader;
import storage.BocchiSaver;
import task.TaskList;
import ui.Ui;


/**
 * Class to run an instance of Bocchi
 */
public class Bocchi {
    private final Ui ui;
    private final Parser parser;
    private TaskList taskList;

    /**
     * Initializes the program
     *
     * @param dialogContainer The container for displaying dialog message.
     */
    public Bocchi(VBox dialogContainer) {
        this.ui = new Ui(dialogContainer);
        this.parser = new Parser(this.ui);
        this.taskList = new BocchiLoader().loadTaskList(this.ui);
        ui.greet();
    }

    /**
     * Processes user input and returns a response.
     *
     * @param input The user's input.
     * @return True if the program should terminate, false otherwise.
     */
    public boolean getResponse(String input) {
        if (parser.isTerminated(input)) {
            BocchiSaver saver = new BocchiSaver(taskList);
            saver.saveTaskList();
            return true;
        }
        try {
            this.taskList = this.parser.process(input, this.taskList);
        } catch (BocchiException e) {
            ui.exception(e);
        }
        return false;
    }
}
