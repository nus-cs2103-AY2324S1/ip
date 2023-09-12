package command;
import main.Main;
import main.Ui;

public class CommandListHandler implements ICommandHandler{

    @Override
    public void execute(String command, String[] parameters) {
        StringBuilder reply = new StringBuilder("Here are the tasks in your list:");
        Main.getInstance().getTaskList().iterate((index, task) -> reply.append(index + 1 + "." + task.toString() + "\n"));
        Ui.getInstance().say(reply.toString());
    }


}
