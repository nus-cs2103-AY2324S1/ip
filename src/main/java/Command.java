public abstract class Command {
    public abstract void executeCommand(UI ui, Actions actionList) throws DukeException;

    public boolean exit(){
        return false;
    }
}
