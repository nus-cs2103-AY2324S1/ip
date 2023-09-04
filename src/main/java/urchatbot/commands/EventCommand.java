package urchatbot.commands;

import urchatbot.storage.Storage;
import urchatbot.taskList.TaskList;
import urchatbot.tasks.Event;
import urchatbot.tasks.Task;
import urchatbot.ui.Ui;

public class EventCommand extends Command{
    public String from;
    public String to;
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
    @Override
    public void execute(TaskList tasks, Ui ui, Storage storage) {
        Task newTask = new Event(taskDescription, false, from, to);
        tasks.addTask(newTask);
        Storage.save(tasks);
        int taskSize = tasks.getSize();
        if (taskSize == 1 || taskSize ==0) {
            ui.showEventMessage(newTask.toString(), taskSize);
        } else {
            ui.showEventMessagePlural(newTask.toString(), taskSize);
        }
    }

}
