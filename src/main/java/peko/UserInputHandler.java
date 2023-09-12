package peko;


import peko.commands.Commands;
import peko.memory.StorageHandler;
import peko.tasks.TaskHandler;

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


    public void newInput(String s) {
        Parser parser = new Parser(s);
        command = parser.getResponseValue();
        description = parser.getDescription();

    }

    public String getResponse() {
        TaskHandler taskHandler = new TaskHandler(command, description);
        return taskHandler.getResponse();
    }

   public boolean processInput() {
        TaskHandler taskHandler = new TaskHandler(command, description);
        return taskHandler.run();
    }

}