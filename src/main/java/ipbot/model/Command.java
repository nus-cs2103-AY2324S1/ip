package ipbot.model;

public enum Command {
    BYE ("bye"),
    LIST ("list"),
    MARK ("mark"),
    UNMARK ("unmark"),
    TODO ("todo"),
    DEADLINE ("deadline"),
    EVENT ("event"),
    DELETE ("delete"),
    INVALID ("");

    private final String command;

    Command(String command) {
        this.command = command;
    }

    public static Command commandEnum(String command) {
        for(Command currCmd: values()){
            if(currCmd.command.equals(command)){
                return currCmd;
            }
        }
        return null;
    }
}
