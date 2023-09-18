package duke.command;

import duke.Storage;
import duke.Ui;
import duke.exceptions.InvalidCommandException;
import duke.task.TaskList;

public class FindCommand extends Command {
    protected static final String regex = "^find\\s([\\w\\s]*)$";
    public FindCommand(String response) {
        super(response, regex);
    }

    @Override
    public String execute(Storage storage, Ui ui, TaskList taskList) throws InvalidCommandException {
        if (!matcher.find() || matcher.groupCount() != 1) {
            throw new InvalidCommandException(
                    "Invalid input. Usage: find <description to match>"
            );
        }
        String searchString = matcher.group(1);
        return taskList.getSearchTask(searchString);
    }
}
