package minion;

import java.io.IOException;

import minion.commands.CommandResult;
import minion.data.TaskList;
import minion.data.exception.MinionException;
import minion.parser.CommandParser;
import minion.storage.Storage;

/**
 * Represents the Minion chatbot.
 */
public class Minion {
    private TaskList tasks;
    private final Storage storage;

    /**
     * Constructs the Minion chatbot.
     * @param filePath The file path of the file storing the task list.
     */
    public Minion(String filePath) {
        storage = new Storage(filePath);
        try {
            tasks = new TaskList(storage.load());
        } catch (IOException e) {
            tasks = new TaskList();
        }
    }

    /**
     * Gets the command result from a string input.
     */
    public CommandResult getCommandResult(String input) throws MinionException, IOException {
        return CommandParser
                .parse(input)
                .execute(tasks, storage);
    }
}
