package commandhandling;

import commandhandling.argsorting.ArgSorter;
import exceptions.KniazRuntimeException;
import exceptions.syntax.KniazInvalidArgsException;
import main.KniazSession;
import storage.TaskList;
import task.Task;

import java.util.List;

/**
 * Abstract class containing logic of marking a task as not done.
 */
public  class UnmarkHandler implements CommandHandler {


    private static final String[] ARG_ORDER = new String[]{""};

    @Override
    public String handle(KniazSession session, String[] args) {

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
