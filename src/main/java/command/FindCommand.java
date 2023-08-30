package command;

import duke.DiskManager;
import duke.DukeException;
import duke.TaskManager;
import duke.Ui;
import task.Event;

/**
 * Represents a find command where when executed,
 * finds all task that matches the string pattern.
 */
public class FindCommand extends Command {
    private String pattern;

    /**
     * Constructs a FindCommand with the input.
     *
     * @param pattern The pattern to find.
     */
    public FindCommand(String pattern) {
        this.pattern = pattern;
    }

    @Override
    public boolean isExit() {
        return false;
    }

    @Override
    public void execute(TaskManager taskManager, DiskManager diskManager, Ui ui) throws DukeException {
        ui.printOutput(taskManager.find(pattern));
    }

    @Override
    public boolean equals(Object other) {
        if (other instanceof FindCommand) {
            FindCommand temp = (FindCommand) other;
            return temp.pattern.equals(this.pattern);
        }
        return false;
    }
}
