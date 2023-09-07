import java.util.ArrayList;

public class TaskList {
    protected ArrayList<Task> tasks;

    public TaskList() {
        this.tasks = new ArrayList<>();
    }

    public void addTask(Task task) {
        tasks.add(task);
        System.out.println("Got it. I've added this task:");
        System.out.println("  " + task);
        System.out.println("Now you have " + tasks.size() + " tasks in the list.");
    }

    public boolean isEmpty() {
        return tasks.isEmpty();
    }

    public void deleteTask(int taskNumber) {
        Task task = tasks.get(taskNumber);
        tasks.remove(taskNumber);
        System.out.println("Noted. I've removed this task: \n" + "  " + task);
        System.out.println("Now you have " + tasks.size() + " tasks in the list.");
    }

    public void set(int index, Task task) {
        tasks.set(index, task);
    }

    public int size() {
        return tasks.size();
    }

    public Task get(int index) {
        return tasks.get(index);
    }

    public ArrayList<Task> toArrayList() {
        return tasks;
    }

    public void markTaskAsDone(int taskNumber) {
        Task task = tasks.get(taskNumber);
        task.setMarked(true);
        tasks.set(taskNumber, task);
        System.out.println("Nice! I've marked this task as done:\n" + "  " + tasks.get(taskNumber));
    }

    public void unmarkTask(int taskNumber) {
        Task task = tasks.get(taskNumber);
        task.setMarked(false);
        tasks.set(taskNumber, task);
        System.out.println("OK, I've marked this task as not done yet:\n" + "  " + tasks.get(taskNumber));
    }

    public void listOfTasks() {
        System.out.println("Here are the tasks in your list: ");
        if (!tasks.isEmpty()) {
            for (int i = 1; i <= tasks.size(); i++) {
                System.out.println(i + "." + tasks.get(i - 1));
            }
        }
    }

    // Add other methods for task list operations



    public String toString() {
        return tasks.toString();
    }
}


