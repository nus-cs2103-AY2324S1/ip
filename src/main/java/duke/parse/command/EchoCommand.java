package duke.parse.command;

import duke.Duke;

/**
 * Represents a command to echo the input from user back to the user.
 */
public class EchoCommand implements Command {
    private String command;

    /**
     * Instantiates the echo command with the given input from the user.
     * @param command the input from the user
     */
    public EchoCommand(String command) {
        this.command = command;
    }

    /**
     * Commands the bot to echo the input back to the user.
     * @param bot the bot to execute the command
     * @return true, as it allows the user to continue the programme
     */
    @Override
    public boolean execute(Duke bot) {
        bot.echo(this.command);
        return true;
    }

    /**
     * Checks whether this echo command is the same as another command, for testing purposes.
     * They are the same if the input from user is the same.
     * @param another
     * @return
     */
    @Override
    public boolean equals(Object another) {
        if (another instanceof EchoCommand) {
            EchoCommand anotherEcho = (EchoCommand) another;
            return this.command.equals(anotherEcho.command);
        }
        return false;
    }
}
