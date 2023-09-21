package duke.command;

import duke.DukeException;
import duke.management.NotesList;
import duke.management.Storage;
import duke.management.TaskList;
import duke.task.Task;

/**
 * Unmark Command Class.
 */
public class UnmarkCommand extends Command {
    /**
     * Constructor for Unmark Command.
     * @param command User command.
     */
    public UnmarkCommand(String command) {
        super(command);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public String execute(Storage storage, TaskList tasks, NotesList notes) {
        try {
            String[] commandArr = this.command.split(" ", 2);
            int index = Integer.parseInt(commandArr[1]) - 1;
            Task t = tasks.getTask(index);
            t.markAsUndone();
            storage.saveTasksToFile(tasks.getTasks());
            return "Nice! Ren marked this task as not done yet: \n" + t.toString();
        } catch (NumberFormatException e) {
            throw new DukeException(DukeException.INVALID_INDEX);
        }

    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void validateCommand() throws DukeException {
        String[] commandArr = this.command.split(" ", 2);
        if (commandArr.length < 2 || commandArr[1].isEmpty()) {
            throw new DukeException(String.format(DukeException.EMPTY_DESCRIPTION, "unmark"));
        }
    }
}
