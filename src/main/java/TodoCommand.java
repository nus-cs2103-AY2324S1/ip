public class TodoCommand extends Command {

    public static final String COMMAND_WORD = "todo";
    private String taskDescription;

    public TodoCommand(String taskDescription) {
        this.taskDescription = taskDescription;
    }

    @Override
    public void execute(TaskList tasks, Ui ui, Storage storage) throws ChatBuddyException {
        ToDo todo = new ToDo(taskDescription);
        tasks.addTask(todo);
        ui.showTaskAddition(todo, tasks.getSize());
    }
}
