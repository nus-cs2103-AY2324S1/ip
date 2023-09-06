package kniaz.logic.handler;

import java.util.List;
import java.util.Map;

import exceptions.syntax.MissingUnnamedArgsException;
import kniaz.KniazSession;
import task.Task;
import task.ToDo;



/**
 * Handles "todo" command by instantiating a new "todo".
 */
public class ToDoHandler implements CommandHandler {


    private static final String[] ARG_ORDER = new String[]{""};
    /**
     * Handles todo command by making a new ToDo
     *
     * @param session     the linked KniazSession that this command is to execute in
     * @param unnamedArgs the arguments to this command, should just be name of the Task
     * @param namedArgs   the named arguments, ignored
     * @return the user-facing string representation of the newly made Task
     * @throws MissingUnnamedArgsException when the name is missing
     */
    @Override
    public String handle(KniazSession session,
                         List<? extends String> unnamedArgs,
                         Map<? extends String, ? extends String> namedArgs) throws MissingUnnamedArgsException {


        if (unnamedArgs.size() < 1) {
            throw new MissingUnnamedArgsException(unnamedArgs.size(), 1, null);
        }
        String toDoName = unnamedArgs.get(0);
        Task taskToAdd = new ToDo(toDoName);

        session.getTaskList().add(taskToAdd);

        return taskToAdd.toPrintString();
    }


}
