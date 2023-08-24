import javafx.css.Match;

import java.util.ArrayList;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Task {
    private static Pattern markUnmarkCommand = Pattern.compile("^(mark|unmark) (?<taskNumber>\\d*)$");
    private static Pattern deleteCommand = Pattern.compile("^delete (?<taskNumber>\\d*)$");
    private static ArrayList<Task> allTasks = new ArrayList<>();
    private boolean isDone;
    private String name;

    Task(String name) {
        this.name = name;
        this.isDone = false;

        allTasks.add(this);
    }

    public static Task deleteTask(String command) throws LukeException {
        Matcher matcher = deleteCommand.matcher(command);
        if (!matcher.find()) {
            throw new LukeException("Invalid format. Usage: delete {task_number}");
        }

        String taskNumber = matcher.group("taskNumber");
        if (taskNumber == null || taskNumber.isBlank()) {
            throw new LukeException("Task number cannot be empty.");
        }

        int taskIndex = Integer.parseInt(taskNumber) - 1;
        if (taskIndex < 0 || taskIndex >= allTasks.size()) {
            throw new LukeException("Invalid task number: no such task");
        }

        Task task = allTasks.get(taskIndex);
        allTasks.remove(taskIndex);
        return task;
    }

    public static Task markUnmarkTask(String command) throws LukeException {
        Matcher matcher = markUnmarkCommand.matcher(command);
        if (!matcher.find()) {
            throw new LukeException("Invalid format. Usage: mark {task_number}");
        }

        String taskNumber = matcher.group("taskNumber");
        if (taskNumber == null || taskNumber.isBlank()) {
            throw new LukeException("Task number cannot be empty.");
        }

        int taskIndex = Integer.parseInt(taskNumber) - 1;
        if (taskIndex < 0 || taskIndex >= allTasks.size()) {
            throw new LukeException("Invalid task number: no such task");
        }

        return setTaskIsDone(taskIndex, command.split(" ")[0].equals("mark"));
    }

    public static void listTasks(String command) {
        String list = "";
        for (int i = 1; i <= allTasks.size(); i++) {
            list += i + ". " + allTasks.get(i - 1).toString() + "\n";
        }

        if (list.isBlank()) {
            Util.displayMessage("Congrats! You have no tasks!");
            return;
        }

        Util.displayMessage(list.trim());
    }

    public static Task addTask(String input) throws LukeException {
        Task task;
        switch (input.split(" ")[0]) {
            case "todo":
                task = Todo.createTodo(input);
                break;
            case "deadline":
                task = Deadline.createDeadline(input);
                break;
            case "event":
                task = Event.createEvent(input);
                break;
            default:
                throw new LukeException("Error processing command in addTask: '" + input + "'");
        }

        return task;
    }

    public static Task setTaskIsDone(int taskNumber, boolean isDone) throws LukeException {
        Task task = allTasks.get(taskNumber);
        if (task.isDone == isDone) {
            String status = isDone ? "marked as done" : "marked as unfinished";
            throw new LukeException("Task: '" + task + "' is already " + status);
        }
        task.isDone = isDone;
        return task;
    }

    @Override
    public String toString() {
        String doneIndicator = isDone ? "x" : " ";
        return "[" + doneIndicator + "] " + name;
    }
}
