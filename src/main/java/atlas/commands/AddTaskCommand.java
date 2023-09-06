package atlas.commands;

import java.io.IOException;

import atlas.components.Storage;
import atlas.components.TaskList;
import atlas.components.Ui;
import atlas.tasks.Task;


/**
 * Command to add task to task list
 */
public class AddTaskCommand extends Command {
    protected final Task task;

    /**
     * Constructs an AddTaskCommand
     * @param t Task to be added
     */
    public AddTaskCommand(Task t) {
        task = t;
    }

    @Override
    public void execute(TaskList taskList, Ui ui, Storage storage) {
        taskList.addTask(task);
        try {
            storage.save(taskList);
            ui.printToScreen("Got it. I've added this task:\n"
                    + "\t" + task);
            ui.printToScreen(taskList.getCountString());
        } catch (IOException e) {
            ui.showError(e.getMessage());
        }
    }

    @Override
    public String execute(TaskList taskList, Storage storage) {
        try {
            taskList.addTask(task);
            storage.save(taskList);
            return String.format("Got it. I've added this task:\n"
                    + "\t%s\n"
                    + "%s", task, taskList.getCountString());
        } catch (IOException e) {
            return e.getMessage();
        }
    }
}
