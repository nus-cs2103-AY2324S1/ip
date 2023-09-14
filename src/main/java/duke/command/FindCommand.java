package duke.command;


import duke.RichieException;
import duke.Storage;
import duke.TaskList;
import duke.Ui;
import duke.task.Task;
import java.util.ArrayList;

public class FindCommand extends Command {
    private String keyword;
    private int LIMIT = 2;

    public FindCommand(String keyword) {
        this.keyword = keyword;
    }
    @Override
    public void execute(Ui ui, Storage storage, TaskList taskList) throws RichieException {
        ArrayList<Task> tasks;
        tasks = taskList.filterTaskByKeyword(this.keyword, this.LIMIT);
        ui.showFilteredTasksList(tasks);
    }
}
