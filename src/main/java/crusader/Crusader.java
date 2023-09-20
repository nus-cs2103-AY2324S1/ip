package crusader;

import crusader.command.Command;
import crusader.exception.CrusaderException;
import crusader.exception.CrusaderMissingSaveFileException;

/**
 * The main instance of the bot.
 */
public class Crusader {
    /** Logo generated from https://patorjk.com/software/taag */
    private static final String LOGO =
              "   _____                          _\n"
            + "  / ____|                        | |\n"
            + " | |     _ __ _   _ ___  __ _  __| | ___ _ __\n"
            + " | |    | '__| | | / __|/ _` |/ _` |/ _ \\ '__|\n"
            + " | |____| |  | |_| \\__ \\ (_| | (_| |  __/ |\n"
            + "  \\_____|_|   \\__,_|___/\\__,_|\\__,_|\\___|_|";

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
     * The interface of the bot.
     */
    private final Ui ui;

    /**
     * Constructs a Crusader bot instance.
     * @param filePath
     * @param logo
     */
    public Crusader(String filePath, String logo) {
        TaskList taskList;
        this.ui = new Ui(logo);
        this.storage = new Storage(filePath);
        try {
            taskList = new TaskList(storage.loadTasks());
        } catch (CrusaderMissingSaveFileException e) {
            ui.say("Creating a new file, file does not exist!");
            taskList = new TaskList();
        } catch (CrusaderException e) {
            ui.say("Saved file is formatted wrongly!");
            taskList = new TaskList();
        }
        this.taskList = taskList;
    }
    /**
     * Constructs a Crusader bot instance.
     */
    public Crusader() {
        TaskList taskList;
        this.ui = new Ui(LOGO);
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

    public String getResponse(String input) {
        try {
            Command c = Parser.parse(input);
            assert ui != null;
            assert taskList != null;
            assert storage != null;
            return c.execute(ui, taskList, storage);
        } catch (CrusaderException e) {
            return e.getMessage();
        }
    }
}
