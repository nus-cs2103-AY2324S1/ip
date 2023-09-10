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
    public void execute() {
        TaskList matchingTasks = new TaskList();

        for (Task task: this.taskList.getTaskList()) {
            if (task.getDescription().contains(this.secondHalfInput)) {
                matchingTasks.add(task);
            }
        }

        System.out.println("____________________________________________________________");
        System.out.println("Here are the matching tasks in your list:");
        ListCommand command = new ListCommand(matchingTasks);
        command.execute();
        System.out.println("____________________________________________________________");
    }

}
