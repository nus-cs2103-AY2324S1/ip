package puke.managers;

import java.util.ArrayList;

import puke.task.Task;

/**
 * A class that stores Tasks in a list.
 */
public class TaskList {
    private final ArrayList<Task> list;

    public TaskList() {
        this.list = new ArrayList<Task>(100);
    }

    public TaskList(ArrayList<Task> list) {
        this.list = list;
    }

    /**
     * Lists down the tasks stored in this list in a String format.
     * @return the String representation of the list.
     */
    public String printOut() {
        StringBuilder sb = new StringBuilder();
        int i = 1;
        for (Task s : list) {
            sb.append(String.format("%d. %s\n", i, s.toString()));
            i++;
        }
        return sb.toString();
    }

    public Task get(int index) throws PukeException {
        try {
            return list.get(index);
        } catch (Exception e) {
            throw new PukeException();
        }
    }

    ArrayList<Task> getList() {
        return this.list;
    }

    public void add(Task t) {
        this.list.add(t);
    }

    /**
     * Deletes the task at the specified index from the list and returns it.
     * @param index of the task to be deleted.
     * @return the task that was deleted.
     * @throws PukeException if an invalid index was used.
     */
    public Task delete(int index) throws PukeException {
        Task hold;
        try {
            hold = list.get(index - 1);
            list.remove(index - 1);
            return hold;
        } catch (Exception e) {
            throw new PukeException();
        }
    }

    /**
     * Returns a String representation of each task in the list that matches the key.
     * @param key the keyword to match the tasks with.
     * @return the String representation of the tasks.
     */
    public String find(String key) {
        StringBuilder sb = new StringBuilder();
        int i = 1;
        for (Task s : list) {
            if (s.getDescription().contains(key)) {
                sb.append(String.format("%d. %s\n", i, s.toString()));
                i++;
            }
        }
        return sb.toString();
    }

    /**
     * Marks a task as done.
     * @param index of the task in question.
     * @throws PukeException if an invalid index is used.
     */
    public void mark(int index) throws PukeException {
        try {
            list.get(index - 1).mark();
        } catch (Exception e) {
            throw new PukeException();
        }
    }

    /**
     * Marks a task as undone.
     * @param index of the task in question.
     * @throws PukeException if an invalid index is used.
     */
    public void unmark(int index) throws PukeException {
        try {
            list.get(index - 1).unmark();
        } catch (Exception e) {
            throw new PukeException();
        }
    }

    public int size() {
        return list.size();
    }

    public void clear() {
        this.list.clear();
    }
}
