package command;
import main.Main;

public class CommandListHandler implements ICommandHandler{

    @Override
    public void execute(String command, String[] parameters) {
        int count = Main.getInstance().getTaskList().getCount();
        Main.getInstance().getTaskList().iterate((index, task) -> Main.getInstance().say(index + 1 + "." + task.toString(), index == 0, index == count - 1));
    }


}
