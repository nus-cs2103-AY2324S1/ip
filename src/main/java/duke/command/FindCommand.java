package duke.command;

import duke.Storage;
import duke.Ui;
import duke.task.TaskList;

public class FindCommand extends Command {
    private String keyword;

    public FindCommand(String keyword) {
        this.keyword = keyword;
    }

    public void execute(TaskList tasks, Ui ui, Storage storage) {
        TaskList matchingTasks = tasks.findTasks(keyword);
        ui.showMatchingTasks(matchingTasks);
    }

    public boolean isExit() {
        return false;
    }
}