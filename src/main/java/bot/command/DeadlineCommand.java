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

    /**
     * Creates an instance of DeadlineCommand object
     *
     * @param taskList the list of tasks
     * @param taskDetail task description
     * @param dueDate deadline of task formatted 'd/MM/yyyy HH:mm'
     * @throws DateTimeParseBotException if dueDate argument is not formatted correctly
     */
    public DeadlineCommand(TaskList taskList, String taskDetail,
                           String dueDate) throws DateTimeParseBotException {
        this.taskList = taskList;
        this.deadline = new Deadline(taskDetail, dueDate);
    }

    /**
     * Execute a series of instructions specific to creating adding a Deadline object
     *
     * @throws FileErrorBotException if the file or directory is missing or corrupted
     * @throws IOException if an I/O error occurred
     */
    public void execute() throws FileErrorBotException, IOException {
        this.taskList.add(this.deadline);
        Storage.save(this.taskList);
        System.out.println(this);
    }

    /**
     * Returns a String representation of DeadlineCommand object
     *
     * @return String representation of DeadlineCommand object
     */
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
