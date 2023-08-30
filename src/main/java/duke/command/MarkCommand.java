package duke.command;

import duke.exception.DukeException;
import duke.main.Storage;
import duke.main.TaskList;
import duke.main.Ui;
import duke.task.Task;

public class MarkCommand extends Command {
    private int index; //index of task to mark
    public MarkCommand(int index) {
        this.index = index - 1;
    }
    @Override
    public void execute(TaskList taskList, Ui ui, Storage storage) throws DukeException {
        if (index >= 0 && index < taskList.size()) {
            Task markTask = taskList.get(index);
            taskList.markDone(index);
            ui.printMessage("Nice! I've marked this task as done:\n\t",markTask);
            storage.saveTasksToFile(taskList);
        } else {
            throw new DukeException("☹ OOPS!!! Please provide a valid task index to mark.");
        }
    }
}
