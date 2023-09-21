package duke;

import java.util.ArrayList;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * Represents a list of tasks and provides methods to manipulate the list.
 */
public class TaskList {
    private static final Pattern PATTERN_COMMAND_DELETE = Pattern.compile("^(del|delete) (?<taskNumber>\\d*)$");
    private static final Pattern PATTERN_COMMAND_MARK_UNMARK =
            Pattern.compile("^(m|mark|um|unmark) (?<taskNumber>\\d*)$");
    private static final Pattern PATTERN_COMMAND_FIND = Pattern.compile("^(f|find)( (?<searchString>.*))?$");

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
     * Returns the string representation of the list of tasks
     *
     * @return String representation of the list of tasks
     */
    public String list() {
        String listStr = "";

        for (int i = 0; i < tasks.size(); i++) {
            listStr += (i + 1) + ". " + tasks.get(i) + "\n";
        }

        if (listStr.isBlank()) {
            listStr = "Congrats! You have no tasks!";
        }

        return listStr.trim();
    }

    /**
     * Returns the string representation of the list of tasks for saving
     *
     * @return the string representation of the list of tasks for saving
     */
    public String toSaveString() {
        String inputTxt = "";

        for (Task task : tasks) {
            inputTxt += task.toSaveStr() + "\n";
        }

        return inputTxt.trim();
    }

    /**
     * Returns a list of tasks whose names contain the specified substring
     *
     * @param command Command specifying the substring to search for
     * @return an arrayList of tasks whose names has the substring
     */
    public String find(String command) {
        Matcher matcher = PATTERN_COMMAND_FIND.matcher(command);
        boolean found = matcher.find();
        assert(found);

        String searchString = matcher.group("searchString");
        if (searchString == null || searchString.isBlank()) {
            return list();
        }

        String result = "";
        for (int i = 0; i < tasks.size(); ++i) {
            Task task = tasks.get(i);
            if (!task.hasSubstring(searchString)) {
                continue;
            }

            result += (i + 1) + ". " + task + "\n";
        }

        if (result.isBlank()) {
            result = "You have no tasks that contain this substring!";
        }

        return result;
    }

    /**
     * Creates and adds a type of task to tasks
     *
     * @param command String command specifying the type of task and associated values to add
     * @return the task created
     * @throws LukeException If the first word in the command is not a recognised command
     */
    public Task add(String command) throws LukeException {
        Task task;
        switch (command.split(" ")[0]) {
        case "t":
            // Fallthrough
        case "todo":
            task = Todo.createTodo(command);
            break;
        case "d":
            // Fallthrough
        case "deadline":
            task = Deadline.createDeadline(command);
            break;
        case "e":
            // Fallthrough
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
     * Deletes  a specified task from the tasks
     *
     * @param command String command specifying which task to delete
     * @return the deleted task
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
     * Marks a task as done
     *
     * @param command String command specifying which task to mark as done
     * @return the task that is successfully marked as done
     * @throws LukeException If task is already marked as done
     */
    public Task markAsDone(String command) throws LukeException {
        Task task = getTask(command);
        task.setDone(true);

        return task;
    }

    /**
     * Marks a task as undone
     *
     * @param command String command specifying which task to mark as undone
     * @return the task that is successfully marked as undone
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
