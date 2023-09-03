package jo.command;

import jo.JoException;
import jo.Storage;
import jo.TaskList;
import jo.Ui;

public class SearchCommand extends Command {

    private String keyword;

    public SearchCommand(String keyword) {
        this.keyword = keyword;
    }
    @Override
    public void execute(TaskList tasks, Ui ui, Storage storage) throws JoException {
        ui.findResult(tasks.find(this.keyword));
    }

    @Override
    public boolean isExit() {
        return false;
    }
}
