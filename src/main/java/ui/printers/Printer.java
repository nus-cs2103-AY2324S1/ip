package ui.printers;

import ui.javafx.MainWindow;

import java.util.concurrent.CompletableFuture;

/**
 * The printer class is in charge of printing all the bot's replies.
 */
public class Printer {
    private static MainWindow mainWindow;

    /**
     * Initializes the printer with the MainWindow object, so that dialog boxes can be created.
     *
     * @param mainWindow1 the MainWindow object
     */
    public static void initializePrinter(MainWindow mainWindow1) {
        mainWindow = mainWindow1;
    }

    /**
     * Prints the output given in a simple dialog box
     *
     * @param output The output to be printed.
     */
    public static void printBasicOutput(String output) {
        mainWindow.printEggbotDialogBox(output);
    }
}
