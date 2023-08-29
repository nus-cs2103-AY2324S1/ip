package dot.commands;

public class ByeCommand extends Command {

    public ByeCommand() {
    }

    @Override
    public boolean isTerminateCommand() {
        return true;
    }

}
