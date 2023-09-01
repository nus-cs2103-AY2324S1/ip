package command;

import exception.FileErrorBotException;
import task.TaskList;
import task.Task;
import storage.Storage;


public class MarkCommand extends Command {
    private final Task task;
    private final TaskList taskList;


    public MarkCommand(TaskList taskList, String taskDetail) {
        this.task = taskList.get(Integer.parseInt(taskDetail) - 1);
        this.taskList = taskList;
    }

    public void execute() throws FileErrorBotException {
        this.task.setComplete();
        Storage.save(this.taskList);
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
