public class ToDoCommand extends Command {

    private String description;

    public ToDoCommand(String description) {
        super(CommandType.TODO);
        this.description = description;
    }

    public void execute(TaskList tasks, Ui ui) {
        Task task = new ToDo(description);
        tasks.addTask(task);
        ui.todoMessage(task);
        ui.taskListSizeMessage(tasks.getSize(), true);
    }
}
