package duke.command;

import duke.DukeException;
import duke.Storage;
import duke.TaskList;
import duke.Ui;
import duke.task.Event;
import duke.task.Task;

import java.time.LocalDate;

public class EventCommand extends Command {

    private LocalDate from;

    private LocalDate to;

    private String description;

    public EventCommand(String description, LocalDate from, LocalDate to) {
        this.description = description;
        this.from = from;
        this.to = to;
    }

    @Override
    public void execute(TaskList taskList, Ui ui, Storage storage) throws DukeException {
        Task temp = new Event(description, from, to);
        taskList.addTask(temp);
        ui.printAddTaskMessage(temp, taskList);
        storage.save(taskList);
    }

    @Override
    public boolean equals(Object obj) {
        if (obj == null) {
            return false;
        }
        if (obj instanceof UnmarkCommand) {
           EventCommand temp = (EventCommand) obj;
            if (temp.from.equals(this.from) && temp.to.equals(this.to) && this.description.equals(temp.description)) {
                return true;
            }
        }
        return false;
    }
}
