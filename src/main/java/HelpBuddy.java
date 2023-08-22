import java.util.ArrayList;
import java.util.Scanner;

/**
 * A HelpBuddy class that produces messages to user.
 */

public class HelpBuddy {
    /** A string that produces a line to segment messages produced. */
    private final static String horizontal = "    ____________________________________________________________";
    /** An ArrayList that holds everything the user has typed into the chatbot. */
    private ArrayList<String> userInput = new ArrayList<>(100);

    /**
     * Returns hello message.
     */
    public static String printHelloMessage() {
        return "Hello! I'm HelpBuddy.\n" + "    What can I do for you?\n";
    }

    /**
     * Returns bye message.
     */
    public static String printByeMessage() {
        return "Bye. Hope to see you again soon!\n";
    }

    /**
     * A method that segments replies from HelpBuddy.
     *
     * @param s The string that HelpBuddy replies.
     */
    private String printMessageBlock(String s) {
        return horizontal + "\n" + "    " + s + horizontal + "\n";
    }

    /**
     * A method that produces answers from HelpBuddy after reading users input.
     *
     * @param sc A scanner to read the user input.
     */
    public void outputMessage(Scanner sc) {
        System.out.println(printMessageBlock(HelpBuddy.printHelloMessage()));

        String inputMessage = sc.nextLine();

        while(!inputMessage.equalsIgnoreCase("bye")) {
            if (!inputMessage.equalsIgnoreCase("list")) {
                System.out.println(printMessageBlock("added: " + inputMessage + "\n"));
                this.userInput.add(inputMessage);
            } else {
                Object[] inputArray = this.userInput.toArray();
                String outputMessage = "";
                for (int i = 0; i < inputArray.length; i++) {
                    int index = i + 1;
                    if (i != inputArray.length - 1) {
                        outputMessage +=  index + ". " + inputArray[i] + "\n    ";
                    } else {
                        outputMessage +=  index + ". " + inputArray[i] + "\n";
                    }
                }
                System.out.println(printMessageBlock(outputMessage));
            }
            inputMessage = sc.nextLine();
        }

        System.out.println(printMessageBlock(HelpBuddy.printByeMessage()));
    }
}
