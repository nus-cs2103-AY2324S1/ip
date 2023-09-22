package bot.command;

import bot.exception.DateTimeParseBotException;
import bot.exception.FileErrorBotException;
import bot.task.TaskList;
import bot.task.Todo;
import bot.storage.Storage;

import java.io.IOException;

public class TodoCommand extends Command {

    private final TaskList taskList;
    private final Todo todo;

    /**
     * Creates an instance of TodoCommand object
     *
     * @param taskList the list of tasks
     * @param taskDetail task description
     */
    public TodoCommand(TaskList taskList, String taskDetail) {
        this.taskList = taskList;
        this.todo = new Todo(taskDetail);
    }

    /**
     * Execute a series of instructions specific to creating adding a Todo object
     *
     * @throws FileErrorBotException if the file or directory is missing or corrupted
     * @throws IOException if an I/O error occurred
     */
    public void execute() throws FileErrorBotException, IOException {
        this.taskList.add(this.todo);
        Storage.save(this.taskList);
        System.out.println(this);
    }

    /**
     * Returns a String representation of EventCommand object
     *
     * @return String representation of EventCommand object
     */
    @Override
    public String toString() {
        if (this.taskList.length() <= 1) {
            return Command.SPACER + "\n" +
                    "Got it. I've added this bot.task:\n" +
                    this.todo + "\n" +
                    "Now you have " + this.taskList.length() + " bot.task in the list.\n" +
                    Command.SPACER;
        } else {
            return Command.SPACER + "\n" +
                    "Got it. I've added this bot.task:\n" +
                    this.todo + "\n" +
                    "Now you have " + this.taskList.length() + " tasks in the list.\n" +
                    Command.SPACER;
        }
    }

}
