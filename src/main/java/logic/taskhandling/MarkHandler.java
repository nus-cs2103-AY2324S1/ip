package logic.taskhandling;

import exceptions.KniazRuntimeException;
import task.TaskList;

import java.util.List;

public abstract class MarkHandler implements commandHandler {
    public static String handle(TaskList taskList, List<String> args) throws KniazRuntimeException {
        return MarkUnmarkHandler.handle(taskList,args,true);
    }
}
