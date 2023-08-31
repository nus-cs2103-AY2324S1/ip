import java.util.ArrayList;

public class TaskList {
    private static ArrayList<Task> tasks = new ArrayList<>();
    private static int count = 0;

    public Task getTask(int index) {
        return tasks.get(index);
    }

    public int getCount() {
        return count;
    }

    public void addTask(Task t, boolean shouldPrint) {
        tasks.add(t);
        count++;

        if (shouldPrint) {
            System.out.println("Got it. I've added this task:");
            System.out.println(t);
            System.out.println("Now you have " + count + " task(s) in the list.");
        }
    }

    public void deleteTask(int index) {
        Task t = getTask(index);
        tasks.remove(t);
        count--;

        System.out.println("Noted. I've removed this task:");
        System.out.println(t);
        System.out.println("Now you have " + count + " task(s) in the list.");

    }

    public void markTask(int index, boolean isDone) {
        Task t = getTask(index);
        if (isDone) {
            t.markAsDone();
        } else {
            t.markAsUndone();
        }
    }

    public void printTasks() {
        System.out.println("List of items:");
        for (int i = 0; i < count; i++) {
            int index = i + 1;
            System.out.println(index + "." + tasks.get(i));
        }
    }
}
