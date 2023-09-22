package duke.command;
import duke.TaskList;
import duke.Storage;
import duke.Ui;

public class FindCommand extends Command {
    private String keyword;
    public FindCommand(String keyword) {
        this.keyword = keyword;
    }

    @Override
    public String execute(TaskList taskList, Storage storage, Ui ui) {
        TaskList matchingTasks= taskList.findTask(keyword);
        return ui.findTaskMessage(matchingTasks, keyword);
    }
}
