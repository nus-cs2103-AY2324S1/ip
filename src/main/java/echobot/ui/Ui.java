package echobot.ui;

import java.util.logging.Logger;

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
}
