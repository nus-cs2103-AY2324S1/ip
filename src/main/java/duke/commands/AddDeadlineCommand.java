package duke.commands;

import duke.tasks.TaskList;
import duke.tasks.TaskType;

public class AddDeadlineCommand extends Command {

    public AddDeadlineCommand(TaskList taskList, String args) {
        super(CommandType.ADD_DEADLINE, taskList, args);
    }

    @Override
    public void execute() {
        this.taskList.addTask(TaskType.DEADLINE, args);
    }
}
