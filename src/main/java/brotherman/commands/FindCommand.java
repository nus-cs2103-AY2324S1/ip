package brotherman.commands;

import brotherman.storage.Storage;
import brotherman.tasks.TaskList;
import brotherman.ui.Ui;
public class FindCommand extends Command {

    private String keyword;

    public FindCommand(String keyword) {
        super(false);
        this.keyword = keyword;
    }

    @Override
    public void execute(TaskList task, Ui ui, Storage storage) {
        ui.showFind(task, keyword);
    }
}
