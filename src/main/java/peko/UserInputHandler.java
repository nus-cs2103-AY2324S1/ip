package peko;


import java.util.Scanner;

public class UserInputHandler {
    private static final String introText = "Konpeko, Konpeko, Konpeko! \n" +
            "Hololive san kisei no\n" +
            "Usada Pekora-peko! almondo almondo!";
    private static final String exitText = "Otsupeko! Bye bye!";

    private Commands command;
    private Scanner scanner = new Scanner(System.in);
    private String description;
    public UserInputHandler() {
        new StorageHandler();
    }
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

    }

    public boolean processInput() {
        TaskHandler taskHandler = new TaskHandler(command, description);
        return taskHandler.run();
    }

}