package duchess;

import java.util.ArrayList;
import java.util.Scanner;

/**
 * The main class used to execute Duchess actions.
 */
public class DuchessGUI {
    
    private Duchess duchess;
    private String responseString = "";

    public DuchessGUI() {
        this.duchess = new Duchess();
        this.duchess.setCallbackHandler((s) -> {
            this.responseString = s;
        });
    }

    /**
     * Returns, as a String, the response of DuchessGUI when a user input command is given.
     *
     * @param userInput - the user's input.
     * @return            the DuchessGUI's reponse.
     */
    public String getResponse(String userInput) {
        this.duchess.parseUserInput(userInput);

        return this.responseString;
    }
}
