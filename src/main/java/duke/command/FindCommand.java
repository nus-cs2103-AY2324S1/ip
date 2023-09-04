package duke.command;

import duke.Storage;
import duke.Ui;
import duke.task.TaskList;

public class FindCommand extends Command {
    protected String searchString;
    public FindCommand(String searchString) {
        this.searchString = searchString;
    }

    @Override
    public void execute(Storage storage, Ui ui, TaskList taskList) {
        System.out.println(taskList.getSearchTask(this.searchString));
        ui.printLine();
    }
}
