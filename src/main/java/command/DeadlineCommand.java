package command;

import exception.FileErrorBotException;
import task.TaskList;
import task.Deadline;
import storage.Storage;

public class DeadlineCommand extends Command {

    private final TaskList taskList;
    private final Deadline deadline;

    public DeadlineCommand(TaskList taskList, String taskDetail, String dueDate) {
        this.taskList = taskList;
        this.deadline = new Deadline(taskDetail, dueDate);
    }

    public void execute() throws FileErrorBotException {
        this.taskList.add(this.deadline);
        Storage.save(this.taskList);
        System.out.println(this);
    }

    @Override
    public String toString() {
        if (this.taskList.length() <= 1) {
            return Command.SPACER + "\n" +
                    "Got it. I've added this task:\n" +
                    this.deadline + "\n" +
                    "Now you have " + this.taskList.length() + " task in the list.\n" +
                    Command.SPACER;
        } else {
            return Command.SPACER + "\n" +
                    "Got it. I've added this task:\n" +
                    this.deadline + "\n" +
                    "Now you have " + this.taskList.length() + " tasks in the list.\n" +
                    Command.SPACER;
        }
    }
}
