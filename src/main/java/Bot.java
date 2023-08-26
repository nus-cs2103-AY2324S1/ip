import exceptions.*;

/**
 * Main class for the bot.
 */
public class Bot {
    /**
     * Storage object for storing data.
     */
    private final Storage storage;
    /**
     * Task list for storing tasks.
     */
    private TaskList tasks;
    /**
     * User interface for interacting with the user.
     */
    private final Ui ui;

    /**
     * Creates the bot with the given file path as data storage point.
     *
     * @param filePath Path to data storage.
     */
    public Bot(String filePath) {
        this.ui = new Ui();
        this.storage = new Storage(filePath);
        try {
            tasks = new TaskList(storage.load(this.ui));
        } catch (LoadingException e) {
            ui.showError(e.getMessage());
            tasks = new TaskList();
        }
    }

    /**
     * Runs the bot with the file path it was constructed with.
     */
    public void run() {
        ui.showWelcome();
        boolean isExit = false;
        while (!isExit) {
            try {
                String rawCommand = ui.readCommand();
                Command c = Parser.parse(rawCommand);
                c.execute(tasks, ui, storage);
                isExit = c.isExit();
                storage.save(tasks);
            } catch (BotException e) {
                ui.showError(e.getMessage());
            }
        }
    }
    public static void main(String[] args) {
        new Bot("./data/tasks.txt").run();
    }
}