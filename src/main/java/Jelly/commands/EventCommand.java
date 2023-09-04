package Jelly.commands;

import Jelly.main.Storage;
import Jelly.main.TaskList;
import Jelly.main.Ui;
import Jelly.task.Event;

public class EventCommand extends Command {

    private String description;

    private String fromWhen;

    private String toWhen;

    public EventCommand(String description, String fromWhen, String toWhen) {
        this.description = description;
        this.fromWhen = fromWhen;
        this.toWhen = toWhen;
    }

    @Override
    public void execute(TaskList taskList, Ui ui, Storage storage) {
        Event eventTask = new Event(description, fromWhen, toWhen);
        taskList.add(eventTask);
        ui.addedTaskMessage(eventTask, taskList.size());
        storage.saveAndExit(taskList);
    }
}
