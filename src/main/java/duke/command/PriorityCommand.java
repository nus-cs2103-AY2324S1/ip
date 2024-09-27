package duke.command;

import duke.task.Task;
import duke.util.Storage;
import duke.util.TaskList;
import duke.util.Ui;

public class PriorityCommand extends Command {

    private Task.Prioritylist priority;

    private int number;
    public PriorityCommand(Task.Prioritylist priority, int number) {
        this.priority = priority;
        this.number = number;
    }

    @Override
    public String executeTask(TaskList taskList, Ui ui, Storage storage) {
        taskList.getTask(number).setPriority(this.priority);
        String toBeWritten = taskList.toWrite();
        storage.write(toBeWritten);
        return ui.printPriorityMessage(this.priority);
    }

    @Override
    public boolean isExit() {
        return false;
    }

}
