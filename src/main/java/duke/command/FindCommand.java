package duke.command;

import java.util.ArrayList;

import duke.DukeException;
import duke.management.NotesList;
import duke.management.Storage;
import duke.management.TaskList;
import duke.task.Task;

/**
 * Find Command Class.
 */
public class FindCommand extends Command {
    /**
     * Constructor for Find Command.
     * @param command User command.
     */
    public FindCommand(String command) {
        super(command);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public String execute(Storage storage, TaskList tasks, NotesList notes) {
        String[] commandArr = this.command.split(" ", 2);
        ArrayList<Task> targetList = new ArrayList<>();
        String target = commandArr[1];
        for (Task task : tasks.getTasks()) {
            if (task.isFound(target)) {
                targetList.add(task);
            }
        }
        String result = "Here are the matching tasks in your list: \n";
        for (int i = 0; i < targetList.size(); i++) {
            int index = i + 1;
            Task t = targetList.get(i);
            result += index + ". " + t.toString() + "\n";
        }

        return result;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void validateCommand() throws DukeException {
        String[] commandArr = this.command.split(" ", 2);
        if (commandArr.length < 2 || commandArr[1].isEmpty()) {
            throw new DukeException(String.format(DukeException.EMPTY_DESCRIPTION, "find"));
        }
    }
}
