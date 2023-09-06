

import main.KniazSession;

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
    public String getResponse(String input) {


        return this.session.runOneIter(input);
    }








}
