package duke.command;

import duke.Storage;
import duke.task.TaskList;

public abstract class Command {
    public abstract void execute(TaskList taskList, Storage storage);

    public abstract void printCommand(TaskList taskList);

    public abstract boolean isContinue();
}
