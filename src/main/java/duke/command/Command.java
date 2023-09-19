package duke.command;


import duke.DukeException;
import duke.Storage;
import duke.TaskList;
import duke.UI;

/**
 * A class that is responsible for executing a certain command when it is being called.
 */
public interface Command {

    /**
     * Carries out the command and returns a response.
     * @param text The command typed in by the user.
     * @param ui The user interface, which is in charge of dealing with interactions with the user.
     * @param list The TaskList that holds all the tasks.
     * @param storage The storage that holds and notes down all past commands.
     * @return The response upon calling this specific command.
     * @throws DukeException
     */
    public abstract String execute(String text, UI ui, TaskList list, Storage storage) throws DukeException;

}
