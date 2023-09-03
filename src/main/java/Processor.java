import java.io.IOException;

import com.alpha.commands.Command;
import com.alpha.exceptions.InvalidTaskException;
import com.alpha.storage.Storage;
import com.alpha.tasks.TaskList;
import com.alpha.ui.Ui;
import com.alpha.utils.Parser;

/**
 * The type Processor.
 */
public class Processor {

    private static Ui ui;
    private Storage storage;
    private TaskList taskList;

    /**
     * Instantiates a new Processor.
     *
     * @param filePath File path of the local storage file.
     */
    public Processor(String filePath) {
        ui = new Ui();
        try {
            storage = new Storage(filePath);
            taskList = new TaskList(storage.load().getTasks());
        } catch (IOException | InvalidTaskException e) {
            ui.loadingError();
            taskList = new TaskList();
        }
    }

    /**
     * Process the user input string.
     *
     * @param input the input
     * @return the processing result string
     */
    public String processInput(String input) {
        Command command = Parser.parse(input);
        if (command == null) {
            return "Error processing input.";
        }
        return command.execute(taskList, ui, storage);
    }

}
