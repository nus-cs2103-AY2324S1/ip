package jelly.commands;

import jelly.main.Storage;
import jelly.main.TaskList;
import jelly.main.Ui;
import jelly.task.Deadline;

public class DeadlineCommand extends Command {
    private String description;
    private String byWhen;

    public DeadlineCommand(String description, String byWhen) {
        this.description = description;
        this.byWhen = byWhen;
    }
    @Override
    public String execute(TaskList taskList, Ui ui, Storage storage) {
        Deadline deadlineTask = new Deadline(description, byWhen);
        taskList.add(deadlineTask);
        storage.saveAndExit(taskList);
        return ui.addedTaskMessage(deadlineTask, taskList.size());
        //    storage.saveAndExit(taskList);
        //initially here
    }
}
