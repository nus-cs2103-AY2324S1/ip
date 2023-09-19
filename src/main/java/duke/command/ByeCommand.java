package duke.command;

import duke.DukeException;
import duke.management.NotesList;
import duke.management.Storage;
import duke.management.TaskList;
import duke.management.Ui;

/**
 * Bye Command Class.
 */
public class ByeCommand extends Command {
    /**
     * Constructor for Bye Command.
     * @param command User command.
     */
    public ByeCommand(String command) {
        super(command);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public String execute(Storage storage, TaskList tasks, NotesList notes) {
        return Ui.bye();
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void validateCommand() throws DukeException {
        String[] commandArr = this.command.split(" ", 2);
        if (commandArr.length > 1) {
            throw new DukeException("To exit the program, please input only 'bye'!");
        }
    }
}
