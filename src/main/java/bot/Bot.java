package bot;

import bot.exceptions.BotException;
import bot.exceptions.LoadingException;
import bot.utils.commands.Command;
import bot.utils.Parser;
import bot.utils.Storage;
import bot.utils.TaskList;
import bot.utils.Ui;

/**
 * Main class for the bot.
 */
public class Bot {
    /**
     * Storage object for storing data.
     */
    private final Storage storage;
    /**
     * User interface for interacting with the user.
     */
    private final Ui ui;
    /**
     * Task list for storing tasks.
     */
    private TaskList tasks;
    /**
     * Shows if the bot is active or not.
     */
    private boolean isExit = false;

    /**
     * Creates the bot.
     */
    public Bot() {
        this.ui = new Ui();
        this.storage = new Storage("/data/tasks.txt");
        try {
            tasks = new TaskList(storage.load(this.ui));
        } catch (LoadingException e) {
            ui.showError(e.getMessage());
            tasks = new TaskList();
        }
    }

    /**
     * Gives the bot's response to a String input.
     *
     * @param input Raw input string.
     * @return Bot's response as a string.
     */
    public String getResponse(String input) {
        String response;
        try {
            Command command = Parser.parse(input);
            response = command.execute(tasks, ui, storage);
            if (command.getExitStatus()) {
                isExit = true;
            }
            storage.save(tasks);
        } catch (BotException exception) {
            response = ui.showError(exception.getMessage());
        }
        return response;
    }

    /**
     * Gets the bot's greeting message.
     *
     * @return Greeting message.
     */
    public String greet() {
        return ui.showWelcome();
    }

    /**
     * Indicates if the bot is active.
     *
     * @return True if the bot is active, else false.
     */
    public boolean getExitStatus() {
        return this.isExit;
    }
}
