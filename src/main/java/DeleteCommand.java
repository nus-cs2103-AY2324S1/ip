public class DeleteCommand  extends Command{

    private int taskIndex;


    public DeleteCommand(int taskIndex) {
        this.taskIndex = taskIndex;
    }

    @Override
    public void execute(TaskList tasks, Ui ui, Storage storage) throws NoacException{

        if (taskIndex + 1 > tasks.size() ||  taskIndex < 0) {
            throw new NoacException("☹ OOPS!!! Please enter a task in your list!");
        }

        tasks.deleteTask(this.taskIndex);

        ui.showDeleteTask(tasks.getTask(this.taskIndex), tasks.size());

        storage.save(tasks);

    }
}
