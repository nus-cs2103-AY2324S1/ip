package commandhandling;

import commandhandling.argsorting.ArgSorter;
import exceptions.KniazRuntimeException;
import exceptions.syntax.KniazInvalidArgsException;
import main.KniazSession;
import storage.TaskList;
import task.Task;

import java.util.List;

/**
 * Handles the unmark command by un-marking specified task
 */
public  class UnmarkHandler implements CommandHandler {


    private static final String[] ARG_ORDER = new String[]{""};

    /**
     * Handles unmark command by un-marking specified task
     * @param session the linked KniazSession that this command is to execute in
     * @param args the arguments to this command
     * @return the user-facings tring representation of the unmarked task
     * @throws KniazInvalidArgsException when there is a problem with the arguments, like index being out of bounds
     */
    @Override
    public String handle(KniazSession session, String[] args) throws KniazInvalidArgsException{

        String[] sortedArgs = ArgSorter.sortArgsByStarting(args,ARG_ORDER);
        // appears redundant but acts as another gatekeeper to make sure the arg syntax is right

        String indexAsString = args[0];
        int index = Integer.parseInt(indexAsString) - 1;

        TaskList sessionTaskList = session.getTaskList();

        if ((index < 0 ) || (index >= sessionTaskList.size())) {
            throw new KniazInvalidArgsException();
        }

        Task unmarkedTask = session.getTaskList().markAsUndone(index);

        return unmarkedTask.toPrintString();
    }
}
