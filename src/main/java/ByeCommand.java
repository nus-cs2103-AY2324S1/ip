public class ByeCommand extends Command{
    public ByeCommand() {
        this.isExit = true;
    }
    @Override
    public void execute(Storage storage, UiDisplay uiDisplay, TaskList taskList) throws DookException{
        storage.save(taskList);
        uiDisplay.bidFarewell();
    }

}
