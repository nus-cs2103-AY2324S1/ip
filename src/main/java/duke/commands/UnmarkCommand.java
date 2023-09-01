package duke.commands;

import java.util.regex.Matcher;

import duke.TaskListStorage;
import duke.exceptions.InvalidIndexException;

public class UnmarkCommand implements Command {

    private int index;

    public UnmarkCommand(Matcher matcher) {
        this.index = Integer.parseInt(matcher.group("index")) - 1;
    }

    @Override
    public void execute(TaskListStorage taskListStorage) throws InvalidIndexException {
        taskListStorage.markAsUndone(index);
    }

}
