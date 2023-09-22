package moss;

import java.util.ArrayList;


/**
 * Moss is a simple task management application that allows users to add, list, mark, unmark, and delete tasks.
 */
public class Moss {
    /**
     * The list of tasks managed by the application.
     */
    static ArrayList<Task> things = new ArrayList<>();


    /**
     * You should have your own function to generate a response to user input.
     * Replace this stub with your completed method.
     */
    public String getResponse(String input) {
        try {
            Storage storage = new Storage();
            Parser parser = new Parser();
            return parser.execute(input, things, storage);
        } catch (MossException e) {
            throw new RuntimeException(e);
        }
    }

}


