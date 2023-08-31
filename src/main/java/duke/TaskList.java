package duke;

import java.util.ArrayList;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import java.util.stream.Collectors;

public class TaskList {
    private static Pattern deleteCommand = Pattern.compile("^delete (?<taskNumber>\\d*)$");
    private static Pattern markUnmarkCommand = Pattern.compile("^(mark|unmark) (?<taskNumber>\\d*)$");
    private static Pattern findCommand = Pattern.compile("^find( (?<searchString>.*))?$");


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

    /**
     * Returns the string representation of the list of tasks
     *
     * @return String representation of the list of tasks
     */
    public String list() {
        String listStr = "";

        for (int i = 0; i < tasks.size(); i++) {
            listStr += (i + 1) + ". " + tasks.get(i - 1) + "\n";
        }

        if (listStr.isBlank()) {
            listStr = "Congrats! You have no tasks!";
        }

        return listStr.trim();
    }

    /**
     * The saving string representation of the list of tasks.
     *
     * @return Saving string representation of the list of tasks
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
     * @return ArrayList of tasks whose names has the substring
     */
    public String find(String command) {
        Matcher matcher = findCommand.matcher(command);
        matcher.find();

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
