package duke.Command;

import java.io.IOException;

import duke.Exception.DukeException;
import duke.Storage;
import duke.TaskList;
import duke.Ui;
import duke.task.Task;


public class FindCommand extends Command {
    private String keyword;

    public FindCommand(String keyword) {
        this.keyword = keyword;
    }

    @Override
    public String execute(Ui ui, Storage storage, TaskList tasks) throws DukeException, IOException {

        TaskList foundTasks = new TaskList();
        for (int i = 0; i < tasks.getSize(); i++) {
            Task task = tasks.getTask(i);
            if (task.getDescription().contains(keyword)) {
                foundTasks.addTask(task);
            }
        }

        if (foundTasks.getSize() > 0) {
            return ui.printFindTask(foundTasks);
        } else {
            return "\tNo tasks found containing the keyword: " + keyword;
        }

    }

    @Override
    public boolean isExit() {
        return false;
    }
}
