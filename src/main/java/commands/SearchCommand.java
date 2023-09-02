package commands;

import functions.*;
import java.util.ArrayList;
import tasks.*;

public class SearchCommand extends Command {
    String searchWord;

    public SearchCommand(String searchWord) {
        this.searchWord = searchWord;
    }

    public void execute(TaskList tasks, Ui ui, Storage storage) {
        ArrayList<Task> matches = tasks.searchTask(this.searchWord);
        ui.showMatchesMsg(matches);
    }
}
