import java.io.IOException;

public abstract class Command {

    public void execute(TaskList taskList, UI ui, Storage storage) throws DukeException, IOException {}

    public boolean end() {
        return true;
    }
}
