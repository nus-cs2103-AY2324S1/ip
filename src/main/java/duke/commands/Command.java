package duke.commands;

import duke.Storage;
import duke.TaskList;

/**
 * The abstract command class containing execution information based on a user's input.
 */
public abstract class Command {
    /**
     * Executes the given command using the tasklist and storage resources.
     *
     * @param tasklist
     * @param storage
     */
    public abstract String execute(TaskList tasklist, Storage storage);
}


