package sally;

/**
 * Represents a command to add a new Todo task.
 */
public class AddTodoCommand implements Command {
    private final String description;

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
     * @throws SallyException If there's an issue while executing the command.
     */
    @Override
    public String execute(TaskList tasks, Storage storage) throws SallyException {
        Todo newTodo = new Todo(description);
        tasks.addTask(newTodo);
        storage.saveTasksToFile(tasks);
        //put String below into another file
        String res = "Got it. I've added this task:\n" + newTodo.toString() + "\n"
                + "Now you have " + tasks.getTaskList().size() + " tasks in the list.";
        return res;
    }
}
