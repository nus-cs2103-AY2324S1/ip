package logic.taskhandling;

import exceptions.KniazRuntimeException;
import task.TaskList;

import java.util.List;

/**
 * Abstract class containing logic of marking a task as not done.
 */
public abstract class UnmarkHandler implements commandHandler {

    /**
     * Marks a task as not done, and also performs input validation and checks if the operation makes sense.
     * Calls MarkUnmarkHandler for most of the actual implementation.
     * @param taskList the tasklist to perform operations on
     * @param args the arguments supplied to mark as not done
     * @return the user-facing string rep of the task marked as not done
     * @throws KniazRuntimeException what went wrong with trying to mark this task as not done
     */
    public static String handle(TaskList taskList, List<String> args) throws KniazRuntimeException {
        return MarkUnmarkHandler.handle(taskList,args,false);
    }
}
