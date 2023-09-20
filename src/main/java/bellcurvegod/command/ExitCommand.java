package bellcurvegod.command;

import static bellcurvegod.storage.Storage.updateData;

import java.io.IOException;

import bellcurvegod.gui.Gui;

/**
 * Encapsulates the exitCommand.
 */
public class ExitCommand {
    /**
     * Says goodbye to the user and exits.
     *
     * @return ExitMessage.
     */
    public static String run() {
        try {
            updateData();
        } catch (IOException e) {
            return e.getMessage();
        }

        return Gui.getExitMessage();
    }

    public static String getHelpMessage() {
        return "Type 'bye' and enter, the app will exit.";
    }
}
