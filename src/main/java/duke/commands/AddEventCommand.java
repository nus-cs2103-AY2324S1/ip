package duke.commands;

import duke.tasks.TaskList;
import duke.tasks.TaskType;

public class AddEventCommand extends Command {

    public AddEventCommand(TaskList taskList, String args) {
        super(CommandType.ADD_EVENT, taskList, args);
    }

    @Override
    public void execute() {
        this.taskList.addTask(TaskType.EVENT, args);
    }
}
