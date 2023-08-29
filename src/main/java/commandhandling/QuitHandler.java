package commandhandling;

import commandhandling.argsorting.ArgSorter;
import main.KniazSession;

public class QuitHandler implements  CommandHandler{

    private static final String[] ARG_ORDER = new String[]{};
    @Override
    public String handle(KniazSession session, String[] args) {
        String[] sortedArgs = ArgSorter.sortArgsByStarting(args,ARG_ORDER);
        //this helps gatekeep this function and check inputs are right
        return session.quit();
    }
}
