package commandhandling;

import commandhandling.argsorting.ArgSorter;
import exceptions.KniazRuntimeException;
import exceptions.syntax.KniazInvalidArgsException;
import main.KniazSession;
import storage.TaskList;
import task.Task;

import java.util.List;

/**
 * Abstract class containing logic of marking a task as done.
 */
public class MarkHandler implements CommandHandler {

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

        Task markedTask = session.getTaskList().markAsUndone(index);

        return markedTask.toPrintString();
    }
    //    /**
//     * Marks a task as done, and also performs input validation and checks if the operation makes sense.
//     * Calls MarkUnmarkHandler for most of the actual implementation.
//     * @param taskList the tasklist to perform operations on
//     * @param args the arguments supplied to mark as done
//     * @return the user-facing string rep of the task marked as done
//     * @throws KniazRuntimeException what went wrong with trying to mark this task as done
//     */
//    public static String handle(TaskList taskList, List<String> args) throws KniazRuntimeException {
//        return MarkUnmarkHandler.handle(taskList,args,true);
//    }
}
