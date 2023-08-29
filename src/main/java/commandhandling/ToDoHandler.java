package commandhandling;

import commandhandling.argsorting.ArgSorter;
import exceptions.KniazRuntimeException;
import exceptions.syntax.KniazInvalidArgsException;
import main.KniazSession;
import task.Task;
import storage.TaskList;
import task.ToDo;

import java.util.List;

/**
 * Handles "todo" command by instantiating a new "todo".
 */
public  class ToDoHandler implements CommandHandler {


    private static final String[] ARG_ORDER = new String[]{""};
    /**
     * Handles todo command by making a new ToDo
     * @param session the linked KniazSession that this command is to execute in
     * @param args the arguments to this command
     * @return the user-facing string representation of the newly made Task
     * @throws KniazInvalidArgsException
     */
    @Override
    public String handle(KniazSession session, String[] args) throws KniazInvalidArgsException {

        String[] sortedArgs = ArgSorter.sortArgsByStarting(args,ARG_ORDER);
        String toDoName = sortedArgs[0];
        Task taskToAdd = new ToDo(toDoName);

        session.getTaskList().add(taskToAdd);

        return taskToAdd.toPrintString();
    }


    /**
     * Handles the creation of a ToDo from a list of arguments, including input validation
     * @param taskList the taskList to place the new ToDo into
     * @param args the arguments supplied for the creation of a new ToDo
     * @return the user-facing string representation of this new ToDo
     * @throws KniazRuntimeException An exception detailing what went wrong when we tried to make a ToDo
     */
//    public static String handle(TaskList taskList, List<String> args) throws KniazRuntimeException {
//
//        if (args.size() > 1) {
//            // ToDo accepts only one argument -- Name
//            throw new KniazRuntimeException(
//                    String.format("%s is too many args into ToDo, can only accept one,", args.size()),
//                    String.format("You gave %s arguments for this operation. That is forbidden.",args.size()),
//                    null);
//        } else if (args.size() < 1) {
//            // one argument only!
//            throw new KniazRuntimeException(
//                    String.format("%s is too few args into ToDo, can only accept one", args.size()),
//                    String.format("You gave no task to add, what is the meaning of this?", args.size()),
//                    null);
//        }
//
//        // extract from the arguments
//        String taskName = args.get(0);
//
//        // make the ToDo
//        Task taskToAdd = new ToDo(taskName);
//
//
//        //Chuck it into the list
//        taskList.add(taskToAdd);
//
//        // pass up what the user needs to see for feedback
//        return taskToAdd.toPrintString();
//
//    }
}
