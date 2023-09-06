package duke.assets.commands;

import duke.data.TaskList;
import duke.dukeexceptions.InvalidCommandException;

public abstract class CommandAbstract {
    public static String HORIZONTAL = "------------------------------------------------------------" +
            "---------------------------";
    protected static String SLASHDATEFORMAT = "\\d{4}\\\\d{2}\\\\d{2}";
    protected static String DASHDATEFORMAT =  "\\d{4}-\\d{2}-\\d{2}";

    protected String input;


    public CommandAbstract(String input) {
        this.input = input;
    }
    public void execute(TaskList tasklist) throws InvalidCommandException {
        if (isValid(tasklist)) {
            completeOperation(tasklist);
        } else {
            throw new InvalidCommandException(this.input);
        }
    }

    protected abstract boolean isValid(TaskList tasklist);

    protected abstract void completeOperation(TaskList tasklist);
}
