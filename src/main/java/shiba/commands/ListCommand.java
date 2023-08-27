package shiba.commands;

import shiba.tasks.TaskList;
import shiba.ui.Replier;

public class ListCommand extends ShibaCommand {
    /**
     * Constructor for ListCommand, which lists all tasks
     *
     * @param tasks Current state of task list
     */
    public ListCommand(TaskList tasks) {
        super(tasks);
    }

    @Override
    public void execute() {
        Replier.printHorizontalLine();
        for (int i = 0; i < tasks.size(); i++) {
            Replier.printWithLevel2Indent((i + 1) + "." + tasks.get(i));
        }
        if (tasks.size() == 0) {
            Replier.printWithLevel2Indent("Woof! You have no tasks in the list - go browse some Reddit!");
        }
        Replier.printHorizontalLine();
    }
}
