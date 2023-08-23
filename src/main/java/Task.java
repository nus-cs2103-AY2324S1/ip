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
                + "\nNow you have %s tasks in the list\n", taskList.size());
        System.out.println(message);
    }
    public static void listAllTasks() {
        if (taskList.isEmpty()) {
            System.out.println("You have no tasks.\n");
        } else {
            System.out.println("Here are your tasks:");
            for (int i = 0; i < taskList.size(); i++) {
                Task task = taskList.get(i);
                System.out.println((i + 1) + ". " + task);
            }
        }
    }

    public static void mark(int i) throws TaskException {
        if (i > taskList.size()) {
            throw new TaskException("   TaskException: Invalid task index. Greater than taskList size\n");
        }
        Task taskToMark = taskList.get(i - 1);
        if (taskToMark.isMarked) {
            System.out.println("Already marked!\n");
        } else {
            taskToMark.isMarked = true;
            System.out.println("Nice! I've marked this task as done:\n  " + taskToMark + "\n");
        }
    }
    public static void unMark(int i) throws TaskException{
        if (i > taskList.size()) {
            throw new TaskException("   TaskException: Invalid task index. Greater than taskList size\n");
        }
        Task taskToMark = taskList.get(i - 1);
        if (taskToMark.isMarked) {
            taskToMark.isMarked = false;
            System.out.println("I've unmarked this task:\n  " + taskToMark + "\n");
        } else {
            System.out.println("Task already unmarked\n");
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
