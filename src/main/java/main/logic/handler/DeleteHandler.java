package main.logic.handler;


import exceptions.syntax.KniazInvalidArgsException;
import main.KniazSession;
import storage.TaskList;
import task.Task;


import java.util.List;
import java.util.Map;

/**
 * Class encapsulating a handler for deleting tasks from a tasklist
 */
public class DeleteHandler implements CommandHandler {


/**
 * Handles deletion by deleting the task from a list, by index
 * @param session     the linked KniazSession that this command is to execute in
 * @param unnamedArgs the unnamed arguments to this command, should just be index of the task to be deleted
 * @param namedArgs   Should be empty, no named arguments are taken
 * @return the user-facing string representation of the deleted task
 * @throws KniazInvalidArgsException if an invalid argument was given, like an inappropriate or unparseable index
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

        Task deletedTask = session.getTaskList().remove(index);

        return deletedTask.toPrintString();
    }
}

    /**
     * Deletes a task, and also performs input validation and checks if the operation makes sense.
     *
     * @param taskList the tasklist to perform operations on
     * @param args     the arguments supplied for deletion
     * @return the user-facing string rep of the deleted task
     * @throws KniazRuntimeException what went wrong with trying to delete this task
     */
//    public static String handle(TaskList taskList, List<String> args) {
//
//        if (args.size() > 1) {
//            // one argument only -- the index
//            throw new KniazRuntimeException(
//                    String.format("%s is too many args into deletion, can only accept one,", args.size()),
//                    String.format("You gave %s arguments for this operation. That is forbidden.", args.size()),
//                    null);
//        } else if (args.isEmpty()) {
//            // one argument only
//            throw new KniazRuntimeException(
//                    String.format(
//                            "%s is too few args into deletion, can only accept one,", args.size()),
//                    String.format(
//                            "You gave %s arguments for this operation. How am I to know which you want?", args.size()),
//                    null);
//        }
//
//        String numberAsString = args.get(0);
//        // guaranteed to have 1 at this point
//
//        try { //try to catch problems with indexing or conversion into int
//            int index = Integer.parseInt(numberAsString) - 1;
//            //Java is 0-indexed but user-facing I/O is 1-indexed, so subtract 1 to correct
//            Task removedTask = taskList.remove(index);
//
//            return removedTask.toPrintString(); // Tell the user the dark deed of deletion is done
//
//        } catch (IndexOutOfBoundsException e) {
//            // If taskList complains that the index we tried to mark as is invalid
//            String userMessage = String.format(
//                    "There is no such entry numbered %s. Try again, but correct.", numberAsString);
//
//            throw new KniazRuntimeException(e.getMessage(), userMessage, e);
//        } catch (NumberFormatException e) {
//            // If we tried converting to an integer but it's not a valid integer
//            String userMessage = String.format(
//                    "%s is not a valid integer. Please consult your nearest mathematics textbook.", numberAsString);
//            throw new KniazRuntimeException(e.getMessage(), userMessage, e);
//
//        }


