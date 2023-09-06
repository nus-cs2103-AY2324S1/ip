package duke;

import java.util.Scanner;
import java.lang.StringBuilder;


/**
 * This class is in charge of the user interface.
 *
 * It is in charge responding to the user.
 */
public class UI {
    private static String name;
    private StringBuilder stringBuilder = new StringBuilder();

    /**
     * Constructs a UI which has a name.
     *
     * @param name The name of the assistant.
     */
    public UI(String name){
        UI.name = name;
    }

    /**
     * Prints a long line.
     * This acts as a separator between commands typed in by the user
     * and responses from the assistant bot.
     */
    public static void printline() {
        System.out.println("____________________________________________________________");
    }

    /**
     * Prints a welcome message.
     * This is the first message the user will receive upon
     * using the program.
     */
    public String welcomeMessage() {
        stringBuilder.append((String.format("Hello I'm %s, your personal assistant. \n", UI.name)));
        stringBuilder.append("What can I do for you today, sir?");
        return stringBuilder.toString();
    }

    /**
     * Reads the input from the user and saves it as a string.
     *
     * @return The command keyed in by the user that will be passed
     * into the parser.
     */
    public String readCommand() {
        Scanner scanIn = new Scanner(System.in);
        String text = scanIn.nextLine();
        return text;
    }

    /**
     * Sends a message to the user.
     * Based on the parameter, a message will be
     * sent to the user.
     *
     * @param message The message that it to be sent to the user.
     */
    public void buildMessage(String message) {
        stringBuilder.append(message);
    }

    public String sendMessage() {
        return stringBuilder.toString();
    }

    public void clearStringBuilder() {
        stringBuilder.setLength(0);
    }
}
