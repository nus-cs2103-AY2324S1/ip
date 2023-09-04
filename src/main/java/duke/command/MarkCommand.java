package duke.command;

import duke.Ui;
import duke.command.Command;
import duke.Storage;
import duke.task.*;

/**
 * Marks a task as either done or not done
 */
public class MarkCommand extends Command {
    protected boolean done;
    protected int taskIndex;
    public MarkCommand(boolean done, int taskIndex) {
        super();
        this.done = done;
        this.taskIndex = taskIndex;
    }

    @Override
    public void execute(Storage storage, Ui ui, TaskList taskList) {
        taskList.setTaskDone(taskIndex, done);
        System.out.println(taskList);
        ui.printLine();
    }
}
