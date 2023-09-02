package bot;

import bot.exceptions.BotException;
import bot.exceptions.LoadingException;
import bot.utils.Command;
import bot.utils.Parser;
import bot.utils.Storage;
import bot.utils.TaskList;
import bot.utils.Ui;

/**
 * Main class for the bot.
 */
public class Bot {
    /**
     * Bot.Storage object for storing data.
     */
    private Storage storage;
    /**
     * User interface for interacting with the user.
     */
    private Ui ui;
    /**
     * Bot.Task list for storing tasks.
     */
    private TaskList tasks;

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
     * Main function of program.
     *
     * @param args Optional arguments. It currently does nothing.
     */
    public static void main(String[] args) {
        new Bot("./data/tasks.txt").run();
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
}
