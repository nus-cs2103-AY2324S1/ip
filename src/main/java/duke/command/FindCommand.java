package duke.command;
import java.io.IOException;

import duke.helper.Storage;
import duke.helper.Ui;
import duke.task.DukeException;
import duke.task.TaskList;

/**
 * FindCommand that searches for tasks with the relevant keyword in it
 */

public class FindCommand extends Command {

    private final String keyword;

    public FindCommand(String keyword) {
        this.keyword = keyword;
    }

    @Override
    public String execute(TaskList tasks, Ui ui, Storage store) throws DukeException {
        try {
            assert tasks != null : "tasks is initialised properly before showing its contents";
            String res = tasks.findTasks(this.keyword);
            store.save(tasks);
            if (res.isEmpty()) {
                return ui.printEmptyFind(this.keyword);
            } else {
                return ui.printFindRes(res);
            }
        } catch (IOException e) {
            throw new DukeException(" umable to locate local file!");
        }
    }
}
