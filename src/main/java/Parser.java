import java.util.ArrayList;

public class Parser {
    private String command;
    private ArrayList<Task> things;

    private Storage storage;
    public Parser(String command, ArrayList<Task> things, Storage storage) {
        this.command = command;
        this.things = things;
        this.storage = storage;
    }

    public void execute(String message, ArrayList<Task> things, Storage storage) throws MossException {
        TaskList.command(message, things, storage);
    }
}
