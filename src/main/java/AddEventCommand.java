public class AddEventCommand extends Command {

    public AddEventCommand(CommandType commandType, TaskList taskList, String args) {
        super(commandType, taskList, args);
    }

    @Override
    public void execute() {
        this.taskList.addTask(TaskType.EVENT, args);
    }
}
