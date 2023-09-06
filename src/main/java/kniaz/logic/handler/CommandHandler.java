package kniaz.logic.handler;

import java.util.List;
import java.util.Map;

import exceptions.syntax.MissingUnnamedArgsException;
import exceptions.syntax.UnknownCommandException;
import kniaz.KniazSession;


/**
 * Interface that encapsulates a class that executes a command.
 */
@FunctionalInterface
public interface CommandHandler {
    /**
     * Handles/executes a command
     *
     * @param session     the linked KniazSession that this command is to execute in
     * @param unnamedArgs the unnamed arguments to this command
     * @param namedArgs   the named arguments to this command. Unused named arguments are discarded without complaint.
     * @return the string output expected by this command
     * @throws MissingUnnamedArgsException    when the arguments are somehow invalid(e.g. invalid index, wrong format)
     * @throws UnknownCommandException when the command that is to be handled is invalid (unrecognised)
     */


    public String handle(KniazSession session,
                         List<? extends String> unnamedArgs,
                         Map<? extends String, ? extends String> namedArgs)
            throws UnknownCommandException,
            MissingUnnamedArgsException;


}
