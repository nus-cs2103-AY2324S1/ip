package cringebot;

import cringebot.dataFile.Storage;
import cringebot.exceptions.DukeException;
import cringebot.parser.Parser;
import cringebot.tasks.TaskList;

/**
 * Class for the CringeBot chat bot.
 */
public class CringeBot {
    private final Storage storage;
    private TaskList tasks;

    /**
     * Constructor for Duke.
     *
     * @param filePath file path to the storage.
     */
    public CringeBot(String filePath) {
        storage = new Storage(filePath);
        try {
            tasks = new TaskList(storage.load());
        } catch (DukeException e) {
            tasks = new TaskList();
        }
    }

    /**
     * Gets response from CringeBot, based on the user input.
     *
     * @param input input from the user.
     * @return response from CringeBot.
     */
    public String getResponse(String input) {
        return Parser.parseCommands(input, this.tasks, this.storage);
    }
}
