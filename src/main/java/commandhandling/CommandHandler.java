package commandhandling;

import exceptions.KniazRuntimeException;
import exceptions.syntax.KniazInvalidArgsException;
import exceptions.syntax.KniazInvalidCommandException;
import main.KniazSession;
import storage.TaskList;
import task.Task;

import java.util.List;


/**
 * Interface that encapsulates a class that executes a command.
 */
@FunctionalInterface
public interface CommandHandler {
    /**
     * Handles/executes a command
     * @param session the linked KniazSession that this command is to execute in
     * @param args the arguments to this command
     * @return the string output expected by this command
     * @throws  KniazInvalidArgsException when the arguments are somehow invalid(e.g. invalid index, wrong format)
     * @throws  KniazInvalidCommandException when the command that is to be handled is invalid (unrecognised)
     */
    public String handle(KniazSession session, String[] args) throws KniazInvalidCommandException,
            KniazInvalidArgsException;


}
