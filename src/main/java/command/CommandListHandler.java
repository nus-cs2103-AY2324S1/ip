package command;
import main.Main;

public class CommandListHandler implements ICommandHandler{

    @Override
    public void execute(String command, String[] parameters) {
        int count = Main.getInstance().getTaskList().getCount();
        Main.getInstance().getUi().say("Here are the tasks in your list:",true,count == 0);
        Main.getInstance().getTaskList().iterate((index, task) -> Main.getInstance().getUi().say(index + 1 + "." + task.toString(), false, index == count - 1));
    }


}
