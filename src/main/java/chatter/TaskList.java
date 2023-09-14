package chatter;

import java.util.ArrayList;

import chatter.task.Task;

/**
 * Represents a list of tasks.
 *
 * @author Anthony Tamzil
 * @version CS2103T Individual Project AY2023/24 Semester 1
 */
public class TaskList {
    private ArrayList<Task> list;
    private int numOfTasks = 0;

    /**
     * A constructor to initialize the chatter.TaskList class.
     *
     * @param list ArrayList of chatter.task.Task objects from reading local data file.
     */
    public TaskList(ArrayList<Task> list) {
        this.list = list;
        this.numOfTasks = list.size();
    }

    /**
     * A constructor to initialize the chatter.TaskList class.
     */
    public TaskList() {
        this.list = new ArrayList<>();
    }

    /**
     * Adds a chatter.task.Task object to the list of Tasks.
     *
     * @param task The chatter.task.Task object to be added.
     */
    public void addTask(Task task) {
        list.add(task);
        numOfTasks++;
    }

    /**
     * Marks specific chatter.task.Task in list as completed.
     *
     * @param taskNumber Index number of task in list to be mark as completed.
     */
    public void markTaskAsDone(int taskNumber) {
        Task completedTask = list.get(taskNumber - 1);
        completedTask.markAsDone();
    }

    /**
     * Deletes specific chatter.task.Task in list.
     *
     * @param taskNumber Number of task in list to be deleted.
     */
    public void delete(int taskNumber) {
        numOfTasks--;
        list.remove(taskNumber - 1);
    }

    /**
     * Marks specific chatter.task.Task in list as uncompleted.
     *
     * @param taskNumber Number of task in list to be mark as uncompleted.
     */
    public void markTaskAsNotDone(int taskNumber) {
        Task unmarkedTask = list.get(taskNumber - 1);
        unmarkedTask.markAsNotDone();
    }

    /**
     * Returns number of tasks in the list
     *
     * @return The number of tasks in the list.
     */
    public int getNumOfTasks() {
        return this.numOfTasks;
    }

    /**
     * Returns a string of the list of chatter.task.Task objects in storage string format.
     *
     * @return String of list of chatter.task.Task objects.
     */
    public String toStorageString() {
        StringBuilder storageString = new StringBuilder();
        list.forEach((Task::toStorageString));

        for (Task listOfStorageString : list) {
            storageString.append(listOfStorageString.toStorageString()).append("\n");
        }

        return storageString.toString();
    }

    /**
     * Returns a specific chatter.task.Task object at the specified index.
     *
     * @return chatter.task.Task object at the specified index.
     */
    public Task getTask(int index) {
        return list.get(index);
    }
}
