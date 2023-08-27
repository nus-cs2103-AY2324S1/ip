package alain;

import java.util.ArrayList;

public class TaskList {
    private ArrayList<Task> list;

    public TaskList() {
        this.list = new ArrayList<>();
    }

    public TaskList(ArrayList<Task> existingList) {
        this.list = existingList;
    }

    public void addTask(Task task) {
        list.add(task);
    }

    public Task removeTask(int index) throws AlainException {
        if (index >= 0 && index < list.size()) {
            return list.remove(index);
        } else {
            throw new AlainException(" ☹ OOPS!!! Seems like alain.Task with such index does not exist");
        }
    }

    public Task getTask(int index) {
        if (index >= 0 && index < list.size()) {
            return list.get(index);
        }
        return null;
    }

    public ArrayList<Task> getTasks() {
        return list;
    }

    public int size() {
        return list.size();
    }
}