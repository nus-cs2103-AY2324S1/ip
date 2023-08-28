public class InvalidCommand extends Command{
    @Override
    public void execute(Storage storage, UiDisplay uiDisplay, TaskList taskList) throws DookException{
        uiDisplay.displayHelp();
    }
}
