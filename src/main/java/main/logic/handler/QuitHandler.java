package main.logic.handler;


import exceptions.syntax.KniazInvalidArgsException;
import main.KniazSession;

import java.util.List;
import java.util.Map;

/**
 * handles the bye command by instructing the session to quit
 */
public class QuitHandler implements CommandHandler{

    private static final String[] ARG_ORDER = new String[]{};
    // no arguments allowed
    /**
     * Handles the bye command by instruction the session to quit
     *
     * @param session     the linked KniazSession that this command is to execute in
     * @param unnamedArgs the arguments to this command, ignored
     * @param namedArgs   the named arguments to this command, ignored
     * @return The string displayed to the user when the session exits
     * @throws KniazInvalidArgsException when any argument is supplied
     */
    @Override
    public String handle(KniazSession session,
                         List<? extends String> unnamedArgs,
                         Map<? extends String, ? extends String> namedArgs) throws KniazInvalidArgsException {
        //this helps gatekeep this function and check inputs are right
        return session.quit();
    }
}
