package duke.main;

import java.util.ArrayList;

import duke.task.Task;

public class TaskList {
    private ArrayList<Task> taskList;

    TaskList() {
        this.taskList = new ArrayList<>();
    }

    TaskList(ArrayList<Task> taskList) {
        this.taskList = taskList;
    }

    public ArrayList<Task> getAllTasks() {
        return taskList;
    }

    public Task get(int index) {
        return taskList.get(index);
    }

    public void add(Task task) {
        taskList.add(task);
    }
    public void delete(int index) { taskList.remove(index); }

    public int size() {
        return taskList.size();
    }

    public void markDone(int index) {
        taskList.get(index).markDone();
    }
    public void markNotDone(int index) {
        taskList.get(index).markNotDone();
    }

    /**
     * Returns list of task containing a keyword
     *
     * @param keyword Keyword to find in the tasks.
     */
    public ArrayList<Task> find(String keyword) {
        ArrayList<Task> filteredTasks = new ArrayList<>();
        for (Task t : taskList) {
            if (t.getDescription().contains(keyword)) {
                filteredTasks.add(t);
            }
        }
        return filteredTasks;
    }

}
