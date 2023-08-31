package rocket;

public class TodoCommand extends AddCommand {
    private String description;

    public TodoCommand (String description, boolean isDone) {
        super(new Todo(description, isDone));
    }
}
