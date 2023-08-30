import java.io.File;

public abstract class Command {

    private boolean isExit = false;

    public abstract void execute(TaskList t, Ui ui, FileHandler f);

    public abstract boolean isExit();

}
