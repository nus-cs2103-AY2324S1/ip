package duke.command;

import java.time.LocalDateTime;

import duke.DukeException;
import duke.TaskList;
import duke.Ui;
import duke.Storage;
import duke.task.Task;
import duke.task.ToDo;
import duke.task.Deadline;

public class UpdateCommand extends Command {
    protected int index;
    protected String description;
    protected LocalDateTime firstDate;
    protected LocalDateTime secondDate;
    protected boolean isFirst;

    public UpdateCommand(int index, String description) {
        this.index = index - 1;
        this.description = description;
    }

    public UpdateCommand(int index, LocalDateTime date) {
        this.index = index - 1;
        this.firstDate = date;
    }

    public UpdateCommand(int index, LocalDateTime date, boolean isFirst) {
        this.index = index - 1;
        this.firstDate = date;
        this.isFirst = isFirst;
    }

    public UpdateCommand(int index, LocalDateTime firstDate, LocalDateTime secondDate) {
        this.index = index - 1;
        this.firstDate = firstDate;
        this.secondDate = secondDate;
    }

    public UpdateCommand(int index, String description, LocalDateTime date, boolean isFirst) {
        this.index = index - 1;
        this.description = description;
        this.firstDate = date;
        this.isFirst = isFirst;
    }

    public UpdateCommand(int index, String description, LocalDateTime firstDate, LocalDateTime secondDate) {
        this.index = index - 1;
        this.description = description;
        this.firstDate = firstDate;
        this.secondDate = secondDate;
    }

    @Override
    public void execute(TaskList taskList, Ui ui, Storage storage) throws DukeException {
        if (index < 0 || index >= taskList.size()) {
            throw new DukeException("OOPS! The index to change is invalid!");
        }
        Task currTask = taskList.get(this.index);
        if (currTask instanceof ToDo) {
            Command newCommand = new UpdateToDoCommand(index, description);
            newCommand.execute(taskList, ui, storage);
        } else if (currTask instanceof Deadline) {
            if (this.description != null) {
                if (this.firstDate != null) {
                    Command newCommand = new UpdateDeadlineCommand(index, description, firstDate);
                    newCommand.execute(taskList, ui, storage);
                } else {
                    Command newCommand = new UpdateDeadlineCommand(index, description);
                    newCommand.execute(taskList, ui, storage);
                }
            } else {
                Command newCommand = new UpdateDeadlineCommand(index, firstDate);
                newCommand.execute(taskList, ui, storage);
            }
        } else {
            if (this.description != null) {
                if (this.firstDate != null) {
                    if (this.secondDate != null) {
                        Command newCommand = new UpdateEventCommand(index, description, firstDate, secondDate);
                        newCommand.execute(taskList, ui, storage);
                    } else {
                        Command newCommand = new UpdateEventCommand(index, description, firstDate, isFirst);
                        newCommand.execute(taskList, ui, storage);
                    }
                } else {
                    Command newCommand = new UpdateEventCommand(index, description);
                    newCommand.execute(taskList, ui, storage);
                }
            } else {
                if (this.firstDate != null) {
                    if (this.secondDate != null) {
                        Command newCommand = new UpdateEventCommand(index, firstDate, secondDate);
                        newCommand.execute(taskList, ui, storage);
                    } else {
                        Command newCommand = new UpdateCommand(index, firstDate, isFirst);
                        newCommand.execute(taskList, ui, storage);
                    }
                }
            }
        }
    }
}
