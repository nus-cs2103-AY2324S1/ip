package main.logic.handler;

import java.util.List;
import java.util.Map;

import exceptions.syntax.ArgFormatException;
import exceptions.syntax.MissingUnnamedArgsException;
import exceptions.syntax.TaskListBoundsException;
import main.KniazSession;
import storage.TaskList;
import task.Task;




/**
 * Class encapsulating a handler for deleting tasks from a tasklist
 */
public class DeleteHandler implements CommandHandler {
    /**
     * Handles deletion by deleting the task from a list, by index
     * @param session     the linked KniazSession that this command is to execute in
     * @param unnamedArgs the unnamed arguments to this command, should just be index of the task to be deleted
     * @param namedArgs   Should be empty, no named arguments are taken
     * @return the user-facing string representation of the deleted task
     * @throws  ArgFormatException when the argument provided cannot be parsed into an integer
     * @throws TaskListBoundsException when an index out of bounds is attempted to be deleted
     */
    @Override
    public String handle(KniazSession session,
                         List<? extends String> unnamedArgs,
                         Map<? extends String, ? extends String> namedArgs) throws ArgFormatException,
            TaskListBoundsException {

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

        Task deletedTask = session.getTaskList().remove(index);

        return deletedTask.toPrintString();
    }
}
