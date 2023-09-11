package duke.command;

import duke.DukeException;
import duke.Storage;
import duke.TaskList;
import duke.UiManager;
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
    public String execute(TaskList taskList, UiManager uiManager, Storage storage) throws DukeException {
        assert taskList != null: "Task list should not be null";
        assert uiManager != null: "UI manager should not be null";
        assert storage != null: "Storage object should not be null";
        Task temp = new Event(description, from, to);
        taskList.addTask(temp);
        storage.save(taskList);
        return uiManager.getAddTaskMessage(temp, taskList);
    }

    @Override
    public boolean equals(Object obj) {
        if (obj == null) {
            return false;
        }
        if (obj instanceof EventCommand) {
           EventCommand temp = (EventCommand) obj;
            if (temp.from.equals(this.from) && temp.to.equals(this.to) && this.description.equals(temp.description)) {
                return true;
            }
        }
        return false;
    }
}
