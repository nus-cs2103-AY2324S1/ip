package taskList;

import task.Task;

import java.util.ArrayList;

/**
 * contains the task list e.g., it has operations to add/delete tasks in the list
 */
public class TaskList {
    private ArrayList<Task> list = new ArrayList<>();


    public TaskList() {

    }

    public void addTask(Task task) {
        list.add(task);
    }

    public ArrayList<Task> getList() {
        return this.list;
    }

    public void printString() {
        System.out.println(list);
    }
    public void deleteTask(int index) {

    }


}
