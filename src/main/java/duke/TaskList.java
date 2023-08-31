package duke;

import java.sql.Array;
import java.util.ArrayList;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class TaskList {
    private static final Pattern PATTERN_COMMAND_DELETE = Pattern.compile("^delete (?<taskNumber>\\d*)$");
    private static final Pattern PATTERN_COMMAND_MARK_UNMARK = Pattern.compile("^(mark|unmark) (?<taskNumber>\\d*)$");


    private ArrayList<Task> tasks;

    TaskList() {
        this.tasks = new ArrayList<>();
    }

    TaskList(ArrayList<Task> tasks) {
        this.tasks = tasks;
    }

    /**
     * Returns the current list of tasks
     *
     * @return ArrayList of tasks
     */
    public ArrayList<Task> getAll() {
        return tasks;
    }

    /**
     * Creates and adds a type of task to tasks
     *
     * @param command String command specifying the type of task and associated values to add
     * @return Task created
     * @throws LukeException If the first word in the command is not a recognised command
     */
    public Task add(String command) throws LukeException {
        Task task;
        switch (command.split(" ")[0]) {
        case "todo":
            task = Todo.createTodo(command);
            break;
        case "deadline":
            task = Deadline.createDeadline(command);
            break;
        case "event":
            task = Event.createEvent(command);
            break;
        default:
            throw new LukeException("Unknown command");
        }

        tasks.add(task);
        return task;
    }

    /**
     * Deletes and Returns a specified task from tasks
     *
     * @param command String command specifying which task to delete
     * @return Deleted task
     * @throws LukeException If the command is of invalid format, or the number specified is an invalid number.
     */
    public Task delete(String command) throws LukeException {
        Matcher matcher = PATTERN_COMMAND_DELETE.matcher(command);
        if (!matcher.find()) {
            throw new LukeException("Invalid format. Usage: delete {task_number}");
        }

        String taskNumber = matcher.group("taskNumber");
        if (taskNumber == null || taskNumber.isBlank()) {
            throw new LukeException("Task number cannot be empty.");
        }

        int taskIndex = Integer.parseInt(taskNumber) - 1;
        if (taskIndex < 0 || taskIndex >= tasks.size()) {
            throw new LukeException("Invalid task number: no such task");
        }

        Task task = tasks.get(taskIndex);
        tasks.remove(taskIndex);
        return task;
    }

    /**
     * Mark a task as done
     *
     * @param command String command specifying which task to mark as done
     * @return Task that is successfully marked as done
     * @throws LukeException If task is already marked as done
     */
    public Task markAsDone(String command) throws LukeException {
        Task task = getTask(command);
        task.setDone(true);

        return task;
    }

    /**
     * Mark a task as undone
     *
     * @param command String command specifying which task to mark as undone
     * @return Task that is successfully marked as undone
     * @throws LukeException If the task is already marked as undone
     */
    public Task markAsUndone(String command) throws LukeException {
        Task task = getTask(command);

        task.setDone(false);
        return task;
    }

    private Task getTask(String command) throws LukeException {
        Matcher matcher = PATTERN_COMMAND_MARK_UNMARK.matcher(command);
        if (!matcher.find()) {
            throw new LukeException("Invalid format. Usage: mark {task_number}");
        }

        String taskNumber = matcher.group("taskNumber");
        if (taskNumber == null || taskNumber.isBlank()) {
            throw new LukeException("Task number cannot be empty.");
        }

        int taskIndex = Integer.parseInt(taskNumber) - 1;
        if (taskIndex < 0 || taskIndex >= tasks.size()) {
            throw new LukeException("Invalid task number: no such task");
        }

        return tasks.get(taskIndex);
    }
}
