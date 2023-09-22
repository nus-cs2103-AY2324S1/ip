package bot.command;

import bot.task.TaskList;

public class FindCommand extends Command {

    private final TaskList taskList;

    /**
     * Creates an instance of the FindCommand object
     *
     * @param pattern the string pattern from tasks
     */
    public FindCommand(TaskList taskList, String pattern) {
        this.taskList = taskList.find(pattern);
    }

    /**
     * Executes a series of instruction specific to print out tasks containing the pattern/keyword
     */
    @Override
    public void execute() {
        System.out.println(this);
    }

    /**
     * Returns String representation of the FindCommand object
     *
     * @return String representation of the FindCommand object
     */
    @Override
    public String toString() {
        return Command.SPACER + "\n" +
                "Here are the matching tasks in your list:\n" +
                this.taskList.list() + "\n" +
                Command.SPACER;
    }
}
