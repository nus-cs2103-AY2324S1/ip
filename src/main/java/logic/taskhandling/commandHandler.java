package logic.taskhandling;

import exceptions.KniazRuntimeException;
import task.TaskList;

import java.util.List;

public interface commandHandler {
    public static String handle(TaskList tasklist, List<String> args) throws KniazRuntimeException {
        throw new KniazRuntimeException(
                "commandHandler instantiated directly, not meant to be!",
                "You're not meant to see this",
                null);
    }
}
