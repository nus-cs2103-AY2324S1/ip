import java.io.IOException;

public class AddTodoCommand extends Command{
    private String taskName;

    /**
     * Constructor for the AddTodoCommand class.
     *
     * @param taskName
     */
    public AddTodoCommand(String taskName) {
        this.taskName = taskName;
    }

    @Override
    public void execute(TaskList taskList, Ui ui, Storage storage) throws DukeException {
        try {
            Todo todo = new Todo(taskName, false);
            taskList.addTask(todo);
            storage.appendToFile(todo.displayableForm());
            ui.successfulAddTaskMsg(todo.displayableForm(), taskList.getIndex());
        } catch (IOException e) {
            throw new DukeException("Something went wrong: " + e.getMessage());
        }
    }

    @Override
    public boolean isExit() {
        return false;
    }
}
