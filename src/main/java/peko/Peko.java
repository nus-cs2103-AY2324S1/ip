package peko;

import java.io.*;
import java.util.Scanner;

public class Peko {
    private UserInputHandler userInputHandler;
    private UI ui;
    private StorageHandler storageHandler;

    /**
     * The main entry point of the program.
     * This method initializes an instance of the Peko class and invokes its run method.
     *
     * @param args The command-line arguments passed to the program.
     */
    public static void main(String[] args) {
        new Peko().run();
    }

    /**
     * Initiates the main execution loop of the program.
     * This method displays an introduction, processes user input in a loop,
     * saves data as needed, and displays an exit message upon completion.
     */
    private void run() {
        Output.intro();
        while (true) {
            userInputHandler.newInput();
            if (!userInputHandler.processInput()) {
                break;
            }
            SaveHandler.saveTo();
        }
        Output.exit();

    }

    /**
     * Private constructor for the Peko class.
     * Initializes necessary handlers for user input and data storage.
     */
    private Peko() {
        userInputHandler = new UserInputHandler();
        storageHandler = new StorageHandler();
    }

}
