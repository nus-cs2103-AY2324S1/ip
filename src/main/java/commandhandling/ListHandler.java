package commandhandling;

import commandhandling.argsorting.ArgSorter;
import exceptions.syntax.KniazInvalidArgsException;
import main.KniazSession;

/**
 * Handles the list command, by getting the user-facing string representation of the session's TaskList
 */
public class ListHandler implements  CommandHandler{

    private static final String[] ARG_ORDER = new String[]{};
    // no arguments allowed
    /**
     * Handles the list command by getting the relevant string representation
     * @param session the linked KniazSession that this command is to execute in
     * @param args the arguments to this command
     * @return the user-facing string representation of the linked session's TaskList
     * @throws KniazInvalidArgsException when any arguments are supplied, this should have no arguments supplied
     */
    @Override
    public String handle(KniazSession session, String[] args) throws KniazInvalidArgsException {
        String[] sortedArgs = ArgSorter.sortArgsByStarting(args,ARG_ORDER);
        // helps to check make sure it's alright
        return session.getTaskList().toPrintString();
    }
}
