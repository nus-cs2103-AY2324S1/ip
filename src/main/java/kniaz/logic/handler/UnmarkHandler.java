package kniaz.logic.handler;

import java.util.List;
import java.util.Map;

import exceptions.syntax.ArgFormatException;
import exceptions.syntax.MissingUnnamedArgsException;
import exceptions.syntax.TaskListBoundsException;
import kniaz.KniazSession;
import storage.TaskList;
import task.Task;



/**
 * Handles the unmark command by un-marking specified task
 */
public class UnmarkHandler implements CommandHandler {


    private static final String[] ARG_ORDER = new String[]{""};

    /**
     * Handles unmark command by un-marking specified task
     *
     * @param session     the linked KniazSession that this command is to execute in
     * @param unnamedArgs the arguments to this command, should just be index
     * @param namedArgs  the named arguments to this command
     * @return the user-facing string representation of the unmarked task
     * @throws MissingUnnamedArgsException when there is a problem with the arguments,
     *     like index being out of bounds/unparseable
     */
    @Override
    public String handle(KniazSession session,
                         List<? extends String> unnamedArgs,
                         Map<? extends String, ? extends String> namedArgs) throws MissingUnnamedArgsException {


        if (unnamedArgs.size() < 1) {
            throw new MissingUnnamedArgsException(unnamedArgs.size(), 1, null);
        }
        String indexAsString = unnamedArgs.get(0);
        int index;
        try {
            index = Integer.parseInt(indexAsString) - 1;
        } catch (NumberFormatException e) {
            throw new ArgFormatException(String.format("%s was invalid", indexAsString),
                    String.format("I could not interpret %s as an integer, what is this?", indexAsString),
                    e);
        }


        TaskList sessionTaskList = session.getTaskList();

        if ((index < 0) || (index >= sessionTaskList.size())) {
            throw new TaskListBoundsException(session.getTaskList().size(), index, null);
        }

        Task unmarkedTask = session.getTaskList().markAsUndone(index);

        return unmarkedTask.toPrintString();
    }
}
