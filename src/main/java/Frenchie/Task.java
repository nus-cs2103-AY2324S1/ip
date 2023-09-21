package Frenchie;

public class Task {
    public static boolean isCompleted;
    public String task_name;

    public Task() {
        isCompleted = false;
    }
    //Constructor
    public Task(String name) {
        this.task_name = name;
        isCompleted = false;
    }

    public void mark_as_completed() {
        isCompleted = true;
    }

    public void mark_as_incomplete() {
        isCompleted = false;
    }

    public static boolean check_Completion() {
        return isCompleted;
    }
    @Override
    public String toString() {
        String indicator = " ";
        if(isCompleted) {
            indicator = "X";
        }
        return "[" + indicator + "] " + task_name;
    }
}
