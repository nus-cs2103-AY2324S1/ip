package dook.command;

import dook.Dook;
import dook.DookException;
import dook.services.Storage;
import dook.services.TaskList;
import dook.services.UiDisplay;

public class ByeCommand extends Command{
    public ByeCommand() {
        this.isExit = true;
    }
    @Override
    public void execute(Storage storage, UiDisplay uiDisplay, TaskList taskList) throws DookException {
        try {
            storage.save(taskList);
        } catch (DookException e) {
            uiDisplay.printMessage(e.getMessage());
        } finally {
            uiDisplay.bidFarewell();
        }
    }

}
