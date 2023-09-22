package command;

import main.Main;
import main.Ui;
import task.Task;

public class CommandDeleteHandler implements ICommandHandler {

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
        assert(Ui.getInstance() != null);
        int number = -1;
        try {
            number = Integer.parseInt(parameters[1]);
        }
        catch (NumberFormatException e) {
            throw new CommandException("Input is not a valid number");
        }
        if(number <= 0 || number > Main.getInstance().getTaskList().getCount()) {
            throw new CommandException("Input number out of range.");
        }
        Task removedTask = Main.getInstance().getTaskList().removeTask(number - 1);
        String reply = "Noted. I've removed this task:\n";
        reply += "  " + removedTask.toString() + "\n";
        reply += "Now you have " + Main.getInstance().getTaskList().getCount() +" tasks in the list.";
        Ui.getInstance().say(reply);
    }
}
