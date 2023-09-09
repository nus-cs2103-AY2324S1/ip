package Alex;

/**
 * A class that extends from Command class. The instance of this class represents a command that is not predefined
 * or is not understand by the chat bot Alex.
 */
public class UnknownCommand extends Command {
    private String command;

    /**
     * The constructor of UnknownCommand when an user input string is given.
     *
     * @param command The user input string.
     */
    public UnknownCommand (String command) {
        this.command = command;
    }

    /**
     * Override the method from the Command class.
     * The execute method of UnknownCommand class is used to actually print alert message to the standard output
     * to notify the user that the command typed by the user is defined or not known by the chat bot Alex.
     */
    @Override
    public void execute() {

        Ui.printAlertForUnknown();
    }
}
