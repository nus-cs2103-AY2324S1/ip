package commandhandling;

import commandhandling.argsorting.ArgSorter;
import exceptions.KniazRuntimeException;
import main.KniazSession;
import task.Deadline;
import task.Event;
import task.Task;
import storage.TaskList;


import java.util.List;

/**
 * Encapsulation of an abstract class that handles the logic and input validation
 * of Event(see task.Event) creation
 * Includes handling of arguments into Event construction
 */
public class EventHandler implements CommandHandler {

    private static final String[] ARGORDER = new String[]{"","/from","/to"};
    @Override
    public String handle(KniazSession session, String[] args) {


        String[] sortedArgs = ArgSorter.sortArgsByStarting(args,ARGORDER);

        String taskName = sortedArgs[0];
        String taskFrom = sortedArgs[1];
        String taskTo = sortedArgs[2];


        Task taskToAdd = new Event(taskName,taskFrom,taskTo);
        session.getTaskList().add(taskToAdd);

        return taskToAdd.toPrintString();
    }

    //    /**
//     * Handles the creation of an Event from a list of arguments, including input validation
//     * @param taskList the taskList to place the new Event into
//     * @param args the arguments supplied for the creation of a new Event
//     * @return the user-facing string representation of this new Event
//     * @throws KniazRuntimeException An exception detailing what went wrong when we tried to make a Event
//     */
//    public static String handle(TaskList taskList, List<String> args) throws KniazRuntimeException {
//        if (args.size() > 3) {
//            //Event accepts only three arguments -- name, from, to.
//            throw new KniazRuntimeException(
//                    String.format("%s is too many args into Event, can only accept three,", args.size()),
//                    String.format("You gave %s arguments for this operation. That is forbidden.",args.size()),
//                    null);
//        } else if (args.size() < 3) {
//            // Three arguments only!
//            throw new KniazRuntimeException(
//                    String.format("%s is too few args into Event, can only accept one", args.size()),
//                    String.format("You gave not enough to make an Event, what is the meaning of this?", args.size()),
//                    null);
//        }
//
//        // pull the indivivual arguments out
//        String taskName = args.get(0);
//        String taskFrom = args.get(1);
//        String taskTo = args.get(2);
//
//        //make the event
//        Task taskToAdd = new Event(taskName,taskFrom,taskTo);
//
//        // add it to the list
//        taskList.add(taskToAdd);
//
//        // pass the user-facing strip rep of this up so the user can see their new Event
//        return taskToAdd.toPrintString();
//
//    }
}
