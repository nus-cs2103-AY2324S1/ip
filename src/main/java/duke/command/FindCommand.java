package duke.command;

import duke.helper.Storage;
import duke.helper.Ui;
import duke.task.DukeException;
import duke.task.TaskList;

import java.io.IOException;

public class FindCommand extends Command {

    String keyword;

    public FindCommand(String keyword) {
        this.keyword = keyword;
    }

    @Override
    public void execute(TaskList tasks, Ui ui, Storage store) throws DukeException {
        try {
            tasks.findTasks(this.keyword);
            store.save(tasks);
        } catch (IOException e) {
            throw new DukeException(" umable to locate local file!");
        }
    }
}
