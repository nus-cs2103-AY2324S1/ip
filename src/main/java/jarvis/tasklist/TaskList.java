package jarvis.tasklist;

import jarvis.task.Task;

import java.util.ArrayList;

public class TaskList {

    private ArrayList<Task> tasks;

    public TaskList() {
        this.tasks = new ArrayList<>();
    }

    public TaskList(ArrayList<Task> loadedTasks) {
        this.tasks = loadedTasks;
    }

    // Adds a task to the list.
    public void add(Task task) {
        tasks.add(task);
    }

    // Removes a task from the list based on the index.
    public Task remove(int index){
        return tasks.remove(index);
    }

    // Gets a task based on its index.
    public Task get(int index) {
        return tasks.get(index);
    }

    // Returns the number of tasks in the list.
    public int size() {
        return tasks.size();
    }

    // Returns the entire list of tasks.
    public ArrayList<Task> getTasks() {
        return tasks;
    }

    /**
     * Returns a list of tasks that contain the specified keyword in their description.
     *
     * @param keyword The keyword to search for.
     * @return A list of tasks that match the keyword.
     */
    public ArrayList<Task> find(String keyword) {
        ArrayList<Task> matchingTasks = new ArrayList<>();
        for (Task task : tasks) {
            if (task.getDescription().contains(keyword)) {
                matchingTasks.add(task);
            }
        }
        return matchingTasks;
    }
}