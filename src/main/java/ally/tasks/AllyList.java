package ally.tasks;

import java.util.ArrayList;

import ally.exceptions.AllyException;

/**
 * AllyList class to store the tasks in an ArrayList.
 */
public class AllyList {
    private ArrayList<Task> arr;
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
        task.setMarkDone();
        System.out.println("Nice! I've marked this task as done:");
        System.out.println("\t" + task);
    }

    /**
     * Function to unmark the task to undone.
     * @param index
     */
    public void unMarkDone(int index) {
        Task task = arr.get(index);
        task.setMarkNotDone();
        System.out.println("OK, I've marked this task as not done yet:");
        System.out.println("\t" + task);
    }

    public int getSize() {
        return arr.size();
    }

    public Task getTask(int index) {
        return arr.get(index);
    }

    /**
     * Prints the task added to the list.
     *
     * @param task
     * @return
     */
    public String printNewList(Task task) {
        String str1 = "Got it. I've added this task:\n";
        String str2 = "\t" + task.toString();
        String str3 = "\n" + "Now you have " + arr.size() + " tasks in the list.";
        return str1 + str2 + str3;
    }


    /**
     * Function to delete a task from the list or array.
     *
     * @param index int
     * @throws AllyException Exception
     */
    public void deleteElement(int index) throws AllyException {
        arr.remove(index);
    }


}
