package logic.taskhandling;

import exceptions.KniazRuntimeException;
import task.TaskList;

import java.util.List;


/**
 * Interface that encapsulates an abstract class that handles specific commands
 * Meant to be subclassed, this is just here as a template
 */
public interface commandHandler {

    /**
     * Handle whatever command this is supposed to. Should not be actually called, is a template
     * @param tasklist the relevant tasklist to do operations on
     * @param args the arguments to this command
     * @return the String feedback that this command should return (like the string rep. of the task that was created)
     * @throws KniazRuntimeException An exception telling you this method should not (typically) be called directly.
     */
    public static String handle(TaskList tasklist, List<String> args) throws KniazRuntimeException {
        throw new KniazRuntimeException(
                "commandHandler.Handle called directly, not meant to be!",
                "You're not meant to see this",
                null);
    }
}
