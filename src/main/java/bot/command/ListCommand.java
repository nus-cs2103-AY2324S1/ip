package bot.command;

import bot.task.TaskList;

public class ListCommand extends Command {
    private final TaskList taskList;

    /**
     * Creates an instance of ListCommand object
     *
     * @param taskList the list of tasks
     */
    public ListCommand(TaskList taskList) {
        this.taskList = taskList;
    }

    /**
     * Execute a series of instructions specific to listing all tasks in TaskList
     * and returns the execution output
     *
     * @return String of the outcome of the command execution
     */
    public String execute() {
        return this.toString();
    }

    /**
     * Returns a String representation of ListCommand object
     *
     * @return String representation of ListCommand object
     */
    @Override
    public String toString() {
        return Command.SPACER + "\n" +
                "Here are the tasks in your list:\n" +
                this.taskList.list() +
                Command.SPACER;
    }
}
