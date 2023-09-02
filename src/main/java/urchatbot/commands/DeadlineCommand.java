package urchatbot.commands;

import urchatbot.storage.Storage;
import urchatbot.taskList.TaskList;
import urchatbot.tasks.Deadline;
import urchatbot.tasks.Task;
import urchatbot.ui.Ui;

public class DeadlineCommand extends Command{
    public String by;
    public DeadlineCommand(String taskDescription, String by) {
        super(taskDescription);
        this.by = by;
    }
    @Override
    public void execute(TaskList tasks, Ui ui, Storage storage) {
        Task newTask = new Deadline(taskDescription, false, by);
        tasks.addTask(newTask);
        Storage.save(tasks);
        int taskSize = tasks.getSize();
        if (taskSize == 1 || taskSize ==0) {
            ui.showDeadlineMessage(taskDescription, taskSize);
        } else {
            ui.showDeadlineMessagePlural(taskDescription, taskSize);
        }
    }
}
