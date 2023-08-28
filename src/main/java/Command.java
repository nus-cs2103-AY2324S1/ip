public abstract class Command{
    public boolean isExit = false;
    protected Storage storage;
    protected UiDisplay uiDisplay;
    protected TaskList taskList;
    public abstract void execute(Storage storage, UiDisplay uiDisplay, TaskList taskList) throws DookException;

}