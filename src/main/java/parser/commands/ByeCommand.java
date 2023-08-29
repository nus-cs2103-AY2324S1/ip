package parser.commands;

public class ByeCommand extends Command {
    public static String BYE_PHRASE = "";
    public ByeCommand() { }

    @Override
    public String execute() {
        return BYE_PHRASE;
    }

    public static boolean isBye(Command command) {
        return command instanceof ByeCommand;
    }
}
