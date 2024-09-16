package command;
import main.Main;
import main.Ui;
import task.Task;

public class CommandAddTaskHandler implements ICommandHandler {

    /**
     * Execute the command
     *
     * @param command the full command
     * @param parameters the command parameters (splited by spaces)
     * @throws CommandException when any exception happens during command execution
     */
    @Override
    public void execute(String command, String[] parameters) throws CommandException {
        assert(Main.getInstance() != null);
        assert(Main.getInstance().getTaskList() != null);
        Main.getInstance().getTaskList().addTaskAndSay(new Task(command));
    }
}
