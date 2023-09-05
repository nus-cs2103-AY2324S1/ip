package main.logic.handler;

import java.util.List;
import java.util.Map;

import exceptions.syntax.UnknownCommandException;
import main.KniazSession;



/**
 * Handles invalid commands, by throwing an exception when it is attempted to be executed
 */
public class InvalidHandler implements CommandHandler {
    /**
     * Handles invalid commands by throwing an exception
     *
     * @param session     the linked KniazSession that this command is to execute in
     * @param unnamedArgs the arguments to this command
     * @param namedArgs   the named arugments to this command
     * @return nothing, should always throw an exception
     * @throws UnknownCommandException when this is executed (i.e. always)
     */
    @Override
    public String handle(KniazSession session,
                         List<? extends String> unnamedArgs,
                         Map<? extends String, ? extends String> namedArgs) throws UnknownCommandException {
        throw new UnknownCommandException("Invalid command attempted to be executed",
                "I do not recognise this command", null);
        //What's inside the box? Surprise! It's an exception!
    }
}
