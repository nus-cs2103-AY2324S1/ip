package bot.command;

import bot.exception.DateTimeParseBotException;
import bot.exception.FileErrorBotException;
import bot.storage.Storage;
import bot.task.Task;
import bot.task.TaskList;

import java.io.IOException;

public class DeleteCommand extends Command {
    private final TaskList taskList;
    private final int idx;
    private final Task task;

    /**
     * Creates an instance of DeleteCommand object
     *
     * @param taskList the list of tasks
     * @param idx Index of Task to be deleted
     */
    public DeleteCommand(TaskList taskList, String idx) {
        this.taskList = taskList;
        this.idx = Integer.parseInt(idx) - 1;
        this.task = this.taskList.get(this.idx);
    }

    /**
     * Execute a series of instructions specific to deleting a task from TaskList object
     *
     * @throws FileErrorBotException if the file or directory is missing or corrupted
     * @throws IOException if an I/O error occurred
     */
    public void execute() throws FileErrorBotException, IOException {
        this.taskList.delete(idx);
        Storage.save(this.taskList);
        System.out.println(this);
    }

    /**
     * Returns a String representation of DeleteCommand
     *
     * @return String representation of DeleteCommand
     */
    @Override
    public String toString() {
        return Command.SPACER + "\n" +
                "Noted. I've removed this bot.task:\n" +
                this.task + "\n" +
                "Now you have " + this.taskList.length() + " tasks in the list.\n" +
                Command.SPACER;
    }
}
