import java.util.ArrayList;

public class Tasklist {
    private ArrayList<Task> tasks;

    // Constructor for the Tasklist
    public Tasklist(ArrayList<Task> tasks) {
        this.tasks = tasks;
    }

    // Gets the ArrayList of tasks
    public ArrayList<Task> getTasks() {
        return tasks;
    }

    // Gets the size of the task list
    public int getSize() {
        return tasks.size();
    }

    // Gets the task at a particular index in the task list
    public Task getTask(int i) {
        return tasks.get(i);
    }

    // Gets the string representation of a particular item in the task list
    public String getItemString(int i) {
        return tasks.get(i).toString();
    }

    // Marks an item as done
    public void markDone(int i) {
        tasks.get(i).markDone();
    }

    // Marks an item as undone
    public void markUndone(int i) {
        tasks.get(i).markUndone();
    }

    // Delete an item in the task list
    public void deleteItem(int i) {
        tasks.remove(i);
    }

    // Adds a task to the task list
    public void addTask(Task task) {
        tasks.add(task);
    }
}
