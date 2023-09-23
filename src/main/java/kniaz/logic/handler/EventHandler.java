package kniaz.logic.handler;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import exceptions.syntax.MissingNamedArgsException;
import exceptions.syntax.MissingUnnamedArgsException;
import kniaz.KniazSession;
import task.Event;
import task.Task;



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

        if (!(namedArgs.containsKey("from") && namedArgs.containsKey(("to")))) {
            throw new MissingNamedArgsException(List.of("from", "to"),
                    new ArrayList<>(namedArgs.keySet()),
                    null);
        }
        String taskName = unnamedArgs.get(0);
        String taskFrom = namedArgs.get("from");
        String taskTo = namedArgs.get("to");




        Task taskToAdd = new Event(taskName, taskFrom, taskTo);
        session.getTaskList().add(taskToAdd);

        return taskToAdd.toPrintString();
    }

}
