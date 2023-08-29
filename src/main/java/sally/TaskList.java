package sally;

import java.util.ArrayList;

// contains the task list e.g.,
// it has operations to add/delete tasks in the list
public class TaskList {
    private ArrayList<Task> tasks;

    public TaskList(ArrayList<Task> tasks) {
        this.tasks = tasks;
    }

    public TaskList() {
        this.tasks = new ArrayList<>();
    }

    public void addTask(Task task) {
        tasks.add(task);
    }

    public void deleteTask(int index) {
        tasks.remove(index);
    }

    public Task getTask(int index) {
        return tasks.get(index);
    }

    public ArrayList<Task> getTaskList() {
        return tasks;
    }

    /**
     * Searches for tasks containing the specified keyword in their descriptions.
     *
     * @param keyword The keyword to search for in task descriptions.
     * @return A new TaskList containing tasks with descriptions matching the keyword.
     */
    public TaskList findTasks(String keyword) {
        ArrayList<Task> matchingTasks = new ArrayList<>();
        for (Task task : tasks) {
            if (task.toString().contains(keyword)) {
                matchingTasks.add(task);
            }
        }
        return new TaskList(matchingTasks);
    }

    public int getSize() {
        return tasks.size();
    }
}
