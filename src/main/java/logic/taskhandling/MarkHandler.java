package logic.taskhandling;

import exceptions.KniazRuntimeException;
import task.TaskList;

import java.util.List;

/**
 * Abstract class containing logic of marking a task as done.
 */
public abstract class MarkHandler implements commandHandler {

    /**
     * Marks a task as done, and also performs input validation and checks if the operation makes sense.
     * Calls MarkUnmarkHandler for most of the actual implementation.
     * @param taskList the tasklist to perform operations on
     * @param args the arguments supplied to mark as done
     * @return the user-facing string rep of the task marked as done
     * @throws KniazRuntimeException what went wrong with trying to mark this task as done
     */
    public static String handle(TaskList taskList, List<String> args) throws KniazRuntimeException {
        return MarkUnmarkHandler.handle(taskList,args,true);
    }
}
