import java.util.ArrayList;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Task {
    protected boolean isComplete;
    protected String description;
    public static ArrayList<Task> taskList = new ArrayList<>();

    public Task(String description){
        this.description = description;
        this.isComplete = false;
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





}
