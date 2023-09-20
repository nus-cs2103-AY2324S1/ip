package pardiyem.task;

import java.util.ArrayList;

public class TaskList {
    private static final String UNABLE_TO_FIND_MSG =
            "Mi dispiace, I cannot find any tasks matching your description";
    private static final String INVALID_INDEX_MSG =
            "Whoops, that number is not an index in the list. Please select a valid index";
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

    public boolean isEmpty() {
        return size() == 0;
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

    public boolean isInRange(int i) {
        return i >= 0 && i < size();
    }

    /**
     * Utility method to delete a task in the taskList
     *
     * @param ind the index of the Task object to be deleted
     * @return status message to indicate the completion of the operation
     * @throws ArrayIndexOutOfBoundsException if the given index is not within the bounds of the taskList
     */
    public String delete(int ind) {
        if (!isInRange(ind)) {
            throw new ArrayIndexOutOfBoundsException(INVALID_INDEX_MSG);
        }
        Task curr = taskList.get(ind);
        taskList.remove(ind);
        return String.format("Noted. I've removed this task:\n%s\nNow you have %d task(s) on the list",
                curr.toString(), taskList.size());
    }

    public String findAndList(String desc) {
        if (desc.isEmpty()) {
            throw new IllegalArgumentException("Whoops, the description cannot be blank");
        }

        TaskList out = new TaskList();

        for (int i = 0; i < this.size(); i++) {
            Task curr = this.getTask(i);
            if (curr.getDescription().contains(desc)) {
                out.add(curr);
            }
        }

        if (out.isEmpty()) {
            return UNABLE_TO_FIND_MSG;
        }

        return out.toString();
    }

    public String mark(int i) {
        if (!isInRange(i)) {
            throw new ArrayIndexOutOfBoundsException(INVALID_INDEX_MSG);
        }
        return String.format("%s\n%s",
                getTask(i).markAsDone(),
                getTask(i).toString());
    }

    public String unmark(int i) {
        if (!isInRange(i)) {
            throw new ArrayIndexOutOfBoundsException(INVALID_INDEX_MSG);
        }

        return String.format("%s\n%s",
                getTask(i).markAsUndone(),
                getTask(i).toString());
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
