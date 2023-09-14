package bellcurvegod.command;

import static bellcurvegod.storage.Storage.updateData;

import java.io.IOException;

import bellcurvegod.ui.Ui;

/**
 * Encapsulates the exitCommand.
 */
public class ExitCommand implements Runnable {
    /**
     * Says goodbye to the user and exits.
     */
    public static void run() {
        try {
            updateData();
        } catch (IOException e) {
            System.out.println(e);
        }

        Ui.showExitMessage();
    }
}
