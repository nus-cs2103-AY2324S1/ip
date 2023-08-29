package duke.commands;

import duke.Storage;
import duke.TaskList;


public class FindCommand extends Command {
    private String prefix;

    public FindCommand(String prefix) {
        this.prefix = prefix;
    }

    @Override
    public void execute(TaskList taskList, Storage storage) {

    }
}
