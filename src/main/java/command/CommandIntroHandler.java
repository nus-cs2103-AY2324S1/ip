package command;
import main.Main;
import main.Ui;

public class CommandIntroHandler implements ICommandHandler{

    @Override
    public void execute(String command, String[] parameters) {
        String name = Main.getInstance().getName();
        Ui.getInstance().say("Hello! I'm " + name + "\nWhat can I do for you?");
    }
}
