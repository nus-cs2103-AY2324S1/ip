package crusader;

import crusader.command.Command;
import crusader.exception.CrusaderException;
import crusader.exception.CrusaderMissingSaveFileException;

/**
 * The main instance of the bot.
 */
public class Crusader {

    /**
     * The filepath used to save data.
     */
    private static final String SAVE_FILE = "./data/crusader.txt";

    /**
     * The system used to manage data storage.
     */
    private final Storage storage;

    /**
     * The set of tasks in the bot.
     */
    private final TaskList taskList;

    /**
     * Constructs a Crusader bot instance.
     */
    public Crusader() {
        TaskList taskList;
        this.storage = new Storage(SAVE_FILE);
        try {
            taskList = new TaskList(storage.loadTasks());
        } catch (CrusaderMissingSaveFileException e) {
            taskList = new TaskList();
        } catch (CrusaderException e) {
            taskList = new TaskList();
        }
        this.taskList = taskList;
    }

    /**
     * Executes a command and generates response for GUI.
     *
     * @param input User input
     * @return String output from command
     */
    public String getResponse(String input) {
        try {
            Command c = Parser.parse(input);
            assert taskList != null;
            assert storage != null;
            return c.execute(taskList, storage);
        } catch (CrusaderException e) {
            return e.getMessage();
        }
    }
}
