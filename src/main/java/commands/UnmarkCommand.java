package commands;

import exceptions.ShibaException;
import tasks.ShibaTask;
import tasks.TaskList;
import ui.Replier;

public class UnmarkCommand extends ShibaCommand {
    private final String[] params;

    /**
     * Constructor for UnmarkCommand, which marks a task as not done
     *
     * @param tasks Current state of task list
     * @param cmd Full command string
     */
    public UnmarkCommand(TaskList tasks, String cmd) {
        super(tasks);
        params = cmd.split(" ");
    }

    @Override
    public void execute() throws ShibaException {
        int taskNumber = checkTaskNumber(params);

        ShibaTask task = tasks.get(taskNumber - 1);
        boolean res = task.markNotDone();
        Replier.printHorizontalLine();
        if (res) {
            Replier.printWithLevel2Indent("Woof! I've marked this task as not done yet:");
        } else {
            Replier.printWithLevel2Indent("Woof! You have not done this task yet!");
        }
        Replier.printWithLevel3Indent(task.toString());
        Replier.printHorizontalLine();
        tasks.save();
    }
}
