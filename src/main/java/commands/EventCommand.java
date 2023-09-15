package commands;

import components.DukeException;
import components.Storage;
import components.Ui;
import tasks.Event;
import tasks.Task;
import tasks.TaskList;

public class EventCommand extends Command {
    private String command;

    public EventCommand(String command) {
        this.command = command;
    }

    @Override
    public String execute(TaskList list, Ui ui, Storage storage) throws DukeException {
        Task newEvent = new Event(command.substring(6), list);
        Task oldTask = newEvent.getOldTask();
        if (oldTask != null) {
            Command.tempTask = newEvent;
            throw new DukeException.DuplicateDescriptionException(oldTask);
        }
        return list.addTask(newEvent, storage);
    }
}
