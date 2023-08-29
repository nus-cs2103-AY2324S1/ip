package commandhandling;

import commandhandling.argsorting.ArgSorter;
import exceptions.syntax.KniazInvalidArgsException;
import main.KniazSession;

/**
 * handles the bye command by instructing the session to quit
 */
public class QuitHandler implements  CommandHandler{

    private static final String[] ARG_ORDER = new String[]{};
    // no arguments allowed
    /**
     * Handles the bye command by instruction the session to quit
     * @param session the linked KniazSession that this command is to execute in
     * @param args the arguments to this command
     * @return The string displayed to the user when the session exits
     * @throws KniazInvalidArgsException when any argument is supplied
     */
    @Override
    public String handle(KniazSession session, String[] args) throws KniazInvalidArgsException {
        String[] sortedArgs = ArgSorter.sortArgsByStarting(args,ARG_ORDER);
        //this helps gatekeep this function and check inputs are right
        return session.quit();
    }
}
