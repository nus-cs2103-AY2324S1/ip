package command;
import main.Main;
import main.Ui;

public class CommandListHandler implements ICommandHandler {

    /**
     * Execute the command
     *
     * @param command the full command
     * @param parameters the command parameters (splited by spaces)
     * @throws CommandException when any exception happens during command execution
     */
    @Override
    public void execute(String command, String[] parameters) {
        assert(Main.getInstance() != null);
        assert(Main.getInstance().getTaskList() != null);
        assert(Ui.getInstance() != null);
        StringBuilder reply = new StringBuilder("Here are the tasks in your list:\n");
        Main.getInstance().getTaskList().iterate((index, task) -> reply.append(index + 1 + "." + task.toString() + "\n"));
        Ui.getInstance().say(reply.toString());
    }


}
