package command;

import main.Main;
import task.DeadlineTask;
import task.DurationTask;

public class CommandDurationHandler implements ICommandHandler {

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
        String taskName = "";
        int length = parameters.length;
        int i;
        for(i = 1; i < length; i++) {
            if(!parameters[i].equals("/for")){
                taskName += (i != 1 ? " " : "") + parameters[i];
            }
            else{
                break;
            }
        }
        String durationStr = "";
        for(i++; i < length; i++) {
            durationStr += parameters[i] + (i != length - 1 ? " " : "");
        }
        if(durationStr.equals("")) {
            throw new CommandException("Error: No duration is given.");
        }
        float duration = 0.0f;
        try {
            duration = Float.parseFloat(durationStr);
        }
        catch (NumberFormatException e) {
            throw new CommandException("Duration length is not a valid number");
        }
        Main.getInstance().getTaskList().addTaskAndSay(new DurationTask(taskName, duration));
    }
}
