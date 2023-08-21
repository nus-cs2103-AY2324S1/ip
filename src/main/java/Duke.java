import java.util.Scanner;

public class Duke {

    // Function that encapsulates message into a message Card template
    private static String messageCard(String message) {
        String horizontalLine = "\t____________________________________________________________\n";
        return horizontalLine + "\t " + message + "\n" + horizontalLine;

    }

    // Main function
    public static void main(String[] args) {
        String CHATBOTNAME = "Carl";
        System.out.println(messageCard("Hello! I'm " + CHATBOTNAME
            + "\n\t What can I do for you?"));
        while (true) {
            Scanner SC = new Scanner(System.in);
            String userInput = SC.nextLine();
            if (userInput.equals("bye")) {
                System.out.println(messageCard("Bye. Hope to see you again soon!"));
                break;
            }
            System.out.println(messageCard(userInput));
        }
    }
}