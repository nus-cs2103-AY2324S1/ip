package oreo.ui;

/**
 * This class implements the Ui of the chatbot.
 *
 * @author Daniel Loh
 * @version 03/09/2023
 */
public class Formatter {
    private String logo =  "                  .-\"-.\n"
            + "                 /|6 6|\\\n"
            + " _  ._ _   _    {/(_0_)\\}\n"
            + "(_) | (/_ (_)    _/ ^ \\_\n"
            + "                (/ /^\\ \\)-'\n"
            + "                 \"\"' '\"\"\n";

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
}
