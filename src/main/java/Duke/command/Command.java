package Duke.command;

import Duke.exception.*;
import Duke.message.Message;
import Duke.task.Task;
import Duke.application.Application;

public abstract class Command {
    protected String content;
    protected Command(String content){
        this.content = content;
    }

    public static Command of(String commandName, String content)
            throws NoCommandFoundException {
        switch (commandName) {
        case "bye":
            return new Bye(content);
        case "todo":
            return new Todo(content);
        case "deadline":
            return new Deadline(content);
        case "event":
            return new Event(content);
        case "list":
            return new List(content);
        case "mark":
            return new Mark(content);
        case "unmark":
            return new Unmark(content);
        case "delete":
            return new Delete(content);
        default:
            throw new NoCommandFoundException(commandName);
        }
    }
    public abstract Message execute(Application application) throws DukeException;
}
class Bye extends Command {
    public Bye(String content) {
        super(content);
    }
    @Override
    public Message execute(Application application) {
        application.kill();
        return Message.onExit();
    }
}

class Todo extends Command {
    public Todo(String content) {
        super(content);
    }
    @Override
    public Message execute(Application application)
            throws DukeException {
        Task task = Task.of(content, Task.TaskType.TODO);
        application.addTask(task);
        return Message.onTaskAdd(task);
    }
}
class Deadline extends Command {
    public Deadline(String content) {
        super(content);
    }
    @Override
    public Message execute(Application application)
            throws DukeException {
        Task task = Task.of(content, Task.TaskType.DEADLINE);
        application.addTask(task);
        return Message.onTaskAdd(task);
    }
}
class Event extends Command {
    public Event(String content) {
        super(content);
    }
    @Override
    public Message execute(Application application)
            throws DukeException {
        Task task = Task.of(content, Task.TaskType.EVENT);
        application.addTask(task);
        return Message.onTaskAdd((task));
    }
}
class List extends Command {
    public List(String content) {
        super(content);
    }
    @Override
    public Message execute(Application application) {
        return Message.accumulateList(Message.convertTasks(application), "\n").chainTo(
                Message.numberOfTasks(application), "\n");
    }
}
class Mark extends Command {
    public Mark(String content) {
        super(content);
    }
    @Override
    public Message execute(Application application)
            throws InvalidTaskIndexException, TaskIndexOutOfRangeException {
        int taskIndex;
        Task task;
        try {
            taskIndex = Integer.parseInt(content) - 1;
        } catch (NumberFormatException e) {
            throw new InvalidTaskIndexException(content);
        }
        try {
            task = application.getTask(taskIndex);
        } catch (IndexOutOfBoundsException e) {
            throw new TaskIndexOutOfRangeException(String.valueOf(taskIndex));
        }
        task.setCompleted();
        return Message.onTaskComplete(task);
    }
}
class Unmark extends Command {
    public Unmark(String content) {
        super(content);
    }
    @Override
    public Message execute(Application application)
            throws InvalidTaskIndexException, TaskIndexOutOfRangeException {
        int taskIndex;
        Task task;
        try {
            taskIndex = Integer.parseInt(content) - 1;
        } catch (NumberFormatException e) {
            throw new InvalidTaskIndexException(content);
        }
        try {
            task = application.getTask(taskIndex);
        } catch (IndexOutOfBoundsException e) {
            throw new TaskIndexOutOfRangeException(String.valueOf(taskIndex));
        }
        task.setUncompleted();
        return Message.onTaskUncomplete(task);
    }
}
class Delete extends Command {
    public Delete(String content) {
        super(content);
    }
    @Override
    public Message execute(Application application)
            throws InvalidTaskIndexException, TaskIndexOutOfRangeException {
        int taskIndex;
        Task task;
        try {
            taskIndex = Integer.parseInt(content) - 1;
        } catch (NumberFormatException e) {
            throw new InvalidTaskIndexException(content);
        }
        try {
            task = application.getTask(taskIndex);
        } catch (IndexOutOfBoundsException e) {
            throw new TaskIndexOutOfRangeException(String.valueOf(taskIndex));
        }
        application.removeTask((task));
        return Message.onTaskDelete(task);
    }
}