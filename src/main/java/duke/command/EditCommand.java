package duke.command;

import duke.DukeException;
import duke.Storage;
import duke.Ui;
import duke.task.DukeList;
import duke.task.Task;

public class EditCommand extends Command {

    private String taskType;
    private String description;

    public EditCommand(String taskType, String description) {
        this.description = description;
    }

    @Override
    public String execute(DukeList dukeList, Storage storage) throws DukeException {
        Task taskToEdit;
        try {
            assert description != null : "Description of task cannot be null";
            taskToEdit = dukeList.editTask(this.taskType, this.description);
            storage.saveData(dukeList.getList());
            assert taskToEdit != null : "Task to be added cannot be null";
        } catch (DukeException e) {
            throw new DukeException(e.getMessage());
        }
        return Ui.editMsg(taskToEdit, dukeList.getList());
    }
}
