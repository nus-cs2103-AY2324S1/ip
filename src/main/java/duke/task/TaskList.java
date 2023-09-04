package duke.task;

import java.util.ArrayList;


public class TaskList {
    private ArrayList<Task> tasks;


    public TaskList() {
        tasks = new ArrayList<>();

    }

    public void addTask(Task task) {
        tasks.add(task);
    }

    public void addNoSaveTask(Task task) {
        tasks.add(task);
    }

    public int getNextIndex() {
        return tasks.size() + 1;
    }

    public Task getTask(int taskIndex) {
        return tasks.get(taskIndex - 1);
    }

    public void removeTask(int taskIndex) {
        tasks.remove(taskIndex - 1);
    }

    public int getLength() {
        return tasks.size();
    }

    public String saveFormat() {
        String output = "";
        for (int i = 0; i < tasks.size(); i++) {
            output = output + "\n" + tasks.get(i).saveFormat();
        }
        return output;
    }
    @Override
    public String toString() {
        String output = "";
        for (int i = 0; i < tasks.size(); i++) {
            output = output + String.format("%d", i + 1) + ". " + tasks.get(i).toString() + "\n";
        }
        return output;
    }
}
