package bot.command;

import bot.exception.FileErrorBotException;
import bot.task.TaskList;
import bot.task.Task;
import bot.storage.Storage;

import java.io.IOException;


public class MarkCommand extends Command {
    private final Task task;
    private final TaskList taskList;


    public MarkCommand(TaskList taskList, String taskDetail) {
        this.task = taskList.get(Integer.parseInt(taskDetail) - 1);
        this.taskList = taskList;
    }

    public void execute() throws FileErrorBotException, IOException {
        this.task.setComplete();
        Storage.save(this.taskList);
        System.out.println(this);
    }

    @Override
    public String toString() {
        return Command.SPACER + "\n" +
                "Nice! I've marked this bot.task as done:\n" +
                this.task + "\n" +
                Command.SPACER;
    }
}
