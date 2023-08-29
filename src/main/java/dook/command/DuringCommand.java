package dook.command;

import java.time.LocalDate;

import dook.DookException;
import dook.services.Storage;
import dook.services.TaskList;
import dook.services.UiDisplay;


public class DuringCommand extends Command {
    private final LocalDate localDate;
    public DuringCommand(LocalDate localDate) {
        this.localDate = localDate;
    }
    @Override
    public void execute(Storage storage, UiDisplay uiDisplay, TaskList taskList) throws DookException {
        uiDisplay.printMessage(taskList.getTasksDuring(localDate));
    }
}
