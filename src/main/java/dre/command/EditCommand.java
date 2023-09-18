package dre.command;

import dre.exception.DreException;
import dre.storage.Storage;
import dre.task.Deadline;
import dre.task.Event;
import dre.ui.Ui;
import dre.task.Task;
import dre.task.TaskList;

public class EditCommand extends Command {
    private final int TASK_NUMBER;
    private String field;
    private String newValue;

    public EditCommand(int TASK_NUMBER, String field, String newValue) {
        this.TASK_NUMBER = TASK_NUMBER;
        this.field = field;
        this.newValue = newValue;
    }

    @Override
    public String execute(TaskList tasks, Ui ui, Storage storage) throws DreException {
        Task task = tasks.getTask(TASK_NUMBER);
        switch (field) {
            case "description":
                task.editDescription(newValue);
                break;
            case "bydate":
                ((Deadline) task).editByDate(newValue);
                break;
            case "fromdate":
                ((Event) task).editFromDate(newValue);
                break;
            case "todate":
                ((Event) task).editToDate(newValue);
                break;
        }
        return ui.generateEditSuccessMessage(task);
    }
}