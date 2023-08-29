import java.util.Scanner;

/**
 * A class which runs the HelpBuddy chatbot.
 */
public class Main {
    public static void main(String[] args) {
        /** Create a scanner to read from standard input. */
        Scanner sc = new Scanner(System.in);
        /** Create a new chatbot to activate its service. */
        HelpBuddy chatBot = new HelpBuddy();
        /** Chatbot will reply according to user input. */
        chatBot.outputMessage(sc);
        /** Clean up the scanner. */
        sc.close();
    }
}
