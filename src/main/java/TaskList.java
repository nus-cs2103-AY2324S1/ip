import java.util.ArrayList;

public class TaskList {
    protected ArrayList<Task> tasks;

    public TaskList(ArrayList<Task> tasks) {
        this.tasks = tasks;
    }

    public TaskList() {
        this.tasks = new ArrayList<>();
    }

    public void addTask(Task task) {
        System.out.println("Got it. I've added this task:");
        System.out.println(task);
        tasks.add(task);
        System.out.println("Now you have " + getSize() + " task(s) in the list.");
    }

    public void removeTask(int index) {
        System.out.println("Noted. I've removed this task:");
        System.out.println(tasks.get(index).toString());
        tasks.remove(index);
        System.out.println("Now you have " + getSize() + " task(s) in the list.");
    }

    public void markTask(int taskIndex) {
        tasks.get(taskIndex).markAsDone();
        System.out.println("Nice! I've marked this task as done:");
        System.out.println(tasks.get(taskIndex).toString());
        System.out.println("Now you have " + getSize() + " task(s) in the list.");
    }

    public void unmarkTask(int taskIndex) {
        tasks.get(taskIndex).markAsUndone();
        System.out.println("OK, I've marked this task as not done yet:");
        System.out.println(tasks.get(taskIndex).toString());
        System.out.println("Now you have " + getSize() + " task(s) in the list.");
    }

    public int getSize() {
        return tasks.size();
    }

    @Override
    public String toString() {
        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append("Here are the tasks in your list:\n");
        for (int i = 0; i < tasks.size(); i++) {
            stringBuilder.append(i + 1).append(". ").append(tasks.get(i)).append("\n");
        }
        return stringBuilder.toString();
    }
}