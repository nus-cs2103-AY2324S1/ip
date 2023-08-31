public abstract class Command {
    public abstract void execute(TaskList taskList, BlipUI ui, BlipStorage storage);
    public boolean isExit() {
        return this instanceof ByeCommand;
    }

}
