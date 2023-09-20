package pardiyem.task;

import java.util.ArrayList;

public class TaskList {
    protected final ArrayList<Task> taskList;

    public TaskList() {
        taskList = new ArrayList<Task>();
    }

    public TaskList(ArrayList<Task> list) {
        taskList = list;
    }

    /**
     * Getter method for the taskList attribute
     *
     * @return the taskList attribute
     */
    public ArrayList<Task> getList() {
        return taskList;
    }

    /**
     * Getter method for members of the taskList attribute
     *
     * @param i index of the item to be accessed from the taskList attribute
     * @return the corresponding task
     */
    public Task getTask(int i) {
        return taskList.get(i);
    }

    /**
     * Utility method to add a Task object to the taskList
     *
     * @param newTask the Task object to be added
     */
    public void add(Task newTask) {
        taskList.add(newTask);
    }

    /**
     * Utility method to get the size of the taskList
     *
     * @return the size of the taskList
     */
    public int size() {
        return taskList.size();
    }

    public boolean inRange(int i) {
        return i >= 0 && i < taskList.size();
    }

    /**
     * Utility method to delete a task in the taskList
     *
     * @param i the index of the Task object to be deleted
     * @return status message to indicate the completion of the operation
     * @throws ArrayIndexOutOfBoundsException if the given index is not within the bounds of the taskList
     */
    public String delete(int i) {
        if (!inRange(i)) {
            throw new ArrayIndexOutOfBoundsException(
                    "Whoops, that number is not an index in the list. Please select a valid index");
        }
        Task curr = taskList.get(i);
        taskList.remove(i);
        return String.format("Noted. I've removed this task:\n%s\nNow you have %d task(s) on the list",
                curr.toString(), taskList.size());
    }

    public String mark(int i) {
        if (!inRange(i)) {
            throw new ArrayIndexOutOfBoundsException(
                    "Whoops, that number is not an index in the list. Please select a valid index");
        }
        return String.format("%s\n%s",
                taskList.get(i).markAsDone(),
                taskList.get(i).toString());
    }

    public String unmark(int i) {
        if (!inRange(i)) {
            throw new ArrayIndexOutOfBoundsException(
                    "Whoops, that number is not an index in the list. Please select a valid index");
        }
        return String.format("%s\n%s",
                taskList.get(i).markAsUndone(),
                taskList.get(i).toString());
    }

    @Override
    public String toString() {
        StringBuilder out = new StringBuilder();
        for (int i = 0; i < taskList.size(); i++) {
            if (i != 0) {
                out.append("\n");
            }
            out.append(String.format("%d.%s", i + 1, taskList.get(i).toString()));
        }
        return out.toString();
    }
}
