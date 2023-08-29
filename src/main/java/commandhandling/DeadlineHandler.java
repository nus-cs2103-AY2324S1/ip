package commandhandling;

import commandhandling.argsorting.ArgSorter;
import exceptions.syntax.KniazInvalidArgsException;
import main.KniazSession;
import task.Deadline;
import task.Task;

/**
 * Encapsulates the execution of a deadline commmand, creating a new Deadline.
 */
public class DeadlineHandler implements CommandHandler {

    // the expected order of arguments to this command
    private static final String[] ARG_ORDER = new String[]{"","/by"};

    /**
     * Executes the command to create a new Deadline
     * @param session the linked KniazSession that this command is to execute in
     * @param args    the arguments to this command
     * @return the user-facing string representation of the created Deadline
     * @throws  KniazInvalidArgsException when the arguments are invalid, such as not having a "/by {TIME}" argument
     */
    @Override
    public String handle(KniazSession session, String[] args) throws KniazInvalidArgsException {


        String[] sortedArgs = ArgSorter.sortArgsByStarting(args,ARG_ORDER);

        String taskName = sortedArgs[0];
        String taskBy = sortedArgs[1];


        Task taskToAdd = new Deadline(taskName,taskBy);
        session.getTaskList().add(taskToAdd);

        return taskToAdd.toPrintString();
    }

    //    /**
//     * Handles the creation of a Deadline from a list of arguments, including input validation
//     * @param taskList the taskList to place the new Deadline into
//     * @param args the arguments supplied for the creation of a new Deadline
//     * @return the user-facing string representation of this new Deadline
//     * @throws KniazRuntimeException An exception detailing what went wrong when we tried to make a Deadline
//     */
//    public static String handle(TaskList taskList, List<String> args) throws KniazRuntimeException {
//
//        if (args.size() > 2) {
//            // Deadlines expect exactly 2 arguments -- name and time
//            throw new KniazRuntimeException(
//                    String.format("%s is too many args into making deadline can only accept two,", args.size()),
//                    String.format("You gave %s arguments for this operation. That is forbidden.",args.size()),
//                    null);
//        } else if (args.size() < 2) {
//            // Two arguments only!
//            throw new KniazRuntimeException(
//                    String.format("%s is too few args into Deadline, can only accept two", args.size()),
//                    String.format("You gave not enough to make an Deadline, what is the meaning of this?", args.size()),
//                    null);
//        }
//
//        // handle creation down here
//        // pull teh parameters
//        String taskName = args.get(0);
//        String taskBy = args.get(1);
//
//        //make the deadline
//        Task taskToAdd = new Deadline(taskName, taskBy);
//
//        // add it in
//        taskList.add(taskToAdd);
//
//        // for the user to see - their shiny new deadline
//        return taskToAdd.toPrintString();
}
