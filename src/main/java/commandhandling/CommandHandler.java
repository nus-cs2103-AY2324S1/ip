package commandhandling;

import exceptions.KniazRuntimeException;
import main.KniazSession;
import storage.TaskList;
import task.Task;

import java.util.List;


/**
 * Interface that encapsulates an abstract class that handles specific commands
 * Meant to be subclassed, this is just here as a template
 */
@FunctionalInterface
public interface CommandHandler {
    public String handle(KniazSession session, String[] args);


}
