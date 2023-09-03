package oreo.ui;

import oreo.task.TaskList;

import java.util.Scanner;

/**
 * This class implements the Ui of the chatbot.
 *
 * @author Daniel Loh
 * @version 03/09/2023
 */
public class Ui {
    private String logo =  "                  .-\"-.\n"
            + "                 /|6 6|\\\n"
            + " _  ._ _   _    {/(_0_)\\}\n"
            + "(_) | (/_ (_)    _/ ^ \\_\n"
            + "                (/ /^\\ \\)-'\n"
            + "                 \"\"' '\"\"\n";

    private String greet = logo + "Woof! I'm oreo.Oreo! How may I help you?\n";

    private String exit = "I will be sad to see you go! bye!\n";

    private String hLine = "    ____________________________________________________________\n";

    private String indent = "     ";

    private Scanner sc = new Scanner(System.in);

    /**
     * Indents line by the number of spaces specified.
     *
     * @param message contents to be indented.
     * @param indents number of spaces to indent by.
     * @return returns the message with the specified amount of indents.
     */
    public static String indentLineBy(String message, int indents) {
        StringBuilder indentedLine = new StringBuilder();
        for (int i = 0; i < indents; i++) {
            indentedLine.append(" ");
        }
        return indentedLine.append(message).toString();
    }

    /**
     * Sets indent for reply message of chatbot.
     *
     * @param message message to display by chatbot.
     * @return messaged indented for reply.
     */
    private String indentMessage(String message) {
        String[] lines = message.split("\n");
        StringBuilder indentedMessage = new StringBuilder();
        for (String line : lines) {
            indentedMessage.append(indent).append(line).append("\n");
        }
        return indentedMessage.toString();
    }

    /**
     * Wraps message in the reply UI of the chatbot.
     *
     * @param message the message to be wrapped.
     * @return messaged wrapped for reply by chatbot.
     */
    private String botReply(String message) {
        StringBuilder reply = new StringBuilder();
        return reply.append(hLine).append(indentMessage(message))
                .append(hLine).toString();
    }

    /**
     * Greet user message.
     *
     * @param tasks to check if this is the first instance that user
     *              has used chatbot.
     */
    public void greet(TaskList tasks) {
        if (tasks.getNumberOfTask() != 0) {
            System.out.println(botReply(greet +
                    "Welcome back! " +
                    tasks.list()));
        } else {
            System.out.println(botReply(greet));
        }
    }

    /**
     * Displays exit message to user.
     */
    public void sayBye() {
        System.out.println(botReply(exit));
    }

    /**
     * Displays message to the user.
     *
     * @param message message to be displayed.
     */
    public void say(String message) {
        System.out.println(botReply(message));
    }

    public String readCommand() {
        return sc.nextLine();
    }
}
