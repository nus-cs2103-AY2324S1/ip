package main.logic.handler;


import exceptions.syntax.KniazInvalidArgsException;
import main.KniazSession;

import java.util.List;
import java.util.Map;

/**
 * Handles the list command, by getting the user-facing string representation of the session's TaskList
 */
public class ListHandler implements  CommandHandler{

    private static final String[] ARG_ORDER = new String[]{};
    // no arguments allowed
    /**
     * Handles the list command by getting the relevant string representation
     *
     * @param session     the linked KniazSession that this command is to execute in
     * @param unnamedArgs the arguments to this command
     * @param namedArgs
     * @return the user-facing string representation of the linked session's TaskList
     * @throws KniazInvalidArgsException when any arguments are supplied, this should have no arguments supplied
     */
    @Override
    public String handle(KniazSession session,
                         List<? extends String> unnamedArgs,
                         Map<? extends String, ? extends String> namedArgs) throws KniazInvalidArgsException {

        return session.getTaskList().toPrintString();
    }
}
