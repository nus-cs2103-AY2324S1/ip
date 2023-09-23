package kniaz.logic.handler;

import java.util.List;
import java.util.Map;

import kniaz.KniazSession;


/**
 * Handles the list command, by getting the user-facing string representation of the session's TaskList
 */
public class ListHandler implements CommandHandler {

    // no arguments allowed
    /**
     * Handles the list command by getting the relevant string representation
     *
     * @param session     the linked KniazSession that this command is to execute in
     * @param unnamedArgs the arguments to this command, ignored
     * @param namedArgs   the named arugments to this command, ignored
     * @return the user-facing string representation of the linked session's TaskList
     */
    @Override
    public String handle(KniazSession session,
                         List<? extends String> unnamedArgs,
                         Map<? extends String, ? extends String> namedArgs) {

        return session.getTaskList().toPrintString();
    }
}
