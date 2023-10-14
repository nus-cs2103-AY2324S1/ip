package shiba.commands;

import shiba.exceptions.ShibaException;
import shiba.tasks.PersistentTaskList;
import shiba.tasks.ShibaTask;
import shiba.ui.Replier;

/**
 * Represents a command to delete a task from the task list
 */
public class DeleteCommand extends ShibaCommand {
    private final String[] params;

    /**
     * Constructor for DeleteCommand, which deletes a task from the task list
     *
     * @param tasks Current state of task list
     * @param cmd Full command string
     */
    public DeleteCommand(PersistentTaskList tasks, String cmd) {
        super(tasks);
        params = cmd.split(" ");
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void execute() throws ShibaException {
        int taskNumber = checkTaskNumber(params);
        ShibaTask task = tasks.removeIndex(taskNumber - 1);

        Replier.printWithNoIndents("Woof! I've deleted this task:");
        Replier.printWithOneIndent(task.toString());
        String taskWord = tasks.size() == 1 ? " task" : " tasks";
        Replier.printWithNoIndents("You now have " + tasks.size() + taskWord
                + " in the list. Some headpats please?");
        Replier.reply();

        tasks.save();
    }
}
