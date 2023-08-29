package duke.commands;

import duke.tasks.TaskList;

public class ListTasksCommand extends Command {

    public ListTasksCommand(TaskList taskList, String args) {
        super(CommandType.LIST_TASKS, taskList, args);
    }

    @Override
    public void execute() {
        this.taskList.listTasks();
    }
}
