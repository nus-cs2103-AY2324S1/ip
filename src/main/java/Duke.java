import java.util.Scanner;

public class Duke {

    // Function that encapsulates message into a message Card template
    private static String messageCard(String message) {
        String horizontalLine = "\t____________________________________________________________\n";
        return horizontalLine + "\t " + message + "\n" + horizontalLine;

    }

    // Displays list of items
    private static String displayList(String[] list) {
        String str = "";
        int count = 0;
        while (list[count] != null) {
            str += count + 1 + ". " + list[count] + "\n\t ";
            count++;
        }

        return messageCard(str.substring(0, str.length() - 3));
    }

    // Main function
    public static void main(String[] args) {
        String CHATBOTNAME = "Carl";
        System.out.println(messageCard("Hello! I'm " + CHATBOTNAME
            + "\n\t What can I do for you?"));

        String[] arr = new String[100];
        int count = 0;
        while (true) {
            Scanner SC = new Scanner(System.in);
            String userInput = SC.nextLine();
            if (userInput.equals("bye")) {
                System.out.println(messageCard("Bye. Hope to see you again soon!"));
                break;
            } else if (userInput.equals("list")) {
                System.out.println(displayList(arr));
            } else {
                System.out.println(messageCard("added: " + userInput));
                arr[count] = userInput;
                count++;
            }
        }
    }
}