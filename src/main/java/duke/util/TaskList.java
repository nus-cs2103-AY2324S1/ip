package duke.util;

import duke.task.Task;

import java.util.ArrayList;

public class TaskList {
    private ArrayList<Task> list;

    public TaskList() {
        this.list = new ArrayList<>();
    }
    public TaskList(ArrayList<Task> list) {
        this.list = list;
    }

    @Override
    public String toString() {
        String output = "";
        for (int i = 0; i < list.size(); i++) {
            output += (i + 1) + "."+ list.get(i).toString() + "\n";
        }
        return output;
    }

    public void add(Task task) {
        this.list.add(task);
    }

    public Task remove(int index) {
        if (index < 0 || index >= list.size()) {
            return null;
        }
        return this.list.remove(index);
    }

    public Task mark(int index) {
        if (index < 0 || index >= list.size()) {
            return null;
        }
        this.list.get(index).markAsDone();
        return this.list.get(index);
    }

    public Task unmark(int index) {
        if (index < 0 || index >= list.size()) {
            return null;
        }
        this.list.get(index).markAsNotDone();
        return this.list.get(index);
    }

    public int size() {
        return this.list.size();
    }

    public TaskList find(String string) {
        ArrayList<Task> filteredList = new ArrayList<>();
        for (int i = 0; i < list.size(); i++) {
            Task task = list.get(i);
            if (task.toString().contains(string)) {
                filteredList.add(task);
            }
        }
        return new TaskList(filteredList);
    }
}
