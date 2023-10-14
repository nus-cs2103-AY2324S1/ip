package sally;

/**
 * Represents a command to add a new Todo task.
 */
public class AddTodoCommand implements Command {
    private final String description;
    private Message message;

    /**
     * Constructs an AddTodoCommand with the provided task description.
     *
     * @param input The description of the todo task.
     * @throws SallyException If the description is empty.
     */
    public AddTodoCommand(String input) throws SallyException {
        if (!input.isEmpty()) {
            this.description = input;
        } else {
            throw new SallyException("OOPS! The description of a todo cannot be empty.");
        }
    }

    /**
     * Executes the command to add a new Todo task to the task list.
     *
     * @param tasks The TaskList containing tasks.
     * @param storage The Storage for tasks.
     * @return A string indicating the todo task has been added.
     * @throws SallyException If there's an issue while executing the command.
     */
    @Override
    public String execute(TaskList tasks, Storage storage) throws SallyException {
        message = new Message();
        Todo newTodo = new Todo(description);
        tasks.addTask(newTodo);
        storage.saveTasksToFile(tasks);
        return message.addMessage(newTodo, tasks.getTaskList().size());
    }
}
