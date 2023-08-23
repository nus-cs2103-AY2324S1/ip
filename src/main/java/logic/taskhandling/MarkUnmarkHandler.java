package logic.taskhandling;

import exceptions.KniazRuntimeException;
import task.TaskList;

import java.util.List;

public abstract class MarkUnmarkHandler implements commandHandler {




    protected static String handle(TaskList taskList, List<String> args, boolean toSetToMarked) {

        if (args.size() > 1) {
            throw new KniazRuntimeException(
                    String.format("%s is too many args into marking/unmarking, can only accept one,", args.size()),
                    String.format("You gave %s arguments for this operation. That is forbidden.", args.size()),
                    null);
        } else if (args.isEmpty()) {
            throw new KniazRuntimeException(
                    String.format("%s is too few args into marking/unmarking, can only accept one,", args.size()),
                    String.format("You gave %s arguments for this operation. How am I to know which you want?", args.size()),
                    null);
        }

        String numberAsString = args.get(0);
        // guaranteed to have 1 at this point

        try {
            int index = Integer.parseInt(numberAsString) - 1;
            //Java is 0-indexed but user-facing I/O is 1-indexed, so subtract 1 to correct
            if (toSetToMarked) {
                taskList.markAsDone(index);
            } else {
                taskList.markAsUndone(index);
            }

            return taskList.get(index).toPrintString();

        } catch (IndexOutOfBoundsException e) {
            String userMessage = String.format(
                    "There is no such entry numbered %s. Try again, but correct.", numberAsString);

            throw new KniazRuntimeException(e.getMessage(), userMessage, e);
        } catch (NumberFormatException e) {
            String userMessage = String.format(
                    "%s is not a valid integer. Please consult your nearest mathematics textbook.", numberAsString);
            throw new KniazRuntimeException(e.getMessage(),userMessage,e);

        }
    }
}
