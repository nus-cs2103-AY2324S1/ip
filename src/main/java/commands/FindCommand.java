package commands;

import functions.TaskList;
import tasks.Task;

public class FindCommand extends Command{

    private TaskList taskList;
    private String functionDescription;

    public FindCommand(TaskList taskList, String functionDescription) {
        this.taskList = taskList;
        this.functionDescription = functionDescription;
    }

    @Override
    public String execute() {
        TaskList matchingTasks = new TaskList();

        for (Task task: this.taskList.getTaskList()) {
            if (task.getDescription().contains(this.functionDescription)) {
                matchingTasks.add(task);
            }
        }

        String message = "";
        message += "____________________________________________________________\n";
        message += "Here are the matching tasks in your list:\n";
        ListCommand command = new ListCommand(matchingTasks);
        String listCommandResult = command.execute();
        message = message + listCommandResult + "\n";
        message += "____________________________________________________________";
        return message;
    }

}
