package duke.command;

import duke.task.TaskList;
import duke.utility.Storage;
import duke.utility.Ui;

public class FindCommand extends Command{
    private String keyword;

    public FindCommand(String keyword) {
        this.keyword = keyword;
    }

    @Override
    public void execute(TaskList taskList, Storage storage, Ui ui) {
        taskList.findTasks(this.keyword, ui);
    }
}
