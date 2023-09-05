package duke;

import duke.Exception.DukeException;
import duke.task.Task;

import java.util.ArrayList;

/**
 * Data Structure that stores the chatbot's tasks.
 */
public class TaskList {
    private ArrayList<Task> tasks;

    /**
     * Constructor for TaskList.
     */
    public TaskList() {
        this.tasks = new ArrayList<>();
    }

    /**
     * Method that returns the size of the tasklist.
     * @return the integer size of the tasklist.
     */
    public int getSize() {
        return this.tasks.size();
    }

    /**
     * Method that gets the task.
     * @param index the task's index in the TaskList
     * @return the Task
     */
    public Task getTask(int index) {
        return this.tasks.get(index);
    }

    /**
     * Method that adds the task to the TaskList.
     * @param task the Task that will be added
     */

    public void addTask(Task task) {
        this.tasks.add(task);
    }

    /**
     * Method that deletes the task from the TaskList.
     * @param index the task's index in the TaskList
     */
    public void deleteTask(int index) {
        this.tasks.remove(index);
    }

    /**
     * Method that marks a particular task as done.
     * @param index the index of the task that is to be marked.
     */
    public void markTaskAsDone(int index) {
        this.tasks.get(index).markAsDone();
    }

    /**
     * Method that marks a particular task as not done.
     * @param index the index of the task that is to be unmarked.
     */

    public void markTaskAsNotDone(int index) {
        this.tasks.get(index).markAsNotDone();
    }

    /**
     * Method that prints the TaskList.
     */
    public void printTasks() {
        try {
            System.out.println("\tHere are the tasks in your list:");
            if (tasks.size() == 0) {
                throw new DukeException("\t Seems like you have no tasks at the moment :) ");

            }
            for (int i = 1; i <= tasks.size(); i++) {
                System.out.println("\t" + i + ". " + tasks.get(i - 1).toString());
            }
        }
        catch (DukeException e) {
            e.printMessage();
        }
    }









}
