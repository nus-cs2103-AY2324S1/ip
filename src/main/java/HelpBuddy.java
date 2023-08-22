import java.util.Scanner;

/**
 * A HelpBuddy class that produces messages to user.
 */

public class HelpBuddy {
    /** A string that produces a line to segment messages produced. */
    private final static String horizontal = "    ____________________________________________________________";
    /** A string that holds what the user has typed into the chatbot. */
    private String userInput;

    /**
     * Returns hello message.
     */
    public static String printHelloMessage() {
        return "Hello! I'm HelpBuddy.\n" + "    What can I do for you?";
    }

    /**
     * Returns bye message.
     */
    public static String printByeMessage() {
        return "Bye. Hope to see you again soon!";
    }

    /**
     * A method that segments replies from HelpBuddy.
     *
     * @param s The string that HelpBuddy replies.
     */
    private String printMessageBlock(String s) {
        return horizontal + "\n" + "    " + s + "\n" + horizontal + "\n";
    }

    /**
     * A method that produces answers from HelpBuddy after reading users input.
     *
     * @param sc A scanner to read the user input.
     */
    public void outputMessage(Scanner sc) {
        System.out.println(printMessageBlock(HelpBuddy.printHelloMessage()));

        String inputMessage = sc.next();
        this.userInput = inputMessage;

        while(!inputMessage.equalsIgnoreCase("bye")) {
            System.out.println(printMessageBlock(this.userInput));
            inputMessage = sc.next();
            this.userInput = inputMessage;
        }

        System.out.println(printMessageBlock(HelpBuddy.printByeMessage()));
    }
}
