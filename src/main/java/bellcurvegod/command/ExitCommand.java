package bellcurvegod.command;

import static bellcurvegod.storage.Storage.updateData;

import java.io.IOException;

import bellcurvegod.gui.Gui;

/**
 * Encapsulates the exitCommand.
 */
public class ExitCommand implements Runnable {
    /**
     * Says goodbye to the user and exits.
     */
    public static String run() {
        try {
            updateData();
        } catch (IOException e) {
            return e.getMessage();
        }

        return Gui.getExitMessage();
    }
}
