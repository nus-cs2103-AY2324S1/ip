package command;
import main.Main;
import task.Task;

public class CommandMarkUnmarkHandler implements ICommandHandler {

    @Override
    public void execute(String command, String[] parameters){
        if(parameters.length < 2){
            Main.getInstance().say("Error: The task name for marking / unmarking is not given.");
            return;
        }
        boolean mark = parameters[0].equals("mark");
        String taskName = mark ? command.replaceFirst("mark ", "") : command.replaceFirst("unmark ", "");
        Task task = Main.getInstance().getTaskList().findTaskByName(taskName);
        if(task == null){
            Main.getInstance().say("Error: Can not find any task with name '" +taskName + "'.");
            return;
        }
        task.setIsDone(mark);
        Main.getInstance().say("Nice! I've marked this task as " + (mark ? "done" : "not done yet") + ":", true, false);
        Main.getInstance().say("  " + Main.getInstance().getTaskList().findTaskByName(taskName).toString(),false, true);
    }


}
