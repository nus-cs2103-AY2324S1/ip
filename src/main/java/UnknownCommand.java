// For handling unknown user command
public class UnknownCommand extends Command{
    private String command;

    public UnknownCommand (String command) {
        this.command = command;
    }

    @Override
    public void execute() {

        Ui.printAlertForUnknown();
    }
}
