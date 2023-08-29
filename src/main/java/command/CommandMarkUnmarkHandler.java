package command;
import main.Main;
import task.Task;

public class CommandMarkUnmarkHandler implements ICommandHandler {

    @Override
    public void execute(String command, String[] parameters) throws CommandException {
        if(parameters.length < 2){
            throw new CommandException("Error: The task name for marking / unmarking is not given.");
        }
        boolean mark = parameters[0].equals("mark");
        String taskName = mark ? command.replaceFirst("mark ", "") : command.replaceFirst("unmark ", "");
        Task task = Main.getInstance().getTaskList().findTaskByName(taskName);
        if(task == null){
            throw new CommandException("Error: Can not find any task with name '" +taskName + "'.");
        }
        task.setIsDone(mark);
        Main.getInstance().getUi().say("Nice! I've marked this task as " + (mark ? "done" : "not done yet") + ":", true, false);
        Main.getInstance().getUi().say("  " + Main.getInstance().getTaskList().findTaskByName(taskName).toString(),false, true);
    }


}
