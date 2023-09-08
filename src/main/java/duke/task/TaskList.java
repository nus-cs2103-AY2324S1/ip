package duke.task;

import java.util.ArrayList;

public class TaskList {
    private ArrayList<Task> taskList;

    public TaskList() {
        taskList = new ArrayList<>();
    }

    public ArrayList<Task> getTaskList() {
        return this.taskList;
    }

    public Task getTask(int index) {
        return taskList.get(index);
    }

    public void printTaskList() {
        int index = 0;
        for (Task task: this.taskList) {
            System.out.println("    " + (++index) + "." + task.toString());
        }
    }

    public int size() {
        return this.taskList.size();
    }

    public void addTask(Task task) {
        taskList.add(task);
    }

    public Task deleteTask(int index) {
        return taskList.remove(index);
    }

    /**
     * Returns the list of tasks that contain the string given.
     *
     * @param str String that may be in the task description.
     * @return the list of tasks that contain the string given.
     */
    public ArrayList<Task> contains(String str) {
        ArrayList<Task> resultTask = new ArrayList<>();
        for (Task task: taskList) {
            if (task.contains(str)) {
                resultTask.add(task);
            }
        }
        return resultTask;
    }
}
