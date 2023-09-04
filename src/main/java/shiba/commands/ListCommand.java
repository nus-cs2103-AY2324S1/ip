package shiba.commands;

import shiba.tasks.PersistentTaskList;
import shiba.ui.Replier;

/**
 * Represents a command that lists all tasks
 */
public class ListCommand extends ShibaCommand {
    /**
     * Constructor for ListCommand, which lists all tasks
     *
     * @param tasks Current state of task list
     */
    public ListCommand(PersistentTaskList tasks) {
        super(tasks);
    }

    @Override
    public void execute() {
        for (int i = 0; i < tasks.size(); i++) {
            Replier.printWithNoIndents((i + 1) + ". " + tasks.get(i));
        }
        if (tasks.size() == 0) {
            Replier.printWithNoIndents("Woof! You have no tasks in the list - go browse some Reddit!");
        }
        Replier.reply();
    }
}
