package echobot.ui;

import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.text.BadLocationException;
import javax.swing.text.StyledDocument;


/**
 * Provides methods related to user interface.
 */
public class Ui {
    private static final Logger logger = Logger.getLogger(Ui.class.getName());
    /**
     * Displays the EchoBot logo.
     */
    public String showLogo() {
        return  " ,d88b.d88b,\n" +
                "88888888888\n" +
                " `Y8888888Y'\n" +
                "    `Y888Y'  \n" +
                "        `Y'    \n";
    }


    public String showWelcomeMessage() {
        String responseText = "Hello! I'm EchoBot\n";
        responseText += showLogo();
        responseText += " What can I do for you?\n";

        return responseText;
    }


    public String showByeMessage() {
        return "Bye. Hope to see you again soon!";

    }

    /**
     * Displays an error message in the chat pane.
     *
     * @param styledDocument The StyledDocument for displaying messages.
     * @param errorMessage   The error message to display.
     */
    public void showErrorMessage(StyledDocument styledDocument, String errorMessage) {
        try {
            styledDocument.insertString(styledDocument.getLength(), "Error: " + errorMessage + "\n", null);
        } catch (BadLocationException e) {
            logger.log(Level.SEVERE, "Error displaying error message", e);
        }
    }
}
