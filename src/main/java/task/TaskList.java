package task;

import java.util.ArrayList;

public class TaskList {
    private ArrayList<Task> tasks;
    public TaskList() {
        tasks = new ArrayList<>();
    }

    public void addTask(Task task) {
        tasks.add(task);
        //System.out.println("added: " + task);
    }

    public int getNextIndex() {
        return tasks.size() + 1;
    }

    public Task getTask(int taskIndex) {
        return tasks.get(taskIndex - 1);
    }



    @Override
    public String toString() {
        String output = "";
        for (int i = 0; i < tasks.size(); i++) {
            output = output + tasks.get(i).toString() + "\n";
        }
        return output;
    }
}
