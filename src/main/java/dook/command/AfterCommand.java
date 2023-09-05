package dook.command;

import java.time.LocalDate;

import dook.DookException;
import dook.services.Storage;
import dook.services.TaskList;
import dook.task.TimedTask;

/**
 * Command for getting all tasks after a certain date.
 */
public class AfterCommand extends Command {
    private final LocalDate localDate;

    public AfterCommand(LocalDate localDate) {
        this.localDate = localDate;
    }

    /**
     * Displays a list of all tasks in the given task list that occur after the stored
     * local date.
     * @param storage Given storage.
     * @param taskList Given task list.
     * @return  Message to be displayed in GUI.
     * @throws DookException Exception thrown by Dook.
     */
    @Override
    public String execute(Storage storage, TaskList taskList) throws DookException {
        return taskList.filterTasks((task) -> {
            if (task instanceof TimedTask) {
                TimedTask t = (TimedTask) task;
                return t.isAfter(localDate);
            }
            return false;
        });
    }
}
