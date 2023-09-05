package dook.command;

import java.time.LocalDate;

import dook.DookException;
import dook.services.Storage;
import dook.services.TaskList;
import dook.task.TimedTask;


/**
 * Command for getting all tasks during a certain date.
 */
public class DuringCommand extends Command {
    private final LocalDate localDate;
    public DuringCommand(LocalDate localDate) {
        this.localDate = localDate;
    }
    /**
     * Displays a list of all tasks in the given task list that occur during the stored
     * local date.
     * @param storage Given storage.
     * @param taskList Given task list.
     * @throws DookException Exception thrown by Dook.
     * @return  Message to be displayed in GUI.
     */
    @Override
    public String execute(Storage storage, TaskList taskList) throws DookException {
        return taskList.filterTasks((task) -> {
            if (task instanceof TimedTask) {
                TimedTask t = (TimedTask) task;
                return t.isDuring(localDate);
            }
            return false;
        });
    }
}
