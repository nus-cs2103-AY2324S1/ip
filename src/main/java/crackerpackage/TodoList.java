package crackerpackage;


import java.util.ArrayList;

import crackerpackage.tasks.Task;


/**
 * A list that temporary stores tasks for the chatbot.
 *
 * @author Anton Tan Hong Zhi
 */
public class TodoList {



    private ArrayList<Task> list;

    /**
     * Creates a TodoList.
     */
    TodoList() {
        this.list = new ArrayList<>();
    }

    /**
     * Temporary stores the task into the TodoList.
     *
     * @param s the task to be stored
     */
    public void store(Task s) {
        list.add(s);
    }

    /**
     * Marks the task as done.
     *
     * @param index the index of the task in the list
     */
    public void markDone(int index) {
        list.get(index).markDone();
    }

    /**
     * Marks the task as undone.
     *
     * @param index the index of the task in the list
     */
    public void markUndone(int index) {
        list.get(index).markUndone();
    }

    /**
     * Removes the task from the list.
     *
     * @param index the index of the task in the list
     */
    public void deleteTask(int index) {
        list.remove(index);
    }

    /**
     * Returns the string representation of the task.
     * @param index the index of the task in the list
     * @return a string representing the task.
     */
    public String getTaskString(int index) {
        return list.get(index).toString();
    }

    /**
     * Returns the amount of elements in the list.
     * @return the amount of elements in the list.
     */
    public int size() {
        return list.size();
    }

    /**
     * Returns a task object
     * @param i the index of the task to be recieved.
     * @return a task in the list.
     */
    public Task getTask(int i) {
        return list.get(i);
    }

    /**
     * Filters all the tasks that contain a keyword.
     * @param keyword the word that must be in the task description
     * @return a TodoList that contains all the tasks that contain the keyword
     */
    public TodoList filter(String keyword) {
        TodoList filteredList = new TodoList();

        for (int i = 0; i < list.size(); i++) {
            Task task = list.get(i);
            if (task.getDesc().contains(keyword)) {
                filteredList.store(task);
            }
        }
        return filteredList;
    }



}
