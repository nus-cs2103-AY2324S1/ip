package duke.command;

import duke.application.Application;
import duke.exception.DukeException;
import duke.exception.InvalidTaskIndexException;
import duke.exception.NoCommandFoundException;
import duke.exception.TaskIndexOutOfRangeException;
import duke.message.Message;
import duke.task.Task;

/**
 * Represents an abstract command that interacts with the application with some content string.
 */
public abstract class Command {
    protected String content;
    protected Command(String content) {
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
        case "find":
            return new Find(content);
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
        return Message.onExit();
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
        Task task = Task.of(content, Task.TaskType.TODO);
        application.addTask(task);
        return Message.onTaskAdd(task);
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
        Task task = Task.of(content, Task.TaskType.DEADLINE);
        application.addTask(task);
        return Message.onTaskAdd(task);
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
        Task task = Task.of(content, Task.TaskType.EVENT);
        application.addTask(task);
        return Message.onTaskAdd((task));
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
        return Message.accumulateList(Message.convertTasks(application.getTaskList()), "\n").chainTo(
                Message.onList(application.getTaskList()), "\n");
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
            task = application.getTask(taskIndex);
        } catch (IndexOutOfBoundsException e) {
            throw new TaskIndexOutOfRangeException(String.valueOf(taskIndex));
        }
        task.setCompleted();
        return Message.onTaskComplete(task);
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
            task = application.getTask(taskIndex);
        } catch (IndexOutOfBoundsException e) {
            throw new TaskIndexOutOfRangeException(String.valueOf(taskIndex));
        }
        task.setUncompleted();
        return Message.onTaskUncomplete(task);
    }
}

class Find extends Command {
    public Find(String content) {
        super(content);
    }

    @Override
    public Message execute(Application application) {
        return Message.onTaskFind().chainTo(
                Message.accumulateList(Message.convertTasks(application.findMatchingTasks(content)), "\n"),
                "\n");
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
            task = application.getTask(taskIndex);
        } catch (IndexOutOfBoundsException e) {
            throw new TaskIndexOutOfRangeException(String.valueOf(taskIndex));
        }
        application.removeTask((task));
        return Message.onTaskDelete(task);
    }
}
