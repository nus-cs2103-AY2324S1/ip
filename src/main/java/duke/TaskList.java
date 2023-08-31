package duke;

import java.util.ArrayList;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class TaskList {
    private static Pattern deleteCommand = Pattern.compile("^delete (?<taskNumber>\\d*)$");
    private static Pattern markUnmarkCommand = Pattern.compile("^(mark|unmark) (?<taskNumber>\\d*)$");


    private ArrayList<Task> tasks;

    TaskList() {
        this.tasks = new ArrayList<>();
    }

    TaskList(ArrayList<Task> tasks) {
        this.tasks = tasks;
    }

    public ArrayList<Task> getAll() {
        return tasks;
    }

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

    public Task delete(String command) throws LukeException {
        Matcher matcher = deleteCommand.matcher(command);
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

    public Task markAsDone(String command) throws LukeException {
        Task task = getTask(command);
        task.setIsDone(true);

        return task;
    }

    public Task markAsUndone(String command) throws LukeException {
        Task task = getTask(command);

        task.setIsDone(false);
        return task;
    }

    private Task getTask(String command) throws LukeException {
        Matcher matcher = markUnmarkCommand.matcher(command);
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
