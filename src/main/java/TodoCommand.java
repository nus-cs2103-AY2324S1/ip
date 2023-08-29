public class TodoCommand extends Command {
    protected String description;
    public static final String COMMAND_WORD = "todo";
    public static final String MESSAGE_SUCCESS = " Got it. I've added this task:\n";

    public TodoCommand(String description) {
        this.description = description;
    }

    public void execute(TaskList tasks, Ui ui, Storage storage) {
        Todo todo = new Todo(this.description);
        tasks.addTask(todo);
        storage.writeToFile(tasks.getList());
        ui.showMessage(MESSAGE_SUCCESS + "     " + todo.toString()
                + "\n Now you have " + tasks.getSize() + " tasks in the list");
    }
}
