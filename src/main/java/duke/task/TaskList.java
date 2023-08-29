package duke.task;

import java.util.ArrayList;

public class TaskList {
    private final ArrayList<Task> list;

    public TaskList() {
        this.list = new ArrayList<>();
    }

    public TaskList(ArrayList<Task> list) {
        this.list = list;
    }

    public void addTask(Task task) {
        this.list.add(task);
    }

    public void deleteTask(int index) {
        this.list.remove(index);
    }

    public Task getTask(int index) {
        return this.list.get(index);
    }

    public void markTask(int index) {
        this.list.get(index).mark();
    }

    public void unmarkTask(int index) {
        this.list.get(index).unmark();
    }


    public int size() {
        return this.list.size();
    }

    public ArrayList<Task> getList() {
        return this.list;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < list.size(); i++) {

            sb.append(String.format("%d. %s \n", i + 1, list.get(i).toString()));
        }
        return sb.toString();
    }

}
