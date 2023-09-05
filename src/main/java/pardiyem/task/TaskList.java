package pardiyem.task;

import java.util.ArrayList;

public class TaskList {
    protected final ArrayList<Task> tasklist;

    public TaskList() {
        tasklist = new ArrayList<Task>();
    }

    public TaskList(ArrayList<Task> list) {
        tasklist = list;
    }

    /**
     * Getter method for the tasklist attribute
     *
     * @return the tasklist attribute
     */
    public ArrayList<Task> getList() {
        return tasklist;
    }

    /**
     * Getter method for members of the tasklist attribute
     *
     * @param i index of the item to be accessed from the tasklist attribute
     * @return the corresponding task
     */
    public Task getTask(int i) {
        return tasklist.get(i);
    }

    /**
     * Utility method to add a Task object to the tasklist
     *
     * @param newTask the Task object to be added
     */
    public void add(Task newTask) {
        tasklist.add(newTask);
    }

    /**
     * Utility method to get the size of the tasklist
     *
     * @return the size of the tasklist
     */
    public int size() {
        return tasklist.size();
    }

    /**
     * Utility method to delete a task in the tasklist
     *
     * @param ind the index of the Task object to be deleted
     * @return status message to indicate the completion of the operation
     * @throws ArrayIndexOutOfBoundsException if the given index is not within the bounds of the tasklist
     * @throws NumberFormatException if the given index string is not parseable to an integer
     */
    public String delete(String ind) {
        ind = ind.trim();
        try {
            int i = Integer.parseInt(ind) - 1;
            if (i < 0 || i >= tasklist.size()) {
                throw new ArrayIndexOutOfBoundsException("Whoops, that number is not an index in the list. Please select a valid index");
            }
            Task curr = tasklist.get(i);
            tasklist.remove(i);
            return String.format("Noted. I've removed this task:\n%s\nNow you have %d task(s) on the list", curr.toString(), tasklist.size());
        } catch (NumberFormatException e) {
            throw new NumberFormatException("Whoops, you need to type in a valid integer");
        }
    }

    @Override
    public String toString() {
        StringBuilder out = new StringBuilder();
        for (int i = 0; i < tasklist.size(); i++) {
            if (i != 0) {
                out.append("\n");
            }
            out.append(String.format("%d.%s", i + 1, tasklist.get(i).toString()));
        }
        return out.toString();
    }
}
