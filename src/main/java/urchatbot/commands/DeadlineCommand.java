package urchatbot.commands;

import urchatbot.storage.Storage;
import urchatbot.taskList.TaskList;
import urchatbot.tasks.Deadline;
import urchatbot.tasks.Task;
import urchatbot.ui.Ui;

/**
 * Adds Deadline type of task.
 */
public class DeadlineCommand extends Command {
    private String by;

    /**
     * Constructs the DeadlineCommand class.
     *
     * @param taskDescription Task description of the task.
     * @param by The date and/or time of the deadline of the task.
     */
    public DeadlineCommand(String taskDescription, String by) {
        super(taskDescription);
        this.by = by;
    }

    public String getBy() {
        return by;
    }

    @Override
    public String execute(TaskList tasks, Ui ui, Storage storage) {
        Task newTask = new Deadline(getTaskDescription(), false, by);
        tasks.addTask(newTask);
        Storage.save(tasks);
        int taskSize = tasks.getSize();
        if (taskSize == 1 || taskSize == 0) {
            return ui.showDeadlineMessage(newTask.toString(), taskSize);
        } else {
            return ui.showDeadlineMessagePlural(newTask.toString(), taskSize);
        }
    }
}
