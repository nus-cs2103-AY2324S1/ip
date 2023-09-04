package shiba.commands;

import shiba.exceptions.ShibaException;
import shiba.tasks.PersistentTaskList;
import shiba.tasks.ShibaTask;
import shiba.ui.Replier;

/**
 * Represents a command to mark a task as not done
 */
public class UnmarkCommand extends ShibaCommand {
    private final String[] params;

    /**
     * Constructor for UnmarkCommand, which marks a task as not done
     *
     * @param tasks Current state of task list
     * @param cmd Full command string
     */
    public UnmarkCommand(PersistentTaskList tasks, String cmd) {
        super(tasks);
        params = cmd.split(" ");
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void execute() throws ShibaException {
        int taskNumber = checkTaskNumber(params);

        ShibaTask task = tasks.get(taskNumber - 1);
        boolean res = task.markNotDone();
        if (res) {
            Replier.printWithNoIndents("Woof! I've marked this task as not done yet:");
        } else {
            Replier.printWithNoIndents("Woof! You have not done this task yet!");
        }
        Replier.printWithOneIndent(task.toString());
        Replier.reply();
        tasks.save();
    }
}
