package Ally.Tasks;

import java.util.ArrayList;

import Ally.Exceptions.AllyException;

public class AllyList {
    ArrayList<Task> arr;
    public AllyList(ArrayList<Task> tasks) throws AllyException {
        arr = new ArrayList<>(100);
    }

    /**
     * Function to add tasks from the input into the arraylist.
     * @param task
     */
    public void addElements(Task task) throws AllyException {
        arr.add(task);
    }

    /**
     * Function to mark the task as done.
     * @param index
     */
    public void markAsDone(int index) {
        Task task = arr.get(index);
        task.setMarked();
        System.out.println("Nice! I've marked this task as done:");
        System.out.println("\t" + task.toString());
    }

    /**
     * Function to unmark the task to undone.
     * @param index
     */
    public void unMarkDone(int index) {
        Task task = arr.get(index);
        task.notDone();
        System.out.println("OK, I've marked this task as not done yet:");
        System.out.println("\t" + task.toString());
    }

    public int getSize() {
        return arr.size();
    }

    public Task getTask(int index) {
        return arr.get(index);
    }

    /**
     * Prints the task added to the list.
     * @param task
     */
    public void printNewList(Task task) {
        System.out.println("Got it. I've added this task:\n");
        System.out.println("\t" + task.toString());
        System.out.println("Now you have " + arr.size() + " tasks in the list.");

    }


    /**
     * Function to delete a task from the list or array.
     * @param index
     * @throws AllyException
     */
    public void deleteElement(int index) throws AllyException {
        arr.remove(index);
    }

}
