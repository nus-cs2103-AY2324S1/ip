package emiya.commands;

import javafx.application.Platform;

public class ByeCommand implements Command{
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
