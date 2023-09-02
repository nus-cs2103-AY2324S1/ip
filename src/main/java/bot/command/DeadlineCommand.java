package bot.command;

import bot.exception.DateTimeParseBotException;
import bot.exception.FileErrorBotException;
import bot.task.TaskList;
import bot.task.Deadline;
import bot.storage.Storage;

import java.io.IOException;

public class DeadlineCommand extends Command {

    private final TaskList taskList;
    private final Deadline deadline;

    public DeadlineCommand(TaskList taskList, String taskDetail,
                           String dueDate) throws DateTimeParseBotException {
        this.taskList = taskList;
        this.deadline = new Deadline(taskDetail, dueDate);
    }

    public void execute() throws FileErrorBotException, IOException {
        this.taskList.add(this.deadline);
        Storage.save(this.taskList);
        System.out.println(this);
    }

    @Override
    public String toString() {
        if (this.taskList.length() <= 1) {
            return Command.SPACER + "\n" +
                    "Got it. I've added this bot.task:\n" +
                    this.deadline + "\n" +
                    "Now you have " + this.taskList.length() + " bot.task in the list.\n" +
                    Command.SPACER;
        } else {
            return Command.SPACER + "\n" +
                    "Got it. I've added this bot.task:\n" +
                    this.deadline + "\n" +
                    "Now you have " + this.taskList.length() + " tasks in the list.\n" +
                    Command.SPACER;
        }
    }
}
