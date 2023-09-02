public abstract class Command {

    public void execute(TaskList taskList, UI ui, Storage storage) throws DukeException {}

    public boolean end() {
        return true;
    }
}
