package duke.command;

import duke.util.Storage;
import duke.util.TaskList;
import duke.util.Ui;

public class FindCommand extends Command {
    private String queryString;

    public FindCommand(String queryString) {
        super(false);
        this.queryString = queryString;
    }

    @Override
    public void execute(TaskList list, Ui ui, Storage storage) {
        TaskList taskList = list.find(this.queryString);
        ui.printFind(taskList);
    }
}