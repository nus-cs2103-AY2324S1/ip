public class SaveCommand extends Command {
    @Override
    public void execute(Storage storage, UiDisplay uiDisplay, TaskList taskList) throws DookException{
        uiDisplay.printMessage(storage.save(taskList));
    }

}
