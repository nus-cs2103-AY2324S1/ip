package bot.command;

import bot.exception.FileErrorBotException;
import bot.exception.IncompleteBotException;

import bot.storage.Storage;
import bot.task.Task;
import bot.task.TaskList;

public class DeleteCommand extends Command {
    private final TaskList taskList;
    private final int idx;
    private final Task task;

    /**
     * Creates an instance of DeleteCommand object
     *
     * @param taskList the list of tasks
     * @param idx Index of Task to be deleted
     * @throws IncompleteBotException if idx is an empty string
     */
    public DeleteCommand(TaskList taskList, String idx) throws IncompleteBotException {
        if (idx.isBlank()) {
            throw new IncompleteBotException("OOPS!!! The task number to delete cannot be empty.");
        }
        this.taskList = taskList;
        this.idx = Integer.parseInt(idx) - 1;
        task = this.taskList.get(this.idx);
    }

    /**
     * Execute a series of instructions specific to deleting a task from TaskList object
     * and returns the execution output
     *
     * @return String of the outcome of the command execution
     * @throws FileErrorBotException if the file or directory is missing or corrupted
     */
    public String execute() throws FileErrorBotException {
        this.taskList.delete(idx);
        Storage.save(this.taskList);
        return this.toString();
    }

    /**
     * Returns a String representation of DeleteCommand
     *
     * @return String representation of DeleteCommand
     */
    @Override
    public String toString() {
        return Command.SPACER + "\n" +
                "Noted. I've removed this task:\n" +
                this.task + "\n" +
                "Now you have " + this.taskList.length() + " tasks in the list.\n" +
                Command.SPACER;
    }
}
