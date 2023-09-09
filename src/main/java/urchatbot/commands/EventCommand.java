package urchatbot.commands;

import urchatbot.storage.Storage;
import urchatbot.taskList.TaskList;
import urchatbot.tasks.Event;
import urchatbot.tasks.Task;
import urchatbot.ui.Ui;

/**
 * Adds Event type of task.
 */
public class EventCommand extends Command {
    private String from;
    private String to;
    /**
     * Constructs the EventCommand class.
     *
     * @param taskDescription Task description of the task.
     * @param from Starting date and/or time of the task.
     * @param to Ending date and/or time of the task.
     */
    public EventCommand(String taskDescription, String from, String to) {
        super(taskDescription);
        this.from = from;
        this.to = to;
    }

    public String getFrom() {
        return from;
    }

    public String getTo() {
        return to;
    }

    @Override
    public String execute(TaskList tasks, Ui ui, Storage storage) {
        Task newTask = new Event(getTaskDescription(), false, from, to);
        tasks.addTask(newTask);
        Storage.save(tasks);
        int taskSize = tasks.getSize();
        if (taskSize == 1 || taskSize == 0) {
            return ui.showEventMessage(newTask.toString(), taskSize);
        } else {
            return ui.showEventMessagePlural(newTask.toString(), taskSize);
        }
    }

}
