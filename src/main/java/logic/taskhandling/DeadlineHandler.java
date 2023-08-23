package logic.taskhandling;

import exceptions.KniazRuntimeException;
import logic.taskhandling.commandHandler;
import task.Deadline;
import task.Event;
import task.Task;
import task.TaskList;

import java.util.List;

public class DeadlineHandler implements commandHandler {
    public static String handle(TaskList taskList, List<String> args) throws KniazRuntimeException {
        if (args.size() > 2) {
            throw new KniazRuntimeException(
                    String.format("%s is too many args into making deadline can only accept two,", args.size()),
                    String.format("You gave %s arguments for this operation. That is forbidden.",args.size()),
                    null);
        } else if (args.size() < 2) {
            throw new KniazRuntimeException(
                    String.format("%s is too few args into Deadline, can only accept two", args.size()),
                    String.format("You gave not enough to make an Deadline, what is the meaning of this?", args.size()),
                    null);
        }

        String taskName = args.get(0);
        String taskBy = args.get(1);

        Task taskToAdd = new Deadline(taskName, taskBy);

        taskList.add(taskToAdd);

        return taskToAdd.toPrintString();

    }
}