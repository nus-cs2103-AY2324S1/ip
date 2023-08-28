package james;

import java.util.ArrayList;
public class TaskList {
    private ArrayList<Task> items = new ArrayList<Task>();
    public Parser parser = new Parser();

    public TaskList(ArrayList<Task> items) {
        this.items = items;
    }

    public TaskList() {
        this.items = new ArrayList<Task>();
    }

    public void unmarkTask(Integer idx) {
        Task task = this.items.get(idx);
        task.unmark();
    }

    public void markTask(Integer idx) {
        Task task = this.items.get(idx);
        task.mark();
    }

    public void deleteTask(Integer idx) {
        Task task = this.items.get(idx);
        this.items.remove(task);
    }

    public void addTask(Task task) {
        this.items.add(task);
    }

    public int size() {
        return this.items.size();
    }

    public ArrayList<Task> getTasks() {
        return this.items;
    }

    public Task getTask(int index) {
        return this.items.get(index);
    }

    @Override
    public String toString() {
        String output = "";
        for (int i = 0; i < this.items.size(); i++) {
            Task task = this.items.get(i);
            output += (i + 1) + "." + task.toString() + "\n";
        }
        // remove the last \n
        if (output.length() > 0) {
            output = output.substring(0, output.length() - 1);
        }
        return output;
    }
}
