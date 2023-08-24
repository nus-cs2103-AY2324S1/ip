/**
 * Insert item into application state.
 */
public class InsertCommand implements Command {
    private final DukeState state;

    public InsertCommand(DukeState state) {
        this.state = state;
    }

    /**
     * Add an item to the application state.
     *
     * @param input The user input of the item to add.
     */
    @Override
    public void run(String input) {
        String[] args = input.split(" ", 2);
        String taskType = args[0];
        String taskInput = args.length > 1 ? args[1] : "";
        try {
            Task task = Task.createTask(taskType, taskInput);
            this.state.insertTask(task);
            int taskCount = this.state.getTaskCount();
            System.out.printf((DukeConstants.INSERT_MESSAGE) + "%n",
                    task, taskCount, taskCount == 1 ? "task" : "tasks");
        } catch (InsufficientArgumentsException e) {
            System.out.println(e.getMessage());
        }
    }
}
