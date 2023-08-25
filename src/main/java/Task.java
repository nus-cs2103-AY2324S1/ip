import java.util.ArrayList;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Task {
    protected boolean isComplete;
    protected String description;
    protected static int numOfTasks = 0;
    protected int id;

    public static ArrayList<Task> taskList = new ArrayList<>();

    public Task(String description){
        this.description = description;
        this.isComplete = false;
        Task.numOfTasks++;
        this.id = numOfTasks;

    }

    public void markDone() {
        isComplete = true;
    }

    public void markUndone() {
        isComplete = false;
    }

    public String getStatusIcon() {
        return (isComplete ? "X" : " "); // mark done task with X
    }

    public String toString() {
        return "[" + getStatusIcon() + "] " + description;
    }

    public static Task getTask(String cmd) throws Exception {
        String type = cmd.split("\\s+")[0]; // grab the first word
        Task newTask;
        switch(type) {
            case "todo":
                newTask = ToDo.interpret(cmd);
                break;
            case "deadline":
                newTask = Deadline.interpret(cmd);
                break;
            case "event":
                newTask = Event.interpret(cmd);
                break;
            default:
                throw new InvalidCommand(cmd);

        }
        return newTask;
    }

    public static boolean checkEmpty(String s) {
        return s == null || s.isBlank();
    }





}
