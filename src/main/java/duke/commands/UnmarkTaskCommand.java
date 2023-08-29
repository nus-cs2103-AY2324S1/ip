package duke.commands;

import duke.exceptions.DukeInvalidArgumentException;
import duke.tasks.TaskList;

public class UnmarkTaskCommand extends Command {

    public UnmarkTaskCommand(TaskList taskList, String args) {
        super(CommandType.UNMARK_TASK, taskList, args);
    }

    @Override
    public void execute() {
        try {
            this.taskList.unmarkTaskDone(Integer.parseInt(args));
        } catch (NumberFormatException e) {
            throw new DukeInvalidArgumentException("Stop trolling me bro. Please enter a numeric index.");
        }
    }
}
