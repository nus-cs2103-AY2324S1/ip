package commands;

import exceptions.ShibaException;
import tasks.ShibaTask;
import tasks.TaskList;
import ui.Replier;

public class MarkCommand extends ShibaCommand {
    private final String[] params;

    /**
     * Constructor for MarkCommand, which marks a task as done
     *
     * @param tasks Current state of task list
     * @param cmd Full command string
     */
    public MarkCommand(TaskList tasks, String cmd) {
        super(tasks);
        params = cmd.split(" ");
    }

    @Override
    public void execute() throws ShibaException {
        int taskNumber = checkTaskNumber(params);

        ShibaTask task = tasks.get(taskNumber - 1);
        boolean res = task.markDone();
        Replier.printHorizontalLine();
        if (res) {
            Replier.printWithLevel2Indent("Woof! I've marked this task as done:");
        } else {
            Replier.printWithLevel2Indent("Woof! This task is already done!");
        }
        Replier.printWithLevel3Indent(task.toString());
        Replier.printHorizontalLine();
        tasks.save();
    }
}
