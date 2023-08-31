package duke;

import task.Task;

import java.util.ArrayList;
import java.util.List;

public class TaskList {
    private List<Task> todoList;

    public TaskList() {
        this.todoList = new ArrayList<Task>();
    }


    public List<Task> getList() {
        return this.todoList;
    }

    public int getLength() {
        return this.todoList.size();
    }

    public String getTaskInString(int index) {
        return this.todoList.get(index).toString();
    }


    public void addTask(Task task) {
        this.todoList.add(task);
        System.out.println("Roger! I have added the following task to the list");
        System.out.println(task.toString());
    }

    public void deleteTask(int taskToDeleteIndex) {
        String taskToRemove = todoList.get(taskToDeleteIndex).toString();
        this.todoList.remove(taskToDeleteIndex);
        System.out.println("Okay! I have removed the following task from the list");
        System.out.println(taskToRemove);
    }

    public void markTask(int taskToMarkIndex) {
        this.todoList.get(taskToMarkIndex).markDone();
    }

    public void unmarkTask(int taskToUnmarkIndex) {
        this.todoList.get(taskToUnmarkIndex).markUndone();
    }

    public void printTaskListInString() {
        System.out.println(String.format("You have %d task(s) currently in the list", todoList.size()));
    }

}
