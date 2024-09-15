package emiya.commands;

public abstract class Command {

    /**
     * Returns the description of the command, as well as its usage. Used by the help command.
     * @return A String description of the command and its usage.
     */
    public String giveHelpDescription() {
        return "";
    };

}
