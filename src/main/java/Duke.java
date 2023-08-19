import java.util.Scanner;

public class Duke {
    private static final int lineLength = 60;
    private static final String horizontalLine = "_".repeat(lineLength);
    private static final String msgIndent = " ";
    private static final String responseIndent = " ".repeat(4);
    private static final String name = "chatBot";
    private static final String helloMsg = String.format("Hello! I'm %s", name);
    private static final String requestMsg = "What can I do for you?";
    private static final String goodbyeMsg = "Bye. Hope to see you again soon!";
    private static final Scanner userInput = new Scanner(System.in);

    public static void main(String[] args) {
        Duke.printMsg(helloMsg + "\n" + requestMsg);
        while (true) {
            String msg = userInput.nextLine();
            if (msg.equals("bye")) {
                break;
            } else {
                Duke.printMsg(msg);
            }
        }
        Duke.printMsg(goodbyeMsg);
    }

    private static void printMsg(String msg) {
        Duke.printResponse(horizontalLine);
        for (String line : msg.split("\n")) {
            Duke.printResponse(msgIndent + line);
        }
        Duke.printResponse(horizontalLine);
        System.out.println();
    }

    private static void printResponse(String response) {
        for (String line : response.split("\n")) {
            System.out.println(responseIndent + line);
        }
    }
}
