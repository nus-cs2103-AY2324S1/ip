package duke.commands;

import duke.exceptions.DukeInvalidArgumentException;
import duke.tasks.TaskList;

public class MarkTaskCommand extends Command {

    public MarkTaskCommand(CommandType commandType, TaskList taskList, String args) {
        super(commandType, taskList, args);
    }

    @Override
    public void execute() {
        try {
            this.taskList.markTaskDone(Integer.parseInt(args));
        } catch (NumberFormatException e) {
            throw new DukeInvalidArgumentException("Stop trolling me bro. Please enter a numeric index.");
        }
    }
}
