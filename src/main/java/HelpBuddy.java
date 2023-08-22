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
        String toDoPattern = "todo\\s+(.+)";
        String deadlinePattern = "deadline\\s+(.+)\\s+/by\\s+(.+)";
        String eventPattern = "event\\s+(.+)\\s+/from\\s+(.+)\\s+/to\\s+(.+)";
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
                String output = "Got it. I've added this task:\n      ";
                int numOfTasks = userInput.size() + 1;
                Task t = null;
                if (inputMessage.matches(deadlinePattern)) {
                    String taskDetails = inputMessage.replaceAll("deadline\\s+", "");
                    String[] taskDetailArray = taskDetails.split("/by");
                    t = new Deadline(taskDetailArray[0], taskDetailArray[1]);
                } else if (inputMessage.matches(toDoPattern)) {
                    String taskDetails = inputMessage.replaceAll("todo\\s+", "");
                    t = new ToDo(taskDetails);
                } else if (inputMessage.matches(eventPattern)) {
                    String taskDetails = inputMessage.replaceAll("event\\s+", "");
                    String[] taskDetailArray = taskDetails.split("/from");
                    String[] timeFromTo = taskDetailArray[1].split("/to");
                    t = new Event(taskDetailArray[0], timeFromTo[0], timeFromTo[1]);
                }
                this.userInput.add(t);
                output += t + "\n    Now you have " + numOfTasks + " tasks in this list.\n";
                System.out.println(printMessageBlock(output));
            }
            inputMessage = sc.nextLine();
        }
    }
}
