public class DeleteCommand extends Command{
    private final Integer taskIndex;
    public DeleteCommand(Integer taskIndex) {
        this.taskIndex = taskIndex;
    }
    @Override
    public void execute(TaskList tasks, Ui ui, Storage storage) throws DukeException{
        if (taskIndex < 0 || taskIndex >= tasks.length()) {
            throw new TaskNotFoundException("Task Not Found :'(");
        }
        String storeTaskTemp = tasks.getTask(taskIndex);
        tasks.delete(taskIndex);
        System.out.println("Noted. I've removed this task:");
        System.out.println("   " + storeTaskTemp);
        System.out.println("Now you have " + tasks.length() + " tasks in the list.");
    }
    @Override
    public boolean isExit() {
        return false;
    }
}
