package duke.assets.commands;

import duke.data.TaskList;
import duke.dukeexceptions.InvalidCommandException;

public abstract class CommandAbstract {
    public static String horizontal = "------------------------------------------------------------" +
            "---------------------------";
    protected String input;

    public CommandAbstract(String input) {
        this.input = input;
    }
    public void execute(TaskList taskList) throws InvalidCommandException {
        if (isValid()) {
            completeOperation(taskList);
        } else {
            throw new InvalidCommandException(this.input);
        }
    }

    protected abstract boolean isValid();

    protected abstract void completeOperation(TaskList taskList);
}
