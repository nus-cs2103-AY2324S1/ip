package duke.command;

import duke.exception.ChatException;
import duke.storage.Storage;
import duke.task.TaskList;
import duke.ui.Ui;

public class FindCommand extends Command {
    private String keyword;
    public FindCommand(String keyword) {
        this.keyword = keyword;
    }
    public String execute(TaskList tasks, Ui ui, Storage storage){
        TaskList foundList = tasks.findTask(this.keyword);
        try {
            return ui.listMatchingTaskResponse(foundList);
        } catch (ChatException e) {
            return ui.showLoadingError(e);
        }
    }
}
