package duke.command;

import duke.DukeException;
import duke.management.NotesList;
import duke.management.TaskList;
import duke.task.Deadline;
import duke.task.Task;

/**
 * Deadline Command Class.
 */
public class DeadlineCommand extends Command {
    /**
     * Constructor for Deadline Command.
     * @param command User command.
     */
    public DeadlineCommand(String command) {
        super(command);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public String execute(TaskList tasks, NotesList notes) {
        String[] commandArr = this.command.split(" ", 2);
        String[] deadlineArr = commandArr[1].split("/by ", 2);
        Task deadline = new Deadline(deadlineArr[0], deadlineArr[1]);
        tasks.addTask(deadline);
        String result = "Ren helped you add " + deadline.toString() + "\n";
        return result + "Now you have " + tasks.getSize() + " tasks in the list.";
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void validateCommand() throws DukeException {
        String[] commandArr = this.command.split(" ", 2);
        if (commandArr.length < 2) {
            throw new DukeException(String.format(DukeException.NON_EMPTY, "deadline"));
        }
    }
}
