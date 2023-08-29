package commandhandling;

import commandhandling.argsorting.ArgSorter;
import main.KniazSession;

public class ListHandler implements  CommandHandler{

    private static final String[] ARG_ORDER = new String[]{};
    @Override
    public String handle(KniazSession session, String[] args) {
        String[] sortedArgs = ArgSorter.sortArgsByStarting(args,ARG_ORDER);
        // helps to check make sure it's alright
        return session.getTaskList().toPrintString();
    }
}
