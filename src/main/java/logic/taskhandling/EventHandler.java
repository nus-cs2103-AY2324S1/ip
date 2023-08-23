package logic.taskhandling;

import exceptions.KniazRuntimeException;
import task.Event;
import task.Task;
import task.TaskList;
import task.ToDo;

import java.util.List;

public class EventHandler implements  commandHandler{
    public static String handle(TaskList taskList, List<String> args) throws KniazRuntimeException {
        if (args.size() > 3) {
            throw new KniazRuntimeException(
                    String.format("%s is too many args into Event, can only accept three,", args.size()),
                    String.format("You gave %s arguments for this operation. That is forbidden.",args.size()),
                    null);
        } else if (args.size() < 3) {
            throw new KniazRuntimeException(
                    String.format("%s is too few args into Event, can only accept one", args.size()),
                    String.format("You gave not enough to make an Event, what is the meaning of this?", args.size()),
                    null);
        }

        String taskName = args.get(0);
        String taskFrom = args.get(1);
        String taskTo = args.get(2);
        Task taskToAdd = new Event(taskName,taskFrom,taskTo);

        taskList.add(taskToAdd);

        return taskToAdd.toPrintString();

    }
}
