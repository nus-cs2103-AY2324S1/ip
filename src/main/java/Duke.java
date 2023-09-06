import joi.Joi;
import joi.utils.InvalidCommandException;
import joi.utils.InvalidInputException;

import java.io.IOException;

public class Duke {
    public static void main(String[] args) throws IOException {
        Joi joi = new Joi();
        try {
            joi.run();
        }
        catch (InvalidInputException e) {
            System.err.println("OOPS!!! I'm sorry, but I don't know what " + e.getWrongInput() + " means :-(");
        }
        catch (InvalidCommandException e) {
            System.err.println("OOPS!!! " + e.getWrongInput());
        }
    }
}
