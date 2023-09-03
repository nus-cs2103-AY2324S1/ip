package bellcurvegod.command;

import bellcurvegod.ui.Ui;

import java.io.IOException;

import static bellcurvegod.storage.Storage.updateData;

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

        Ui.showLine();
        System.out.println("Bye. Hope to see you again soon!");
        Ui.showLine();
    }
}
