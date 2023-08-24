package command;
import main.Main;
import task.Task;

public class CommandAddTaskHandler implements ICommandHandler {

    @Override
    public void execute(String command, String[] parameters) throws CommandException {
        Main.getInstance().getTaskList().addTask(new Task(command));
    }
}
