package Commands;

import Task.TaskList;
import Task.Task;

public class MarkCommand extends Command {
    private final Task task;


    public MarkCommand(TaskList taskList, String taskDetail) {
        this.task = taskList.get(Integer.parseInt(taskDetail) - 1);
    }

    public void execute() {
        this.task.setComplete();
        System.out.println(this);
    }

    @Override
    public String toString() {
        return Command.SPACER + "\n" +
                "Nice! I've marked this task as done:\n" +
                this.task + "\n" +
                Command.SPACER;
    }
}
