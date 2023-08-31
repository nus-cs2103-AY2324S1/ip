package command;

import main.Main;

public class CommandFindHandler implements ICommandHandler {
    @Override
    public void execute(String command, String[] parameters) throws CommandException {
        if(parameters.length < 2) {
            throw new CommandException("Missing target task name");
        }
        Main.getInstance().getUi().say("Here are the matching tasks in your list:", true, false);
        Main.getInstance().getTaskList().iterate((index, task) -> {if(task.getName().indexOf(parameters[1])!=-1) {Main.getInstance().getUi().say(task.toString(), false, false); }});
        Main.getInstance().getUi().outputLine();
    }
}
