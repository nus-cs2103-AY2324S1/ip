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
    public void execute(TaskList tasks, Ui ui, Storage store) throws DukeException {
        try {
            String res = tasks.findTasks(this.keyword);
            if (res.isEmpty()) {
                ui.printEmptyFind(this.keyword);
            } else {
                ui.printFindRes(res);
            }
            store.save(tasks);
        } catch (IOException e) {
            throw new DukeException(" umable to locate local file!");
        }
    }
}
