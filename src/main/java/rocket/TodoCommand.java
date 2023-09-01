package rocket;

public class TodoCommand extends AddCommand {
    private String description;

    /**
     * Creates todo command.
     * @param description the description of the todo.
     * @param isDone whether the todo has been completed.
     */
    public TodoCommand (String description, boolean isDone) {
        super(new Todo(description, isDone));
    }
}
