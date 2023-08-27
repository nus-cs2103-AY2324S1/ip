package duke.command;

import duke.exception.DukeException;
import duke.storage.Storage;
import duke.task.Task;
import duke.task.TaskList;
import duke.ui.Ui;
public class DeleteCommand extends Command {
    private final int index;

    public DeleteCommand(String input) throws DukeException {
        if (input == null) {
            throw new DukeException(" ☹ Which task?");
        }
        this.index = Integer.parseInt(input.strip());
    }

    @Override
    public void execute(TaskList taskList, Ui ui, Storage storage) throws DukeException {
        Task deletedTask = taskList.deleteTask(index);
        ui.showMessage(String.format("Noted. I've removed this task:\n    " +
                "%s\nNow you have %d tasks in the list.", deletedTask, taskList.getListSize()));
        storage.writeFile(taskList.stringToFile());
    }
}
