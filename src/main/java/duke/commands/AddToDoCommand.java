package duke.commands;

import duke.tasks.TaskList;
import duke.tasks.TaskType;

public class AddToDoCommand extends Command {

    public AddToDoCommand(TaskList taskList, String args) {
        super(CommandType.ADD_TODO, taskList, args);
    }

    @Override
    public void execute() {
        this.taskList.addTask(TaskType.TODO, args);
    }

}
