package logic.taskhandling;

import exceptions.KniazRuntimeException;
import task.Task;
import task.TaskList;
import task.ToDo;

import java.util.List;

public abstract class ToDoHandler implements commandHandler {

    public static String handle(TaskList taskList, List<String> args) throws KniazRuntimeException {
        if (args.size() > 1) {
            throw new KniazRuntimeException(
                    String.format("%s is too many args into ToDo, can only accept one,", args.size()),
                    String.format("You gave %s arguments for this operation. That is forbidden.",args.size()),
                    null);
        } else if (args.size() < 1) {
            throw new KniazRuntimeException(
                    String.format("%s is too few args into ToDo, can only accept one", args.size()),
                    String.format("You gave no task to add, what is the meaning of this?", args.size()),
                    null);
        }

        String taskName = args.get(0);
        Task taskToAdd = new ToDo(taskName);


        taskList.add(taskToAdd);

        return taskToAdd.toPrintString();

    }
}
