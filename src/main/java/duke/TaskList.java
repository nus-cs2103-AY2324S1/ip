package duke;

import java.util.ArrayList;

/**
 * TaskList class
 */
public class TaskList {
    ArrayList<Task> tasklist;

    /**
     * Constructor for tasklist
     * @param tasklist an array list of tasks
     */
    public TaskList(ArrayList<Task> tasklist) {
        this.tasklist = tasklist;
    }

    /**
     * Constructor for task list
     */
    public TaskList() {
        this.tasklist = new ArrayList<Task>();
    }
    /**
     * get the task according to index
     */
    public Task getTask(int index) {
       return this.tasklist.get(index);
    }

    /**
     * add new task
     * @param newtask a new Task
     */
    public void addTask (Task newtask) {
        this.tasklist.add(newtask);
    }

    /**
     * delete a task according to index
     * @param index index of the task, 0-indexed
     */
    public void deleteTask(int index) {
        this.tasklist.remove(index);
    }

    /**
     * mark the task as done
     * @param index index of the task
     */
    public void markTask(int index) {
        this.tasklist.get(index).markAsDone();
    }

    /**
     * mark the task as not done
     * @param index the index of the task
     */
    public void unmarkTask(int index) {
        this.tasklist.get(index).markAsNotDone();
    }

    /**
     * getSize of the task list
     * @return the getSize
     */
    public int getSize() {
        return this.tasklist.size();
    }
}
