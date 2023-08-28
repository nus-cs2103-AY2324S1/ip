public class AddTaskCommand extends Command {
    private final Task task;
    public AddTaskCommand(Task task) {
        this.task = task;
    }
    @Override
    public void execute(Storage storage, UiDisplay uiDisplay, TaskList taskList) throws DookException{

        uiDisplay.printMessage(taskList.addTask(task));
    }
}
