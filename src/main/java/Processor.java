import java.io.IOException;

import com.alpha.commands.Command;
import com.alpha.exceptions.InvalidDateTimeException;
import com.alpha.exceptions.InvalidTaskException;
import com.alpha.storage.Storage;
import com.alpha.tasks.TaskList;
import com.alpha.utils.TaskParser;

/**
 * The Processor class used to process user inputs/commands.
 */
public class Processor {

    private Storage storage;
    private TaskList taskList;

    /**
     * Instantiates a new Processor.
     *
     * @param filePath The file path to the local storage file.
     */
    public Processor(String filePath) {
        try {
            storage = new Storage(filePath);
            taskList = new TaskList(storage.load().getTasks());
        } catch (IOException | InvalidTaskException | InvalidDateTimeException e) {
            taskList = new TaskList();
        }
    }

    /**
     * Process and execute user commands.
     *
     * @param input The input command.
     * @return The output of executing the command.
     */
    public String processInput(String input) {
        try {
            Command command = TaskParser.parse(input, taskList);
            return command.execute();
        } catch (InvalidTaskException | InvalidDateTimeException e) {
            return e.getMessage();
        }
    }

    /**
     * Save task list to local storage file..
     */
    public void saveTaskList() {
        try {
            storage.save(taskList);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

}
