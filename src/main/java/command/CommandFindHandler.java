package command;

import main.Main;
import main.Ui;

public class CommandFindHandler implements ICommandHandler {
    @Override
    public void execute(String command, String[] parameters) throws CommandException {
        assert(Main.getInstance() != null);
        assert(Main.getInstance().getTaskList() != null);
        assert(Ui.getInstance() != null);
        if(parameters.length < 2) {
            throw new CommandException("Missing target task name");
        }
        StringBuilder reply = new StringBuilder("Here are the matching tasks in your list:\n");
        Main.getInstance().getTaskList().iterate((index, task) -> {if(task.getName().indexOf(parameters[1])!=-1) {reply.append(task.toString() + "\n"); }});
        Ui.getInstance().say(reply.toString());
    }
}
