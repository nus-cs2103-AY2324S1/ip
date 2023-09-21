package duke.command;

import duke.DukeException;
import duke.management.NotesList;
import duke.management.Storage;
import duke.management.TaskList;

/**
 * List Command Class.
 */
public class ListCommand extends Command {
    /**
     * Constructor for List Command.
     *
     * @param commmand User command.
     */
    public ListCommand(String commmand) {
        super(commmand);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public String execute(Storage storage, TaskList tasks, NotesList notes) {
        return tasks.printTasks();
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void validateCommand() throws DukeException {
        String[] commandArr = this.command.split(" ", 2);
        System.out.println(commandArr);
        if (commandArr.length > 1) {
            throw new DukeException(DukeException.EMPTY);
        }
    }
}
