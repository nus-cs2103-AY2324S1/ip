package duke;

import java.util.ArrayList;
public class TaskList {
    ArrayList<Task> taskList;

    TaskList(ArrayList<Task> taskList) {
        this.taskList = taskList;
    }

    TaskList() {
        this.taskList = new ArrayList<>();
    }

    public ArrayList<Task> getTaskList() {
        return this.taskList;
    }

    public String taskString(int a) {
        return this.taskList.get(a).toString();
    }

    public void add(Task task) {
        this.taskList.add(task);
    }

    public Task get(int a) {
        return this.taskList.get(a);
    }

    public void remove(int a) {
        this.taskList.remove(a);
    }

    public int size() {
        return this.taskList.size();
    }

    public void mark(int index) {
        this.taskList.get(index).mark();
    }

    public void unmark(int index) {
        this.taskList.get(index).unmark();
    }


    public int getSize() {
        return this.taskList.size();
    }

    public void taskIterator() {
        for (int i = 0; i < taskList.size(); i++) {
            Ui.staticTabPrinter(String.format("%d. %s", i + 1,
                    taskList.get(i).toString()));
        }
    }
}
