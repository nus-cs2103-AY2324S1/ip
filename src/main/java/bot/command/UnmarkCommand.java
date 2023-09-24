package bot.command;

import bot.exception.FileErrorBotException;
import bot.exception.IncompleteBotException;

import bot.task.TaskList;
import bot.task.Task;
import bot.storage.Storage;

public class UnmarkCommand extends Command {
    private final Task task;
    private final TaskList taskList;

    /**
     * Creates an instance of MarkCommand object
     *
     * @param taskList the list of tasks
     * @param idx Index of Task to be marked
     * @throws IncompleteBotException if idx is an empty string
     */
    public UnmarkCommand(TaskList taskList, String idx) throws IncompleteBotException {
        if (idx.isBlank()) {
            throw new IncompleteBotException("OOPS!!! The task number to mark cannot be empty.");
        }
        this.task = taskList.get(Integer.parseInt(idx) - 1);
        this.taskList = taskList;
    }

    /**
     * Execute a series of instructions specific to unmarking a task from TaskList object
     * and returns the execution output
     *
     * @return String of the outcome of the command execution
     * @throws FileErrorBotException if the file or directory is missing or corrupted
     */
    @Override
    public String execute() throws FileErrorBotException {
        this.task.setIncomplete();
        Storage.save(this.taskList);
        return this.toString();
    }

    /**
     * Returns a String representation of UnmarkCommand
     *
     * @return String representation of UnmarkCommand
     */
    @Override
    public String toString() {
        return Command.SPACER + "\n" +
                "OK, I've marked this task as not done yet:\n" +
                this.task + "\n" +
                Command.SPACER;
    }
}
