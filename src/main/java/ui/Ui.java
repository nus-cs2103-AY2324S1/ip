package ui;

import java.io.InputStream;
import java.io.PrintStream;
import java.util.Scanner;

import static messages.Message.MESSAGE_WELCOME;
import static messages.Message.MESSAGE_EXIT;
import static messages.Message.MESSAGE_INSTRUCTIONS;

public class Ui {
    private final Scanner in;
    private final PrintStream out;

    public Ui() {
        this(System.in, System.out);
    }

    public Ui(InputStream in, PrintStream out) {
        this.in = new Scanner(in);
        this.out = out;
    }

    private static final String LINE_DIVIDER = "────────────────────────────────────";

    public void printLineDivider() {
        out.println(LINE_DIVIDER);
    }

    public void printWelcomeMessage(String version) {
        printToUser(version);
        printToUser(MESSAGE_WELCOME);
    }

    public void printInstructions() {
        printToUser(MESSAGE_INSTRUCTIONS);
    }

    public void printExitMessage() {
        printToUser(MESSAGE_EXIT);
    }

    public void printToUser(String text) {
        out.println(LINE_DIVIDER);
        out.println(text);
        out.println(LINE_DIVIDER);
    }

    public boolean shouldIgnoreInput(String input) {
        return input.trim().isEmpty();
    }

    public String getUserCommand() {
        printLineDivider();
        out.println("Please enter command below:");

        String userInput = in.nextLine();

        while (shouldIgnoreInput(userInput)) {
            userInput = in.nextLine();
        }

        return userInput;
    }
}
