package duke;

import java.util.ArrayList;

public class TaskList {
    public ArrayList<Task> userData;

    public TaskList(ArrayList<Task> userData) {
        this.userData = userData;
    }

    /**
     * Appends newly created Task to the current ArrayList<Task> of the user
     *
     * @param newTask Task Object. Could be Todo, Deadline or Event
     */
    public void addTask(Task newTask) {
        this.userData.add(newTask);
    }

    /**
     * Deletes a particular Task from the current ArrayList<Task> based on given index
     *
     * @param referenceIndex position of the Task within the ArrayList
     */
    public void deleteTask(int referenceIndex) {
        this.userData.remove(referenceIndex);
    }
}
