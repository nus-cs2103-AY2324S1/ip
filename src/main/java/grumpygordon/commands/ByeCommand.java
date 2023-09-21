package grumpygordon.commands;

import grumpygordon.storage.Storage;
import grumpygordon.tasks.TaskList;

/**
 * Represents a command to exit the program.
 */
public class ByeCommand extends Command {
    private static final String OUTRO = "Bye. Hope to never see you again.";

    /**
     * Executes the bye command.
     * @param tasks The list of tasks
     * @param storage The storage of the program
     * @return The outro string
     */
    @Override
    public String execute(TaskList tasks, Storage storage) {
        return OUTRO;
    }
}
