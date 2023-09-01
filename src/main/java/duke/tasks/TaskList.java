package duke.tasks;

import duke.tasks.Task;

import java.util.ArrayList;

public class TaskList {
    private ArrayList<Task> tasks;

    public TaskList() {
        this.tasks = new ArrayList<>();
    }

    public String getTaskAsString(int index) {
        return tasks.get(index).toString();
    }

    public void printTasksAsList() {
        int index = 1;
        for (Task task : tasks) {
            System.out.println(index + "." + task.toString());
            index++;
        }
    }

    public void addTask(Task newTask) {
        tasks.add(newTask);
    }

    public void removeTask(int index) {
        tasks.remove(index);
    }

    public Task getTask(int index) {
        return tasks.get(index);
    }

    public int getTaskCount() {
        return tasks.size();
    }

    public void markTaskAsDone(int index, boolean done) {
        getTask(index).markTaskCompleted(done);
    }

    public String getTaskListData() {
        String data = "";
        for (Task task : tasks) {
            data = data.concat(task.toData() + "\n");
        }
        return data;
    }
}
