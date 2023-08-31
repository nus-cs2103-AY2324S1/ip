package duke.task;

import java.util.ArrayList;

public class TaskList {
    ArrayList<Task> tasks;
    public TaskList() {
        this.tasks = new ArrayList<>();
    }
    public TaskList(ArrayList<Task> tasks) {
        this.tasks = tasks;
    }

    /**
     * Add task to array of tasks.
     * @param task to be added.
     */
    public void add(Task task) {
        tasks.add(task);
    }

    public void remove(int taskNumber) {
        tasks.remove(taskNumber - 1);
    }

    /**
     * Return number of tasks in current task list.
     * @return number of tasks.
     */
    public int getNumberOfTasks() {
        return tasks.size();
    }

    public Task getTask(int taskNumber) {
        return tasks.get(taskNumber - 1);
    }

    public ArrayList<Task> getList() {
        return this.tasks;
    }
}
