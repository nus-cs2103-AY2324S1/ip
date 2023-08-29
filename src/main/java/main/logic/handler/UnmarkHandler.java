package main.logic.handler;


import exceptions.syntax.KniazInvalidArgsException;
import main.KniazSession;
import storage.TaskList;
import task.Task;

import java.util.List;
import java.util.Map;

/**
 * Handles the unmark command by un-marking specified task
 */
public  class UnmarkHandler implements CommandHandler {


    private static final String[] ARG_ORDER = new String[]{""};

    /**
     * Handles unmark command by un-marking specified task
     *
     * @param session     the linked KniazSession that this command is to execute in
     * @param unnamedArgs the arguments to this command
     * @param namedArgs
     * @return the user-facings tring representation of the unmarked task
     * @throws KniazInvalidArgsException when there is a problem with the arguments, like index being out of bounds
     */
    @Override
    public String handle(KniazSession session,
                         List<? extends String> unnamedArgs,
                         Map<? extends String, ? extends String> namedArgs) throws KniazInvalidArgsException{


        String indexAsString = unnamedArgs.get(0);
        int index = Integer.parseInt(indexAsString) - 1;

        TaskList sessionTaskList = session.getTaskList();

        if ((index < 0 ) || (index >= sessionTaskList.size())) {
            throw new KniazInvalidArgsException();
        }

        Task unmarkedTask = session.getTaskList().markAsUndone(index);

        return unmarkedTask.toPrintString();
    }
}
