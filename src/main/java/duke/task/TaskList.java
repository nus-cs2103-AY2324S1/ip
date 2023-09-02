package duke.task;

import java.util.ArrayList;

public class TaskList {
    public ArrayList<Task> list;

    public TaskList() {
        this.list = new ArrayList<Task>();
    }

    public TaskList(ArrayList<Task> list) {
        this.list = list;
    }

    public void addTask(Task task) {
        this.list.add(task);
    }

    public void deleteTask(int index) {
        this.list.remove(index);
    }

    public Task getTask(int index) {
        return this.list.get(index);
    }

    public int getSize() {
        return this.list.size();
    }

    public ArrayList<Task> getList() {
        return this.list;
    }

    public TaskList findTasks(String searchTerm) {
        ArrayList<Task> matchingTasks = new ArrayList<Task>();
        for (Task task : this.list) {
            if (task.description.contains(searchTerm)) {
                matchingTasks.add(task);
            }
        }
        return new TaskList(matchingTasks);
    }
}
