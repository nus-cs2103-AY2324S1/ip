package dook.command;

import dook.DookException;
import dook.services.Parser;
import dook.services.Storage;
import dook.services.TaskList;


/**
 * Represents any invalid command.
 */
public class InvalidCommand extends Command {
    /**
     * Displays a list of all possible commands and their descriptions.
     *
     * @param storage  Given storage.
     * @param taskList Given task list.
     * @param parser
     * @return Message to be displayed in GUI.
     * @throws DookException Exception thrown by Dook.
     */
    @Override
    public String execute(Storage storage, TaskList taskList, Parser parser) throws DookException {
        StringBuilder result = new StringBuilder();
        result.append("Available commands:\n");
        for (CommandInfo c : CommandInfo.values()) {
            result.append(c.toString()).append("\n");
        }
        return result.toString();
    }
}
