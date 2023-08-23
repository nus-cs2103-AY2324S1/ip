import java.util.ArrayList;
import java.util.List;

public class Task extends Duke {
    private static List<Task> taskList = new ArrayList<>();

    private String description;
    private boolean isMarked;

    public Task(String description) {
        this.description = description;
        this.isMarked = false;
    }

    public static void addTask(Task task) {
        taskList.add(task);
        String message = String.format("Got it. I've added this task:\n  "
                + task
                + "\nNow you have %s tasks in the list", taskList.size());
        System.out.println(message);
    }

    public static List<Task> getAllTasks() {
        return taskList;
    }
    public static void listAllTasks() {
        if (taskList.isEmpty()) {
            System.out.println("You have no tasks.");
        } else {
            System.out.println("Here are your tasks:");
            for (int i = 0; i < taskList.size(); i++) {
                Task task = taskList.get(i);
                System.out.println((i + 1) + ". " + task);
            }
        }
    }

    public static void mark(int i) {
        Task taskToMark = taskList.get(i - 1);
        if (taskToMark.isMarked) {
            System.out.println("Already marked!");
        } else {
            taskToMark.isMarked = true;
            System.out.println("Nice! I've marked this task as done:\n  " + taskToMark);
        }
    }
    public static void unMark(int i) {
        Task taskToMark = taskList.get(i - 1);
        if (taskToMark.isMarked) {
            taskToMark.isMarked = false;
            System.out.println("I've unmarked this task:\n  " + taskToMark);
        } else {
            System.out.println("Already unmarked");
        }
    }

    public String getStatusIcon() {
        return (isMarked ? "[X]" : "[ ]");
    }

    @Override
    public String toString() {
        return getStatusIcon() + " " + description;
    }
}
