package task;

import java.util.ArrayList;

public class TaskList {
    private ArrayList<String> tasks;
    public TaskList() {
        tasks = new ArrayList<>();
    }

    public void addTask(String task) {
        tasks.add(task);
        //System.out.println("added: " + task);
    }

    @Override
    public String toString() {
        String output = "";
        for (int i = 1; i < tasks.size() + 1; i++) {
            output = output + String.format("%d", i) + ". " + tasks.get(i - 1) + "\n";
        }
        return output;
    }
}
