package kniaz.logic.handler;

import java.util.List;
import java.util.Map;

import exceptions.syntax.ArgFormatException;
import exceptions.syntax.MissingUnnamedArgsException;
import exceptions.syntax.TaskListBoundsException;
import exceptions.syntax.UnknownCommandException;
import kniaz.KniazSession;
import storage.TaskList;


/**
 * Class that handles the tag command
 */
public class TagHandler implements CommandHandler {
    /**
     * Handles/executes tagging
     *
     * @param session     the linked KniazSession that this command is to execute in
     * @param unnamedArgs the unnamed arguments to this command
     * @param namedArgs   the named arguments to this command. Unused named arguments are discarded without complaint.
     * @return the string output expected by this command
     * @throws MissingUnnamedArgsException when the arguments are somehow invalid(e.g. invalid index, wrong format)
     * @throws UnknownCommandException     when the command that is to be handled is invalid (unrecognised)
     */
    @Override
    public String handle(KniazSession session,
                         List<? extends String> unnamedArgs,
                         Map<? extends String, ? extends String> namedArgs)
            throws UnknownCommandException, MissingUnnamedArgsException {

        if (unnamedArgs.size() < 2) {
            throw new MissingUnnamedArgsException(unnamedArgs.size(), 2, null);
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
        String tag = unnamedArgs.get(1);
        return session.getTaskList().addTag(index, tag).toPrintString();
    }
}
