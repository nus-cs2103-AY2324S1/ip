package commands;

import duke.DukeException;
import duke.TaskList;
import duke.Ui;
import duke.Storage;

import java.io.IOException;

/**
 * An abstract command class
 */
public abstract class Command {

    /**
     * Executes the command
     * @param tasks Tasklist object that contains a list of tasks
     * @param ui UI object that interacts with the user
     * @param storage Storage object that manipulates task data in text file
     * @return a response by Ari in String
     * @throws IOException if the text file cannot be opened or located
     * @throws DukeException if the command cannot be executed
     */
    public abstract String execute(TaskList tasks, Ui ui, Storage storage) throws IOException, DukeException;
}
