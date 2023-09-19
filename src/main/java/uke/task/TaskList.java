package uke.task;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Comparator;

/**
 * Represents the task list.
 */
public class TaskList {

    private ArrayList<Task> storedTasks;

    /**
     * Constructor to initialise an empty TaskList object.
     */
    public TaskList() {
        this.storedTasks = new ArrayList<>();
    }

    /**
     * Constructor to initialise a TaskList object with stored tasks.
     *
     * @param storedTasks Array of Tasks to be stored in the TaskList.
     */
    public TaskList(ArrayList<Task> storedTasks) {
        this.storedTasks = storedTasks;
    }

    /**
     * Returns the task at the given index in the TaskList.
     *
     * @param i Index of the task.
     */
    public Task getTask(int i) {
        return this.storedTasks.get(i);
    }

    /**
     * Returns the length of the TaskList.
     */
    public int getLength() {
        return this.storedTasks.size();
    }

    /**
     * Adds a task into the TaskList.
     *
     * @param t Task to be added.
     */
    public void addTask(Task t) {
        storedTasks.add(t);
    }

    /**
     * Deletes a task from the TaskList.
     *
     * @param index Index of the task to be deleted.
     */
    public void deleteTask(int index) {
        storedTasks.remove(index);
    }

    /**
     * Filters the TaskList for tasks containing the given keyword.
     *
     * @param keyword Keyword to be searched and filtered for.
     * @return A TaskList of all tasks containing the keyword.
     */
    public TaskList getTaskListWithKeyword(String keyword) {
        TaskList list = new TaskList();
        int len = this.getLength();

        for (int i = 0; i < len; i++) {
            Task t = this.getTask(i);
            String desc = t.getDescription();
            int index = desc.indexOf(keyword);

            if (index != -1) {
                list.addTask(t);
            }
        }

        return list;
    }

    /**
     * Filters the TaskList for tasks starting on or due by the given date.
     *
     * @param date Date to be searched for.
     * @return A TaskList of all tasks starting on or due by the given date.
     */
    public TaskList getTaskListWithDate(LocalDate date) {
        TaskList list = new TaskList();
        int len = this.getLength();

        for (int i = 0; i < len; i++) {
            Task t = this.getTask(i);
            LocalDateTime taskDateTime = t.getDateTime();

            LocalDate taskDate = taskDateTime.toLocalDate();

            if (date.equals(taskDate)) {
                list.addTask(t);
            }
        }

        return list;
    }

    /**
     * Sorts the TaskList from earliest to latest.
     */
    public void sortByTime() {

        int len = this.getLength();

        for (int i = 0; i < len; i++) {
            Task t = this.getTask(i);
            LocalDateTime dateTime = t.getDateTime();

            this.storedTasks.sort(new Comparator<Task>() {
                public int compare(Task t1, Task t2) { return t1.getDateTime().compareTo(t2.getDateTime()); }
            });
        }
    }
}
