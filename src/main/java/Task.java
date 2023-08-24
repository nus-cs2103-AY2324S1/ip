import java.util.ArrayList;
import java.util.regex.Matcher;
import java.util.regex.Pattern;


public class Task {
    private static final Pattern todoPattern = Pattern.compile("^todo (?<taskName>.+)$");
    private static final Pattern deadlinePattern = Pattern.compile("^deadline (?<taskName>.+) /by (?<finishByTime>.+)$");
    private static final Pattern eventPattern = Pattern.compile("^event (?<taskName>.+) /from (?<startTime>.+) /to (?<endTime>.+)$");
    private static ArrayList<Task> allTasks = new ArrayList<>();
    private boolean isDone;
    private String name;

    Task(String name) {
        this.name = name;
        this.isDone = false;

        allTasks.add(this);
    }

    public static ArrayList<Task> getAllTasks() {
        return allTasks;
    }

    public static Task addTask(String input) {
        Matcher matcher;
        Task task = null;
        if ((matcher = todoPattern.matcher(input)).find()) {
            task = new Todo(matcher);
        } else if ((matcher = deadlinePattern.matcher(input)).find()) {
            task = new Deadline(matcher);
        } else if ((matcher = eventPattern.matcher(input)).find()) {
            task = new Event(matcher);
        }

        return task;
    }

    public static Task setTaskIsDone(int taskNumber, boolean isDone) {
        if (taskNumber < 1 || taskNumber > allTasks.size()) {
            return null;
        }

        Task task = allTasks.get(taskNumber - 1);
        task.isDone = isDone;
        return task;
    }

    public void mark() {
        this.isDone = true;
    }

    public void unmark() {
        this.isDone = false;
    }

    @Override
    public String toString() {
        String doneIndicator = isDone ? "x" : " ";
        return "[" + doneIndicator + "] " + name;
    }
}
