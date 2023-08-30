import java.util.ArrayList;

public class TaskList {
    private ArrayList<Task> tasks;

    public TaskList() {
        tasks = new ArrayList<>();
    }

    public void addTask(Task task) {
        tasks.add(task);
    }

    public void deleteTask(int index) {
        if (index >= 0 && index < tasks.size()) {
            tasks.remove(index);
        } else {
            System.out.println("Invalid task index.");
        }
    }

    public void listTasks() {
        int len = tasks.size();
        for (int i = 0; i < len; i++) {
            Task curr = tasks.get(i);
            System.out.println((i + 1) + "." + curr.toString());
        }
    }

    // Add more methods as needed

    // Getter for tasks
    public ArrayList<Task> getTasks() {
        return tasks;
    }
    // Method to set initial tasks
    public void setTasks(ArrayList<Task> initialTasks) {
        tasks = initialTasks;
    }


}
