package logic.taskhandling;

import exceptions.KniazRuntimeException;
import logic.taskhandling.commandHandler;
import task.Deadline;
import task.Event;
import task.Task;
import task.TaskList;

import java.util.List;

/**
 * Encapsulation of an abstract class that handles the logic and input validation
 * of Deadline(see task.Deadline) creation
 * Includes handling of arguments into Deadline construction
 */
public abstract class DeadlineHandler implements commandHandler {

    /**
     * Handles the creation of a Deadline from a list of arguments, including input validation
     * @param taskList the taskList to place the new Deadline into
     * @param args the arguments supplied for the creation of a new Deadline
     * @return the user-facing string representation of this new Deadline
     * @throws KniazRuntimeException An exception detailing what went wrong when we tried to make a Deadline
     */
    public static String handle(TaskList taskList, List<String> args) throws KniazRuntimeException {

        if (args.size() > 2) {
            // Deadlines expect exactly 2 arguments -- name and time
            throw new KniazRuntimeException(
                    String.format("%s is too many args into making deadline can only accept two,", args.size()),
                    String.format("You gave %s arguments for this operation. That is forbidden.",args.size()),
                    null);
        } else if (args.size() < 2) {
            // Two arguments only!
            throw new KniazRuntimeException(
                    String.format("%s is too few args into Deadline, can only accept two", args.size()),
                    String.format("You gave not enough to make an Deadline, what is the meaning of this?", args.size()),
                    null);
        }

        // handle creation down here
        // pull teh parameters
        String taskName = args.get(0);
        String taskBy = args.get(1);

        //make the deadline
        Task taskToAdd = new Deadline(taskName, taskBy);

        // add it in
        taskList.add(taskToAdd);

        // for the user to see - their shiny new deadline
        return taskToAdd.toPrintString();

    }
}