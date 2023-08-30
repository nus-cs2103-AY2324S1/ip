public class TodoCommand extends AddCommand {

    String commandType;
    boolean isExit;

    TodoCommand(String name) {
        this.commandType = "todo";
        this.isExit = false;
        this.name = name;
    }

    void execute(TaskList tasks, Ui ui, Storage storage) {
        // 1. Create new to-do task
        Task newTodoTask = new Todo(name);
        // 2. Add to newTodoTask to tasks
        tasks.addTask(newTodoTask);
        // 3. Print successful task added message
        ui.printSuccessfulAddTaskResponse(newTodoTask, tasks.getNumTotalTasks());
    }

}
