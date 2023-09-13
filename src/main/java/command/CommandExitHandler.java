package command;
import main.Main;
import main.Ui;

public class CommandExitHandler implements ICommandHandler{

    @Override
    public void execute(String command, String[] parameters) {
        assert(Ui.getInstance() != null);
        Ui.getInstance().byeAndExit();
    }
}
