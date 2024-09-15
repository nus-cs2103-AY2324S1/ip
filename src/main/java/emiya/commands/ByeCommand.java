package emiya.commands;

import javafx.application.Platform;

/**
 * A class that represents the Bye command.
 */
public class ByeCommand extends Command {

    /**
     * Exits the user out of the application.
     */
    public static void exit() {
        Platform.exit();
    }

    @Override
    public String giveHelpDescription() {
        return "Bye: This command exits and closes the program!\n"
                + "The format for the input is as follows:\n"
                + "bye\n";
    }
}
