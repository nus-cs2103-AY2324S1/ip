package main.logic.handler;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import exceptions.syntax.MissingNamedArgsException;
import exceptions.syntax.MissingUnnamedArgsException;
import main.KniazSession;
import task.Deadline;
import task.Task;



/**
 * Encapsulates the execution of a deadline commmand, creating a new Deadline.
 */
public class DeadlineHandler implements CommandHandler {

    // the expected order of arguments to this command
    private static final String BY_NAME = "by";

    /**
     * Executes the command to create a new Deadline
     *
     * @param session     the linked KniazSession that this command is to execute in
     * @param unnamedArgs the arguments to this command
     * @param namedArgs   the named arguments to this command
     * @return the user-facing string representation of the created Deadline
     * @throws MissingUnnamedArgsException when the number of unnamed args is somehow invalid
     * @throws  MissingNamedArgsException when a required named arg is missing
     */
    @Override
    public String handle(KniazSession session,
                         List<? extends String> unnamedArgs,
                         Map<? extends String, ? extends String> namedArgs) throws MissingUnnamedArgsException,
            MissingNamedArgsException {



        if (unnamedArgs.size() < 1) {
            throw new MissingUnnamedArgsException(unnamedArgs.size(), 1, null);
        }

        if (!namedArgs.containsKey(BY_NAME)) {
            throw new MissingNamedArgsException(List.of(BY_NAME),
                    new ArrayList<>(namedArgs.keySet()),
                    null);
        }
        String taskName = unnamedArgs.get(0);

        String taskBy = namedArgs.get(BY_NAME);


        Task taskToAdd = new Deadline(taskName, taskBy);
        session.getTaskList().add(taskToAdd);

        return taskToAdd.toPrintString();
    }


}
