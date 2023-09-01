package command;

import exception.FileErrorBotException;
import task.TaskList;
import task.Task;
import storage.Storage;

public class UnmarkCommand extends Command {
    private final Task task;
    private final TaskList taskList;


    public UnmarkCommand(TaskList taskList, String taskDetail) {
        this.task = taskList.get(Integer.parseInt(taskDetail) - 1);
        this.taskList = taskList;
    }

    public void execute() throws FileErrorBotException {
        this.task.setIncomplete();
        Storage.save(this.taskList);
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