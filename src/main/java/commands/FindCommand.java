package commands;

import functions.TaskList;
import tasks.Task;

public class FindCommand extends Command{

    private TaskList taskList;
    private String secondHalfInput;

    public FindCommand(TaskList taskList, String secondHalfInput) {
        this.taskList = taskList;
        this.secondHalfInput = secondHalfInput;
    }

    @Override
    public String execute() {
        TaskList matchingTasks = new TaskList();

        for (Task task: this.taskList.getTaskList()) {
            if (task.getDescription().contains(this.secondHalfInput)) {
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
