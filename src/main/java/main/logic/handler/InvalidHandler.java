package main.logic.handler;

import exceptions.syntax.KniazInvalidCommandException;
import main.KniazSession;

import java.util.List;
import java.util.Map;

/**
 * Handles invalid commands, by throwing an exception when it is attempted to be executed
 */
public class InvalidHandler implements  CommandHandler{
    /**
     * Handles invalid commands by throwing an exception
     *
     * @param session     the linked KniazSession that this command is to execute in
     * @param unnamedArgs the arguments to this command
     * @param namedArgs
     * @return nothing, should always throw an exception
     * @throws KniazInvalidCommandException when this is executed (i.e. always)
     */
    @Override
    public String handle(KniazSession session, List<? extends String> unnamedArgs, Map<? extends String, ? extends String> namedArgs) throws KniazInvalidCommandException {
        throw new KniazInvalidCommandException();
        //What's inside the box? Surprise! It's an exception!
    }
}
