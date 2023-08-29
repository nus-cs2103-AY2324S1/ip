public class AddToDoCommand extends Command {

    public AddToDoCommand(CommandType commandType, TaskList taskList, String args) {
        super(commandType, taskList, args);
    }

    @Override
    public void execute() {
        this.taskList.addTask(TaskType.TODO, args);
    }

}
