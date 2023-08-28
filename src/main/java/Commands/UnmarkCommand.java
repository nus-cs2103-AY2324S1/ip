package Commands;

import Task.TaskList;
import Task.Task;

public class UnmarkCommand extends Command {
    private final Task task;


    public UnmarkCommand(TaskList taskList, String taskDetail) {
        this.task = taskList.get(Integer.parseInt(taskDetail) - 1);
    }

    public void execute() {
        this.task.setIncomplete();
        System.out.println(this);
    }

    @Override
    public String toString() {
        return Command.SPACER + "\n" +
                "OK, I've marked this task as not done yet:\n" +
                this.task + "\n" +
                Command.SPACER;
    }
}