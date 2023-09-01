package nobita.task;

import java.util.ArrayList;

public class TaskList {

    ArrayList<Task> tasks;
    public TaskList() {
        this.tasks = new ArrayList<>();
    }

    public int getTotalTask() {
        return this.tasks.size();
    }

    public void addTask(Task task) {
        this.tasks.add(task);
    }

    public Task deleteTask(int targetTask) {
        return this.tasks.remove(targetTask);
    }

    public Task markComplete(int targetTask) {
        Task task = this.tasks.get(targetTask);
        task.markComplete();
        return task;
    }

     public Task markIncomplete(int targetTask) {
        Task task = this.tasks.get(targetTask);
        task.markIncomplete();
        return task;
    }

    public ArrayList<Task> getAllTasks() {
        return this.tasks;
    }

    public void showAllTask() {
        int listNum = 1;
        for (Task task: tasks) {
            System.out.println(listNum + ". " + task);
            ++listNum;
        }
    }

    public boolean checkIndexWithinRange(int ind) {
        if (ind < 1 || ind > this.tasks.size()) {
            return false;
        }
        return true;
    }
}

