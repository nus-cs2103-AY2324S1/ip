public class Command {

    private String commandWord;

    private String[] arguments;

    public Command(String commandWord, String[] arguments) {
        this.commandWord = commandWord;
        this.arguments = arguments;
    }

    public String getCommand() {
        return commandWord;
    }

    public String[] getArguments() {
        return arguments;
    }

}