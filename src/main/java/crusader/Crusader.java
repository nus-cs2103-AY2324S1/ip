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
     * Activates the bot.
     */
    public void run() {
        this.ui.showLogo();
        this.ui.greet();
        boolean hasEnded = false;
        while (!hasEnded) {
            try {
                String command = this.ui.promptInput();
                Command c = Parser.parse(command);
                c.execute(ui, taskList);
                hasEnded = c.isExit();
            } catch (CrusaderException e) {
                this.ui.say(e.getMessage());
            }
        }
        this.ui.farewell();
        this.storage.saveTasks(taskList.getTasks());
    }

    public static void main(String[] args) {
        new Crusader(SAVE_FILE, LOGO).run();
    }
}
