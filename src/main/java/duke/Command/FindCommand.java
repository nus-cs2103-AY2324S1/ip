package duke.Command;

import duke.Exception.DukeException;
import duke.Storage;
import duke.TaskList;
import duke.Ui;
import duke.task.Task;

import java.io.IOException;

public class FindCommand extends Command {
    private String keyword;

    public FindCommand(String keyword) {
        this.keyword = keyword;
    }

    @Override
    public void execute(Ui ui, Storage storage, TaskList tasks) throws DukeException, IOException {

        TaskList foundTasks = new TaskList();
        for (int i = 0; i < tasks.getSize(); i++) {
            Task task = tasks.getTask(i);
            if (task.getDescription().contains(keyword)) {
                foundTasks.addTask(task);
            }
        }

        if (foundTasks.getSize() > 0) {
            ui.printFindTask(foundTasks);
        } else {
            System.out.println("\tNo tasks found containing the keyword: " + keyword);
        }

    }

    @Override
    public boolean isExit() {
        return false;
    }
}
