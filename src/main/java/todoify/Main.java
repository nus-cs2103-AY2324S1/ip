package todoify;

import javafx.application.Application;

import todoify.gui.MainWindowController;
import todoify.stdio.MainTextUi;

/**
 * The main entry point of the program.
 */
public class Main {
    public static void main(String[] args) {

        boolean hasRequestedTextMode = false;
        for (String arg : args) {
            if (arg.equals("-t") || arg.equals("--text-ui")) {
                hasRequestedTextMode = true;
                break;
            }
        }

        if (hasRequestedTextMode) {
            MainTextUi.run(args);
        } else {
            Application.launch(MainWindowController.class, args);
        }

    }
}
