package main.logic.handler;


import exceptions.syntax.KniazInvalidArgsException;
import main.KniazSession;
import storage.TaskList;
import task.Task;

import java.util.List;
import java.util.Map;

/**
 * Handles the mark command, by marking the specified task as done
 */
public class MarkHandler implements CommandHandler {


    // argument not expected to have prefix, just an index

    /**
     * Handles the mark command by marking the specified task as done, returning the user-facing string representation
     * of the marked task
     *
     * @param session     the linked KniazSession that this command is to execute in
     * @param unnamedArgs the arguments to this command, should just be the index of the task to mark
     * @param namedArgs   the named arguments to this command, should be none
     * @return the user-facing string representation of the marked task
     * @throws KniazInvalidArgsException when the arguments are invalid, like when the index is out of bounds
     */
    @Override
    public String handle(KniazSession session,
                         List<? extends String> unnamedArgs,
                         Map<? extends String, ? extends String> namedArgs) throws KniazInvalidArgsException {



        String indexAsString = unnamedArgs.get(0);
        int index = Integer.parseInt(indexAsString) - 1;

        TaskList sessionTaskList = session.getTaskList();

        if ((index < 0 ) || (index >= sessionTaskList.size())) {
            throw new KniazInvalidArgsException();
        }

        Task markedTask = session.getTaskList().markAsDone(index);

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
