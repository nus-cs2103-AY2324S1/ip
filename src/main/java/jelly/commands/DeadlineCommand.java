package jelly.commands;

import jelly.main.Storage;
import jelly.main.TaskList;
import jelly.main.Ui;
import jelly.task.Deadline;

/**
 * Responsible for the logic regarding deadline tasks.
 */
public class DeadlineCommand extends Command {
    private String description;
    private String byWhen;

    /**
     * @param description The details of the task.
     * @param byWhen The deadline of the task.
     */
    public DeadlineCommand(String description, String byWhen) {
        this.description = description;
        this.byWhen = byWhen;
    }
    @Override
    public String execute(TaskList taskList, Ui ui, Storage storage) {
        Deadline deadlineTask = new Deadline(description, byWhen);
        taskList.add(deadlineTask);
        storage.saveAndExit(taskList);
        return ui.showTaskAdded(deadlineTask, taskList.size());
    }
}
