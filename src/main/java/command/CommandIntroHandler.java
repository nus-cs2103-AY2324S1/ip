package command;
import main.Main;
import main.Ui;

public class CommandIntroHandler implements ICommandHandler{

    @Override
    public void execute(String command, String[] parameters) {
        assert(Main.getInstance() != null);
        assert(Main.getInstance().getName() != null);
        assert(Ui.getInstance() != null);
        String name = Main.getInstance().getName();
        Ui.getInstance().say("Hello! I'm " + name + "\nWhat can I do for you?");
    }
}
