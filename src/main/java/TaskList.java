import java.util.ArrayList;
import java.util.Iterator;
import java.util.concurrent.ForkJoinTask;

/**
 * The TaskList class represents a collection of tasks managed by the ChatterChicken task manager.
 * It allows for adding, marking, unmarking, and deleting tasks, as well as printing the list of tasks
 * along with their respective indexes. Tasks can be of different types: ToDo, Deadline, and Event.
 */
public class TaskList implements Iterable<Task> {

    private ArrayList<Task> taskList;

    public TaskList(ArrayList<Task> taskList) {
        this.taskList = taskList;
    }

    /**
     * Adds a new task to the task list based on the provided Task object.
     * Displays a confirmation message with the added task's details.
     *
     * @param task The task to be added to the task list.
     * @throws CCException If there is an error in adding the task.
     */
    public void addTask(Task task) throws CCException {
        taskList.add(task);
        System.out.println(ChatterChicken.LINE
                + ChatterChicken.INDENT + "Got it. I've added this task:\n"
                + ChatterChicken.INDENT_BIG + task.getTaskForPrinting() + "\n"
                + ChatterChicken.INDENT + "Now you have " + taskList.size() + " tasks in the list."
                + ChatterChicken.LINE);
    }

    /**
     * Marks a task as done based on the provided input.
     * Displays a confirmation message with the marked task's details.
     *
     * @param input The input containing task information to mark as done.
     * @throws CCException If there is an error in marking the task or if the input is invalid.
     */
    public void markTask(String input) throws CCException {
        try {
            Task task = taskList.get(getIndex(input));
            task.setDone(true);
            System.out.println(ChatterChicken.LINE
                    + ChatterChicken.INDENT + "Nice! I've marked this task as done:\n"
                    + ChatterChicken.INDENT_BIG + task.getTaskForPrinting()
                    + ChatterChicken.LINE);
        } catch (IndexOutOfBoundsException e) {
            throw new CCException("Invalid input for marking list of length " + taskList.size());
        }
    }

    /**
     * Unmarks a previously marked task as not done based on the provided input.
     * Displays a confirmation message with the unmarked task's details.
     *
     * @param input The input containing task information to unmark.
     * @throws CCException If there is an error in unmarking the task or if the input is invalid.
     */
    public void unmarkTask(String input) throws CCException {
        try {
            Task task = taskList.get(getIndex(input));
            task.setDone(false);
            System.out.println(ChatterChicken.LINE
                    + ChatterChicken.INDENT + "OK, I've marked this task as not done yet:\n"
                    + ChatterChicken.INDENT_BIG + task.getTaskForPrinting()
                    + ChatterChicken.LINE);
        } catch (IndexOutOfBoundsException e) {
            throw new CCException("Invalid input for list of length " + taskList.size());
        }
    }

    /**
     * Deletes a task from the task list based on the provided input.
     * Displays a confirmation message after deleting the task.
     *
     * @param input The input containing task information to be deleted.
     * @throws CCException If there is an error in deleting the task or if the input is invalid.
     */
    public void deleteTask(String input) throws CCException {
        try {
            int index = getIndex(input);
            Task task = taskList.get(index);
            taskList.remove(index);
            System.out.println(ChatterChicken.LINE
                    + ChatterChicken.INDENT + "Noted. I've removed this task:\n"
                    + ChatterChicken.INDENT_BIG + task.getTaskForPrinting() + "\n"
                    + ChatterChicken.INDENT + "Now you have " + taskList.size() + " tasks in your list."
                    + ChatterChicken.LINE);
        } catch (IndexOutOfBoundsException e) {
            throw new CCException("Invalid input for list of length " + taskList.size());
        }
    }

    /**
     * Retrieves the index of a task based on the provided input.
     *
     * @param input The input containing task information and index as the last character.
     * @return The index of the task parsed from the input.
     */
    private int getIndex(String input) {
        return input.charAt(input.length() - 1) - '0' - 1;
    }

    /**
     * Prints the list of tasks with their respective indexes.
     */
    public void printList() {
        System.out.println(ChatterChicken.LINE + ChatterChicken.INDENT + "Here are the tasks in your list:");
        for (int i = 0; i < taskList.size(); i++) {
            System.out.println(ChatterChicken.INDENT_BIG + (i + 1) + "." + taskList.get(i).getTaskForPrinting());
        }
        System.out.println(ChatterChicken.LINE);
    }

    @Override
    public Iterator<Task> iterator() {
        return taskList.iterator();
    }
}