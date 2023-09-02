package cheems;

import java.util.Scanner;

/**
 * Represents the user interface for the application.
 * Handles display and interaction with the user.
 */
public class UI {

    /**
     * Prints welcome message with logo and greetings.
     */
    public void showWelcomeMsg() {
        String logo = "\n" +
                "         __                                             \n" +
                "        [  |                                            \n" +
                " .---.   | |--.    .---.   .---.   _ .--..--.    .--.   \n" +
                "/ /'`\\]  | .-. |  / /__\\\\ / /__\\\\ [ `.-. .-. |  ( (`\\]  \n" +
                "| \\__.   | | | |  | \\__., | \\__.,  | | | | | |   `'.'.  \n" +
                "'.___.' [___]|__]  '.__.'  '.__.' [___||__||__] [\\__) ) \n" +
                "                                                        \n";
        System.out.println("Hello from");
        System.out.println(logo);

        String hello = "Heyo I'm Cheems! Nice to meet you:)" + "\n" + "Want to get some fries on the pier together?";
        printWithFormat(hello);
    }

    /**
     * Prints exit message.
     */
    public void showExitMsg() {
        String bye = "Okay bye:( Let's get the fries next time.";
        printWithFormat(bye);
    }

    /**
     * Prompts and obtains user input.
     * @param scanner The scanner to scan user input.
     * @return The user input in a string.
     */
    public String getInput(Scanner scanner) {
        System.out.println("> You: ");
        return scanner.nextLine();
    }

    /**
     * Formats the given feedback to user to comply with chatbot convention.
     * @param msg Feedback to user.
     */
    public void printWithFormat(String msg) {
        System.out.println("> Cheems: ");
        System.out.println(msg);
    }
}
