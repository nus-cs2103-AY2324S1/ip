package command;
import main.Main;
import main.Ui;

public class CommandExitHandler implements ICommandHandler{

    @Override
    public void execute(String command, String[] parameters) {
        Ui.getInstance().byeAndExit();
    }
}
