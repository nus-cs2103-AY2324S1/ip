package duke.command;

import duke.DukeException;
import duke.management.NotesList;
import duke.management.Storage;
import duke.management.TaskList;
import duke.task.Task;

/**
 * Mark Command Class.
 */
public class MarkCommand extends Command {
    /**
     * Constructor for Mark Command.
     * @param command User command.
     */
    public MarkCommand(String command) {
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
            t.markAsDone();
            storage.saveTasksToFile(tasks.getTasks());
            return "Nice! Ren marked this task as done: \n" + t.toString();
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
            throw new DukeException(String.format(DukeException.EMPTY_DESCRIPTION, "mark"));
        }
    }
}
