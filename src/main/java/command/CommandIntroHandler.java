package command;
import main.Main;

public class CommandIntroHandler implements ICommandHandler{

    @Override
    public void execute(String command, String[] parameters) {
        String name = Main.getInstance().getName();
        Main.getInstance().getUi().say("Hello! I'm " + name, true, false);
        Main.getInstance().getUi().say("What can I do for you?", false, true);
    }
}
