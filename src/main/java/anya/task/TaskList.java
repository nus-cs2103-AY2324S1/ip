package anya.task;

import java.util.ArrayList;

public class TaskList {
    private ArrayList<Task> tasks;

    public TaskList() {
        this.tasks = new ArrayList<>();
    }

    public TaskList(ArrayList<Task> tasks) {
        this.tasks = tasks;
    }

    public void add(Task task) {
        this.tasks.add(task);
    }

    public void remove(Task task) {
        this.tasks.remove(task);
    }

    public Task get(int index) {
        return this.tasks.get(index);
    }

    public int size() {
        return this.tasks.size();
    }

    public void list() {
        StringBuilder list = new StringBuilder("Here are the tasks in your list:");
        for (int i = 0; i < tasks.size(); i++) {
            list.append(System.lineSeparator());
            list.append(String.format((i + 1) + ". " + tasks.get(i)));
        }
        System.out.println(list.toString());
    }

    /**
     * Finds and displays tasks containing a specified keyword in their descriptions.
     *
     * @param input The keyword to search for in task descriptions.
     */
    public void find(String input) {
        TaskList matchingTasks = new TaskList();
        for (Task t : tasks) {
            if (t.getDescription().contains(input)) {
                matchingTasks.add(t);
            }
        }
        matchingTasks.list();
    }
}
