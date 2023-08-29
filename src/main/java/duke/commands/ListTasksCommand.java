package duke.commands;

import duke.tasks.TaskList;

public class ListTasksCommand extends Command {

    public ListTasksCommand(CommandType commandType, TaskList taskList, String args) {
        super(commandType, taskList, args);
    }

    @Override
    public void execute() {
        this.taskList.listTasks();
    }
}
