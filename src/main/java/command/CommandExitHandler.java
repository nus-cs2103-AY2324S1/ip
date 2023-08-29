package command;
import main.Main;

public class CommandExitHandler implements ICommandHandler{

    @Override
    public void execute(String command, String[] parameters) {
        Main.getInstance().getUi().say("Bye. Hope to see you again soon!");
        Main.getInstance().exit();
    }
}
