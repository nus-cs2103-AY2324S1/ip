import java.util.ArrayList;

public abstract class Command {
    public abstract void execute(Storage storage, ArrayList<Task> tasks);
}

