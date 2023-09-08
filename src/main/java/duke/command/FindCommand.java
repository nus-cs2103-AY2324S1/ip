package duke.command;

import duke.exception.DukeException;
import duke.list.FunnyList;
import duke.storage.Storage;
import duke.task.Task;
import duke.ui.Ui;

import java.util.ArrayList;

public class FindCommand extends Command {

    public static final String COMMAND_WORD = "find";

    private String search;

    public FindCommand(String search) {
        this.search = search;
    }

    @Override
    public String execute(FunnyList taskList, Ui ui, Storage storage) throws DukeException {
        ArrayList<Task> matchingTasks = taskList.findTasks(this.search);
        return ui.showMatchingTasks(matchingTasks);
    }
}
