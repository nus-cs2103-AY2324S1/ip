package main.logic.handler;

import exceptions.syntax.MissingNamedArgsException;
import exceptions.syntax.MissingUnnamedArgsException;
import exceptions.syntax.UnknownCommandException;
import main.KniazSession;
import storage.TaskList;
import task.Task;

import java.util.List;
import java.util.Map;

public class FindHandler implements CommandHandler {

    /**
     * Handles the find command by searching the sesion's list
     *
     * @param session     the linked KniazSession that this command is to execute in
     * @param unnamedArgs the unnamed arguments to this command, should just be the  keywrod to search
     * @param namedArgs   the named arugments to this command - Will be discarded, not used
     * @return the user-facing string representation of the event that was created
     * @throws MissingUnnamedArgsException When the keyword to search
     */
    @Override
    public String handle(KniazSession session,
                         List<? extends String> unnamedArgs,
                         Map<? extends String, ? extends String> namedArgs)
            throws MissingUnnamedArgsException {

        if (unnamedArgs.size() < 1) {
            throw new MissingUnnamedArgsException(unnamedArgs.size(), 1, null);
        }

        TaskList taskList = session.getTaskList();
        // search it here
        TaskList foundMatches = getAllMatchingTasks(taskList, unnamedArgs);


        return foundMatches.toPrintString();
    }

    private TaskList getAllMatchingTasks(TaskList toSearch, List<? extends String> keywords) {
        // gets the sub-tasklist of all tasks in toSearch that contain at least one of keywords
        // could just use a string but in case we want to support searching for more later or something
        TaskList out = new TaskList();

        for (String key : keywords) {

            for (Task task : toSearch) {

                if (task.getTaskName().contains(key)) {
                    out.add(task);
                }
                // This nesting's not TOO bad
            }
        }

        return out;

    }
}
