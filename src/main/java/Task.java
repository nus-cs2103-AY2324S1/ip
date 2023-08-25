import java.util.ArrayList;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Task {
    protected boolean isComplete;
    protected String description;
    protected static int numOfTasks = 0;


    public static ArrayList<Task> taskList = new ArrayList<>();

    public Task(String description){
        this.description = description;
        this.isComplete = false;
        Task.numOfTasks++;

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

    public static void addTask(String cmd) throws InvalidCommand, EmptyTaskException {
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
        taskList.add(newTask);
        // print statement
        Haste.printLine();
        System.out.println(Haste.INDENT + "Got it. I've added this task:");
        System.out.println(Haste.INDENT + newTask);
        System.out.println(Haste.INDENT + "Now you have " + Task.numOfTasks + " tasks in the list.");
        Haste.printLine();
    }

    public static boolean checkEmpty(String s) {
        return s == null || s.isBlank();
    }

    public static void printList() {
        Haste.printLine();
        for (Task a : taskList) {
            System.out.println(Haste.INDENT + (taskList.indexOf(a) + 1) + ". " + a);
        }
        Haste.printLine();
    }

    public static void deleteTask(int id) {
        taskList.remove(id);
        numOfTasks--;
    }





}
