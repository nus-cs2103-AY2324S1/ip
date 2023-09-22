package duke;

import java.util.ArrayList;

/**
 * Represents the list of tasks created by user.
 */
public class TaskList {
    private ArrayList<TaskType> items;

    /**
     * Default constructor for TaskList class, creates an empty list of TaskTypes.
     */
    public TaskList() {
        items = new ArrayList<>();
    }

    /**
     * Secondary constructor for TaskList class, generates a list of TaskTypes
     * from saved data.
     *
     * @param ss ArrayList of strings loaded from external data storage
     * @param dtf Datetime formatter used to recognize dates in saved data
     * @throws DukeException when the string format cannot be recognized and converted to TaskType
     */

    public TaskList(ArrayList<String> ss, DtFormat dtf) throws DukeException {
        items = new ArrayList<>();
        for (String s : ss) {
            String[] d = s.split("&");
            String desc = d[0];
            assert desc.length() > 0;
            boolean isCompleted = Boolean.valueOf(d[1]);
            String taskType = d[2];
            String dateString = d[3].equals("null") ? null : d[3];
            int p = Integer.valueOf(d[4]);
            TaskType task;

            if (taskType.equals("T")) {
                task = new Todo(desc, isCompleted);
            } else if (taskType.equals("D")) {
                task = new Deadline(desc, isCompleted, dateString, dtf.getFormatters());
            } else if (taskType.equals("E")) {
                task = new Event(desc, isCompleted, dateString, dtf.getFormatters());
            } else {
                throw new DukeException("Task type not recognized");
            }
            task.setPriority(p);
            items.add(task);
        }
    }

    /**
     * Returns the size of the TaskList.
     *
     * @returns size of TaskList.
     */

    public int getSize() {
        return items.size();
    }

    /**
     * Returns an item in the TaskList by index
     *
     * @param x index of item
     * @returns item in the TaskList by index
     */

    public TaskType getItem(int x) {
        return items.get(x);
    }

    /**
     * Appends an item to the end of the TaskList
     *
     * @param y TaskType to be appended
     */

    public void addItem(TaskType y) {
        items.add(y);
    }

    /**
     * Removes an item from the TaskList by index
     *
     * @param x index of item to be removed
     */
    public void removeItem(int x) {
        items.remove(x);
    }

    /**
     * Returns all items in the TaskList as an ArrayList of TaskTypes
     *
     * @returns ArrayList of TaskTypes
     */

    public ArrayList<TaskType> getItems() {
        return items;
    }
}
