package bot.command;

import bot.exception.FileErrorBotException;
import bot.task.TaskList;
import bot.task.Task;
import bot.storage.Storage;

import java.io.IOException;

public class UnmarkCommand extends Command {
    private final Task task;
    private final TaskList taskList;

    /**
     * Creates an instance of MarkCommand object
     *
     * @param taskList the list of tasks
     * @param idx Index of Task to be marked
     */
    public UnmarkCommand(TaskList taskList, String idx) {
        this.task = taskList.get(Integer.parseInt(idx) - 1);
        this.taskList = taskList;
    }

    /**
     * Execute a series of instructions specific to unmarking a task from TaskList object
     *
     * @throws FileErrorBotException if the file or directory is missing or corrupted
     * @throws IOException if an I/O error occurred
     */
    @Override
    public void execute() throws FileErrorBotException, IOException {
        this.task.setIncomplete();
        Storage.save(this.taskList);
        System.out.println(this);
    }

    /**
     * Returns a String representation of UnmarkCommand
     *
     * @return String representation of UnmarkCommand
     */
    @Override
    public String toString() {
        return Command.SPACER + "\n" +
                "OK, I've marked this bot.task as not done yet:\n" +
                this.task + "\n" +
                Command.SPACER;
    }
}