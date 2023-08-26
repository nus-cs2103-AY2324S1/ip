import java.io.IOException;

public abstract class Command {
    boolean isExit;

    public Command(boolean isExit){
        this.isExit = isExit;
    }

    public boolean isExit() {
        return isExit;
    }

    abstract void execute(TaskList tasks, UI ui, Storage storage) throws IOException;
}
