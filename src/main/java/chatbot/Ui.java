package chatbot;

import chatbot.exceptions.LocalFileException;

import java.util.Scanner;

/**
 * Class that represents User Interface which deals with user input and output.
 */
public class Ui {
    static final String line = "\t____________________________________________________________";
    static final Scanner scanner = new Scanner(System.in);

    /**
     * Display a separator line in the output.
     */
    public void showLine() {
        System.out.println(line);
    }

    /**
     * Display the specified String, followed by a separator line, in the output.
     * @param s the String to be displayed
     */
    public void output(String s) {
        System.out.println(s.stripTrailing());
        showLine();
    }

    /**
     * Display the welcome message.
     */
    public void greet() {
        this.showLine();
        System.out.println("\tWelcome back, human!");
        System.out.println("\tI'm your personal chatBot, " + ChatBot.NAME + ".");
        System.out.println("\tWhat can I do for you today?");
        this.showLine();
    }

    /**
     * Display the farewell message.
     */
    public void farewell() {
        output("\tBye. Hope to see you again soon!");
    }

    /**
     * Display error when an error has occurred during initiation process with the data file.
     * @param e the error that has occurred
     */
    public void showLoadingError(LocalFileException e) {
        System.out.println(e.toString());
    }

    /**
     * Obtains the next command as a String from user input.
     * @return String of the command provided by the user
     */
    public String nextCommand() {
        return scanner.nextLine();
    }
}
