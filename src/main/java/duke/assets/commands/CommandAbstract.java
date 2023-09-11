package duke.assets.commands;

import duke.assets.storage.TaskList;
import duke.dukeexceptions.InvalidCommandException;

public abstract class CommandAbstract {
    public static String HORIZONTAL = "------------------------------------------------------------" +
            "---------------------------";
    protected static final String VALID_DATE_REGEX_STRING = "(\\d{4}/\\d{2}/\\d{2}|\\d{4}-\\d{2}-\\d{2})";
    protected static final String VALID_TIME_REGEX_STRING = "[0-2][0-9][0-5][0-9]";

    protected String input;

    public CommandAbstract(String input) {
        this.input = input;
    }
    public void execute(TaskList tasklist) throws InvalidCommandException {
        if (isValid(tasklist)) {
            completeOperation(tasklist);
        } else {
            throw new InvalidCommandException();
        }
    }

    public abstract void printChatbotLine();

    protected abstract boolean isValid(TaskList tasklist);

    protected abstract void completeOperation(TaskList tasklist);
}
