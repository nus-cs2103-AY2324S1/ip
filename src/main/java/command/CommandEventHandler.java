package command;

import main.Main;
import main.Ui;
import task.DeadlineTask;
import task.EventTask;

public class CommandEventHandler implements ICommandHandler{

    @Override
    public void execute(String command, String[] parameters) throws CommandException {
        assert(Main.getInstance() != null);
        assert(Main.getInstance().getTaskList() != null);
        String taskName = "";
        int length = parameters.length;
        int i;
        for(i = 1; i < length; i++){
            if(!parameters[i].equals("/from")){
                taskName += (i != 1 ? " " : "") + parameters[i];
            }
            else{
                break;
            }
        }
        String from = "";
        int fromStringBeginParameterIndex = i + 1;
        for(i++; i < length; i++){
            if(!parameters[i].equals("/to")){
                from += (i != fromStringBeginParameterIndex ? " " : "") + parameters[i];
            }
            else{
                break;
            }
        }
        String to = "";
        for(i++; i < length; i++){
            to += parameters[i] + (i != length - 1 ? " " : "");
        }
        if(from.equals("") || to.equals("")){
            throw new CommandException("Error: No from/to time is given.");
        }
        Main.getInstance().getTaskList().addTaskAndSay(new EventTask(taskName, from, to));
    }
}
