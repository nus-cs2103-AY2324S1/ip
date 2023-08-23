package logic.taskhandling;

import exceptions.KniazRuntimeException;
import task.TaskList;

import java.util.List;
/**
 * Abstract class containing logic of marking a task as done AND undone.
 * Should not be called, call MarkHandler.handle or UnmarkHandler.handle respectively.
 */
public abstract class MarkUnmarkHandler implements commandHandler {
    /**
     * Marks a task as done / not done, depeending on the boolean supplied, including input validation
     * @param taskList the tasklist to perform operations on
     * @param args the arguments supplied (such as which to mark/unmark)
     * @param toSetToMarked whether to set the task as done(True) or undone(False)
     * @return the user-facing string rep of the task marked as not done
     * @throws KniazRuntimeException what went wrong with trying to mark this task as not done
     */
    protected static String handle(TaskList taskList, List<String> args, boolean toSetToMarked) {

        if (args.size() > 1) {
            // one argument only -- the index
            throw new KniazRuntimeException(
                    String.format("%s is too many args into marking/unmarking, can only accept one,", args.size()),
                    String.format("You gave %s arguments for this operation. That is forbidden.", args.size()),
                    null);
        } else if (args.isEmpty()) {
            // one argument only
            throw new KniazRuntimeException(
                    String.format(
                            "%s is too few args into marking/unmarking, can only accept one,", args.size()),
                    String.format(
                            "You gave %s arguments for this operation. How am I to know which you want?", args.size()),
                    null);
        }

        String numberAsString = args.get(0);
        // guaranteed to have 1 at this point

        try { //try to catch problems with indexing or conversion into int
            int index = Integer.parseInt(numberAsString) - 1;
            //Java is 0-indexed but user-facing I/O is 1-indexed, so subtract 1 to correct
            if (toSetToMarked) {
                taskList.markAsDone(index);
            } else {
                taskList.markAsUndone(index);
            }

            return taskList.get(index).toPrintString(); // pass up the shiny changed task to show the user!

        } catch (IndexOutOfBoundsException e) {
            // If taskList complains that the index we tried to mark as is invalid
            String userMessage = String.format(
                    "There is no such entry numbered %s. Try again, but correct.", numberAsString);

            throw new KniazRuntimeException(e.getMessage(), userMessage, e);
        } catch (NumberFormatException e) {
            // If we tried converting to an integer but it's not a valid integer
            String userMessage = String.format(
                    "%s is not a valid integer. Please consult your nearest mathematics textbook.", numberAsString);
            throw new KniazRuntimeException(e.getMessage(),userMessage,e);

        }
    }
}
