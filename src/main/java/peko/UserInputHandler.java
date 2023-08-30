package peko;

import java.awt.image.BufferedImage;
import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Scanner;

public class UserInputHandler {
    private static final String introText = "Konpeko, Konpeko, Konpeko! \n" +
            "Hololive san kisei no\n" +
            "Usada Pekora-peko! almondo almondo!";
    private static final String exitText = "Otsupeko! Bye bye!";

    private Commands command;
    private Scanner scanner = new Scanner(System.in);
    private String description;

    /**
     * Constructor for the UserInputHandler class.
     * Initializes a new instance of the StorageHandler class.
     */
    public UserInputHandler() {
        new StorageHandler();
    }


    /**
     * Reads and processes new user input.
     * This method initializes a Scanner to read input from the console,
     * parses the input using a Parser instance, and stores the resulting
     * command and description for further processing.
     */
    public void newInput() {
        scanner = new Scanner(System.in);
        String input = scanner.nextLine();
        Parser parser = new Parser(input);
        command = parser.getResponseValue();
        description = parser.getDescription();
    }


    /**
     * Processes the parsed user input.
     * This method initializes a TaskHandler with the parsed command and description,
     * and delegates the processing of the task to the TaskHandler.
     *
     * @return True if the user selects a Task that continues the program and false otherwise.
     */
    public boolean processInput() {
        TaskHandler taskHandler = new TaskHandler(command, description);
        return taskHandler.run();
    }

}