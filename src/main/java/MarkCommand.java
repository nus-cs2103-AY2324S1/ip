public class MarkCommand extends Command{
    private final int index;
    public MarkCommand(int index) {
        this.index = index;

    }
    @Override
    public void execute(Storage storage, UiDisplay uiDisplay, TaskList taskList) throws DookException{
        uiDisplay.printMessage(taskList.markTask(index, true));
    }
}
