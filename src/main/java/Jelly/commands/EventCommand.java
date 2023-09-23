package jelly.commands;

import jelly.main.Storage;
import jelly.main.TaskList;
import jelly.main.Ui;

import jelly.task.Event;

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
    public String execute(TaskList taskList, Ui ui, Storage storage) {
        Event eventTask = new Event(description, fromWhen, toWhen);
        taskList.add(eventTask);
        storage.saveAndExit(taskList);
        return ui.addedTaskMessage(eventTask, taskList.size());
    }
}
