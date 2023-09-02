package bot.command;

import bot.task.TaskList;

public class ListCommand extends Command {
    private final TaskList taskList;

    public ListCommand(TaskList taskList) {
        this.taskList = taskList;
    }

    public void execute() {
        System.out.println(this);
    }

    @Override
    public String toString() {
        return Command.SPACER + "\n" +
                "Here are the tasks in your list:\n" +
                this.taskList.list() +
                Command.SPACER;
    }
}
