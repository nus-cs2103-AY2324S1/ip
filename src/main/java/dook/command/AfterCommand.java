package dook.command;

import java.time.LocalDate;

import dook.DookException;
import dook.services.Storage;
import dook.services.TaskList;
import dook.services.UiDisplay;

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
     * @param uiDisplay Given UI display.
     * @param taskList Given task list.
     * @throws DookException Exception thrown by Dook.
     */
    @Override
    public void execute(Storage storage, UiDisplay uiDisplay, TaskList taskList) throws DookException {
        uiDisplay.printMessage(taskList.getTasksAfter(localDate));
    }
}
