package commands;

import components.DukeException;
import components.Storage;
import components.Ui;
import tasks.Event;
import tasks.TaskList;

public class EventCommand extends Command {
    private String command;

    public EventCommand(String command) {
        this.command = command;
    }

    @Override
    public String execute(TaskList list, Ui ui, Storage storage) throws DukeException {
        return list.addTask(new Event(command.substring(6)), storage);
    }
}
