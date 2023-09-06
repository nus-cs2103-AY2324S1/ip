package ipbot.model;

/**
 * Represents a command available in ipbot.
 */
public enum Command {
    BYE ("bye"),
    LIST ("list"),
    MARK ("mark"),
    UNMARK ("unmark"),
    TODO ("todo"),
    DEADLINE ("deadline"),
    EVENT ("event"),
    DELETE ("delete"),
    FIND ("find"),
    INVALID ("");

    private final String command;

    Command(String command) {
        this.command = command;
    }

    /**
     * Returns the corresponding command based on the command String.
     *
     * @param command The String version of the command.
     * @return The corresponding command if it exists. Otherwise, return null.
     */
    public static Command commandEnum(String command) {
        for(Command currCmd: values()){
            if(currCmd.command.equals(command)){
                return currCmd;
            }
        }
        return null;
    }
}
