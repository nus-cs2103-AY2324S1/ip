package duke.command;

import duke.DukeException;
import duke.Storage;
import duke.Ui;
import duke.task.Task;
import duke.task.TaskList;

import java.util.ArrayList;

public class FindCommand extends Command{
    private String taskToFindDetails;

    public FindCommand(String args) {
        this.taskToFindDetails = args;
    }

    @Override
    public void execute(TaskList taskList, Ui ui, Storage storage) throws DukeException {
        ArrayList<Task> foundTasks = taskList.findTasks(taskToFindDetails);
        TaskList taskListWithFoundTasks = new TaskList(foundTasks);
        ui.printTaskList(taskListWithFoundTasks, true);
    }
}
