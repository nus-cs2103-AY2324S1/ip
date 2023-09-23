package jelly.commands;

import jelly.main.Storage;
import jelly.main.TaskList;
import jelly.main.Ui;
import jelly.task.Event;

/**
 * Responsible for the logic regarding event tasks.
 */
public class EventCommand extends Command {

    private String description;

    private String fromWhen;

    private String toWhen;

    /**
     * @param description The details of the event.
     * @param fromWhen The start date/time of the event.
     * @param toWhen The end date/time of the event.
     */
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
        return ui.showTaskAdded(eventTask, taskList.size());
    }
}
