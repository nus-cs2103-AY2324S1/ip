

import main.KniazSession;
import ui.UiCommand;

/**
 * Entry point for the Kniaz program, functions mostly as a wrapper around KniazSession
 */
public class Kniaz {



    private KniazSession session;


    public Kniaz() {
        this.session = KniazSession.init();
    }

    /**
     * You should have your own function to generate a response to user input.
     * Replace this stub with your completed method.
     */
    public UiCommand getResponse(String input) {


        return this.session.runOneIter(input);
    }








}
