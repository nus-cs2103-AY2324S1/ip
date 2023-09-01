package command;

import exception.FileErrorBotException;
import storage.Storage;
import task.Task;
import task.TaskList;

public class DeleteCommand extends Command {
    private final TaskList taskList;
    private final int idx;
    private final Task task;

    public DeleteCommand(TaskList taskList, String taskDetail) {
        this.taskList = taskList;
        this.idx = Integer.parseInt(taskDetail) - 1;
        this.task = this.taskList.get(this.idx);
    }

    public void execute() throws FileErrorBotException {
        this.taskList.delete(idx);
        Storage.save(this.taskList);
        System.out.println(this);
    }

    @Override
    public String toString() {
        return Command.SPACER + "\n" +
                "Noted. I've removed this task:\n" +
                this.task + "\n" +
                "Now you have " + this.taskList.length() + " tasks in the list.\n" +
                Command.SPACER;
    }
}
