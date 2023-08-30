package main.logic.handler;


import exceptions.syntax.MissingNamedArgsException;
import exceptions.syntax.MissingUnnamedArgsException;
import main.KniazSession;
import task.Event;
import task.Task;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 * Handles the event command, by creating a new Event object.
 */
public class EventHandler implements CommandHandler {


    /**
     * Handles the event command by creating a new Event
     *
     * @param session     the linked KniazSession that this command is to execute in
     * @param unnamedArgs the unnamed arguments to this command, should just be the name of the Event
     * @param namedArgs   the named arugments to this command - Should just be from and to
     * @return the user-facing string representation of the event that was created
     * @throws MissingUnnamedArgsException When the name is missing as an arg
     * @throws MissingNamedArgsException when /from or /to are missing
     */
    @Override
    public String handle(KniazSession session,
                         List<? extends String> unnamedArgs,
                         Map<? extends String, ? extends String> namedArgs)
            throws MissingUnnamedArgsException, MissingNamedArgsException {

        if (unnamedArgs.size() < 1) {
            throw new MissingUnnamedArgsException(unnamedArgs.size(), 1, null);
        }

        if (!(namedArgs.containsKey("from") && namedArgs.containsKey(("to")))){
            throw new MissingNamedArgsException(List.of("from","to"),
                    new ArrayList<>(namedArgs.keySet()),
                    null);
        }
        String taskName = unnamedArgs.get(0);
        String taskFrom = namedArgs.get("from");
        String taskTo = namedArgs.get("to");




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
