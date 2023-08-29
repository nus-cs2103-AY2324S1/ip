package duke.commands;

import duke.exceptions.DukeInvalidArgumentException;
import duke.tasks.TaskList;

public class DeleteTaskCommand extends Command {

    public DeleteTaskCommand(TaskList taskList, String args) {
        super(CommandType.DELETE_TASK, taskList, args);
    }

    @Override
    public void execute() {
        try {
            this.taskList.deleteTask(Integer.parseInt(args));
        } catch (NumberFormatException e) {
            throw new DukeInvalidArgumentException("Stop trolling me bro. Please enter a numeric index.");
        }
    }
}
