import java.util.ArrayList;

public abstract class Command {
//    private String fullCommand;
//
//    public Command(String fullCommand) {
//        this.fullCommand = fullCommand;
//    }

    public abstract void execute(Storage storage, ArrayList<Task> tasks);
}

