package Duke.command;

import Duke.exception.*;
import Duke.message.Message;
import Duke.task.Task;
import Duke.application.Application;

/**
 * Represents an abstract command that interacts with the application with some content string.
 */
public abstract class Command {
    protected String content;
    protected Command(String content){
        this.content = content;
    }

    /**
     * Creates a Command instance based on the given command name and content.
     *
     * @param commandName The name of the command.
     * @param content The content associated with the command.
     * @return A Command instance corresponding to the specified command name.
     * @throws NoCommandFoundException If the provided command name is not recognized.
     */
    public static Command Of(String commandName, String content)
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

    /**
     * Executes the command and interacts with the application.
     *
     * @param application The application instance with which the command interacts.
     * @return A Message instance representing the result of executing the command.
     * @throws DukeException If an error occurs during command execution.
     */
    public abstract Message execute(Application application) throws DukeException;
}

/**
 * Represents a command to exit the application.
 */
class Bye extends Command {
    protected Bye(String content) {
        super(content);
    }
    @Override
    public Message execute(Application application) {
        application.kill();
        return Message.OnExit();
    }
}

/**
 * Represents a command to add a To-do task to the task list.
 */
class Todo extends Command {
    protected Todo(String content) {
        super(content);
    }
    @Override
    public Message execute(Application application)
            throws DukeException {
        Task task = Task.Of(content, Task.TaskType.TODO);
        application.AddTask(task);
        return Message.OnTaskAdd(task);
    }
}

/**
 * Represents a command to add a Deadline task to the task list.
 */
class Deadline extends Command {
    protected Deadline(String content) {
        super(content);
    }
    @Override
    public Message execute(Application application)
            throws DukeException {
        Task task = Task.Of(content, Task.TaskType.DEADLINE);
        application.AddTask(task);
        return Message.OnTaskAdd(task);
    }
}

/**
 * Represents a command to add an Event task to the task list.
 */
class Event extends Command {
    protected Event(String content) {
        super(content);
    }
    @Override
    public Message execute(Application application)
            throws DukeException {
        Task task = Task.Of(content, Task.TaskType.EVENT);
        application.AddTask(task);
        return Message.OnTaskAdd((task));
    }
}

/**
 * Represents a command to list tasks in the application.
 */
class List extends Command {
    protected List(String content) {
        super(content);
    }
    @Override
    public Message execute(Application application) {
        return Message.AccumulateList(Message.ConvertTasks(application), "\n").ChainTo(
                Message.NumberOfTasks(application), "\n");
    }
}

/**
 * Represents a command to mark a task as completed.
 */
class Mark extends Command {
    protected Mark(String content) {
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
            task = application.GetTask(taskIndex);
        } catch (IndexOutOfBoundsException e) {
            throw new TaskIndexOutOfRangeException(String.valueOf(taskIndex));
        }
        task.SetCompleted();
        return Message.OnTaskComplete(task);
    }
}

/**
 * Represents a command to unmark a completed task.
 */
class Unmark extends Command {
    protected Unmark(String content) {
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
            task = application.GetTask(taskIndex);
        } catch (IndexOutOfBoundsException e) {
            throw new TaskIndexOutOfRangeException(String.valueOf(taskIndex));
        }
        task.SetUncompleted();
        return Message.OnTaskUncomplete(task);
    }
}

/**
 * Represents a command to delete a task from the task list.
 */
class Delete extends Command {
    protected Delete(String content) {
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
            task = application.GetTask(taskIndex);
        } catch (IndexOutOfBoundsException e) {
            throw new TaskIndexOutOfRangeException(String.valueOf(taskIndex));
        }
        application.RemoveTask((task));
        return Message.OnTaskDelete(task);
    }
}