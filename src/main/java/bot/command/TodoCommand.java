package bot.command;

import bot.exception.FileErrorBotException;
import bot.exception.IncompleteBotException;

import bot.task.TaskList;
import bot.task.Todo;
import bot.storage.Storage;

public class TodoCommand extends Command {

    private final TaskList taskList;
    private final Todo todo;

    /**
     * Creates an instance of TodoCommand object
     *
     * @param taskList the list of tasks
     * @param taskDetail task description
     * @throws IncompleteBotException if taskDetail is empty
     */
    public TodoCommand(TaskList taskList, String taskDetail) throws IncompleteBotException {
        if (taskDetail.isBlank()) {
            throw new IncompleteBotException("OOPS!!! The description of a todo cannot be empty.");
        }
        this.taskList = taskList;
        this.todo = new Todo(taskDetail);
    }

    /**
     * Execute a series of instructions specific to creating adding a Todo object
     * and returns the execution output
     *
     * @return String of the outcome of the command execution
     * @throws FileErrorBotException if the file or directory is missing or corrupted
     */
    public String execute() throws FileErrorBotException {
        this.taskList.add(this.todo);
        Storage.save(this.taskList);
        return this.toString();
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
                    "Got it. I've added this task:\n" +
                    this.todo + "\n" +
                    "Now you have " + this.taskList.length() + " task in the list.\n" +
                    Command.SPACER;
        } else {
            return Command.SPACER + "\n" +
                    "Got it. I've added this task:\n" +
                    this.todo + "\n" +
                    "Now you have " + this.taskList.length() + " tasks in the list.\n" +
                    Command.SPACER;
        }
    }

}
