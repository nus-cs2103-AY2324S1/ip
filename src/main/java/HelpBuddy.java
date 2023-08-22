import java.util.ArrayList;
import java.util.Scanner;
import java.util.regex.Pattern;

/**
 * A HelpBuddy class that produces messages to user.
 */

public class HelpBuddy {
    /** A string that produces a line to segment messages produced. */
    private final static String horizontal = "    ____________________________________________________________";
    /** An ArrayList that holds everything the user has typed into the chatbot. */
    private ArrayList<Task> userInput = new ArrayList<>(100);

    /**
     * @return hello message.
     */
    public static String printHelloMessage() {
        return "Hello! I'm HelpBuddy.\n" + "    What can I do for you?\n";
    }

    /**
     * @return bye message.
     */
    public static String printByeMessage() {
        return "Bye. Hope to see you again soon!\n";
    }

    /**
     * A method that segments replies from HelpBuddy.
     *
     * @param s The string that HelpBuddy replies.
     * @return The message segment.
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

        String markPattern = "mark\\s+(\\d+)";
        String unmarkPattern = "unmark\\s+(\\d+)";
        String inputMessage = sc.nextLine();

        while(inputMessage != "") {
            if (inputMessage.matches(markPattern)) {
                String taskIndex = inputMessage.replaceAll("mark\\s+", "");
                int intTaskValue = Integer.valueOf(taskIndex) - 1;
                Task t = this.userInput.get(intTaskValue);
                t.updateDone();
                System.out.println(printMessageBlock("Nice! I've marked this task as done:\n      " + t + "\n"));
            } else if (inputMessage.matches(unmarkPattern)) {
                String taskIndex = inputMessage.replaceAll("unmark\\s+", "");
                int intTaskValue = Integer.valueOf(taskIndex) - 1;
                Task t = this.userInput.get(intTaskValue);
                t.updateDone();
                System.out.println(printMessageBlock("OK, I've marked this task as not done yet:\n      " + t + "\n"));
            } else if (inputMessage.equalsIgnoreCase("list")){
                Object[] inputArray = this.userInput.toArray();
                String outputMessage = "Here are the tasks in your list:\n    ";
                for (int i = 0; i < inputArray.length; i++) {
                    int index = i + 1;
                    Task curr = (Task) inputArray[i];
                    if (i != inputArray.length - 1) {
                        outputMessage +=  index + "." + curr + "\n    ";
                    } else {
                        outputMessage +=  index + "." + curr + "\n";
                    }
                }
                System.out.println(printMessageBlock(outputMessage));
            } else if (inputMessage.equalsIgnoreCase("bye")) {
                System.out.println(printMessageBlock(HelpBuddy.printByeMessage()));
                break;
            } else {
                System.out.println(printMessageBlock("added: " + inputMessage + "\n"));
                this.userInput.add(new Task(inputMessage));
            }
            inputMessage = sc.nextLine();
        }
    }
}
