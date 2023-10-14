package shiba.commands;

import shiba.exceptions.ShibaException;
import shiba.tasks.PersistentTaskList;
import shiba.tasks.ShibaTask;
import shiba.ui.Replier;

/**
 * Represents a command to mark a task as done
 */
public class MarkCommand extends ShibaCommand {
    private final String[] params;

    /**
     * Constructor for MarkCommand, which marks a task as done
     *
     * @param tasks Current state of task list
     * @param cmd Full command string
     */
    public MarkCommand(PersistentTaskList tasks, String cmd) {
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

        boolean res = task.markDone();
        if (res) {
            Replier.printWithNoIndents("Woof! I've marked this task as done:");
        } else {
            Replier.printWithNoIndents("Woof! This task is already done!");
        }
        Replier.printWithOneIndent(task.toString());
        Replier.reply();

        tasks.save();
    }
}
