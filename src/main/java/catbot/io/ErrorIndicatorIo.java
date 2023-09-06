package catbot.io;

import catbot.internal.NamedParameterMap;
import catbot.task.TaskList;

public interface ErrorIndicatorIo {

    void indicateInvalidCommand(String attemptedCommand);
    void indicateInvalidInteger(String attemptedInteger);
    void indicateInvalidIndex(int attemptedIndex, TaskList.Bounds bounds);

    enum InvalidState {
        PARAMETER_EMPTY, PARAMETER_MISSING
    }
    void indicateArgumentInvalid(InvalidState invalidState, NamedParameterMap namedParameterMap);

}
