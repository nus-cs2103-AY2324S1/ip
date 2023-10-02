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
     *
     * @return The welcome message
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
     * Stores a message in the string builder.
     * This adds a string to the string builder.
     *
     * @param message The message that is to be stored up in the string builder.
     */
    public void buildMessage(String message) {
        stringBuilder.append(message);
    }

    /**
     * Sends the string which has been stored in the string builder.
     *
     * @return The message that is to be sent to the user.
     */
    public String sendMessage() {
        return stringBuilder.toString();
    }

    /**
     * Clears all previous strings that were in the string builder.
     *
     */
    public void clearStringBuilder() {
        stringBuilder.setLength(0);
    }
}
