package jarvis.tasks;

import static jarvis.exceptions.ExceptionMessages.INVALID_INDEX;
import static jarvis.exceptions.ExceptionMessages.INVALID_MARK;

import java.util.ArrayList;

import jarvis.exceptions.DukeException;
import jarvis.ui.UiMessages;

/**
 * Represents the TaskList Class.
 *
 * @author Shishir
 */
public class TaskList {

    /** List of all tasks */
    private ArrayList<Task> tasks;

    /**
     * Constructs the TaskList Class.
     * @param tasks List of initial tasks.
     */
    public TaskList(ArrayList<Task> tasks) {
        this.tasks = tasks;
    }

    /**
     * Adds a new task to the list.
     * @param newTask New task to be added.
     */
    public void add(Task newTask) {
        this.tasks.add(newTask);
    }

    /**
     * Marks the task with the given index.
     * @param index Index of the task to be marked.
     * @param isMark Mark if true, Unmark if false.
     * @throws DukeException Exception thrown upon invalid index.
     */
    public void changeStatus(int index, boolean isMark) throws DukeException {
        if (index >= this.size()) {
            throw new DukeException(INVALID_INDEX);
        }
        if (this.tasks.get(index).isCompleted() == isMark) {
            throw new DukeException(INVALID_MARK);
        }
        this.tasks.get(index).completeTask(isMark);
    }

    /**
     * Deletes the task with the given index.
     * @param index Index of the task to be deleted.
     * @throws DukeException Exception thrown on invalid index.
     */
    public void delete(int index) throws DukeException {
        if (index > this.size()) {
            throw new DukeException(INVALID_INDEX);
        }
        this.tasks.remove(index);
    }

    /**
     * Returns the size of the list.
     * @return Size of the list.
     */
    public int size() {
        return this.tasks.size();
    }

    /**
     * Returns the task at the given index.
     * @param index Index of required task.
     * @return Required task.
     */
    public Task getTask(int index) {
        return this.tasks.get(index);
    }

    /**
     * Returns the list of all the tasks.
     * @return List of all tasks.
     */
    public ArrayList<Task> getAllTasks() {
        return this.tasks;
    }

    /**
     * Filters and prints the list of all the tasks matching with the given keyword.
     * @param name Given keyword.
     */
    public String filterByName(String name) {
        int count = 1;
        StringBuilder filteredString = new StringBuilder(UiMessages.SEARCH);
        for (Task t : this.tasks) {
            if (t.contains(name)) {
                filteredString.append(count).append(") ").append(t.toString()).append("\n");
                count++;
            }
        }
        return count == 1 ? UiMessages.EMPTY_SEARCH : filteredString.toString();
    }

    /**
     * Returns the current and past deadlines in string format.
     * @return Current and passed deadlines in string format.
     */
    public String getPendingTasks() {
        int currentCount = 0;
        int passedCount = 0;

        StringBuilder currentTasks = new StringBuilder(UiMessages.REMINDER);
        StringBuilder passedTasks = new StringBuilder(UiMessages.PASSED_DUE_DATE);

        for (Task t : this.tasks) {
            if (t instanceof Todo) {
                continue;
            } else if (t.hasPassed() && !t.isCompleted()) {
                passedTasks.append(passedCount + 1).append(") ").append(t).append("\n");
                passedCount++;
            } else if (!t.hasPassed() && !t.isCompleted()) {
                currentTasks.append(currentCount + 1).append(") ").append(t).append("\n");
                currentCount++;
            }
        }

        return toReminder(currentTasks.toString(), passedTasks.toString(), currentCount, passedCount);
    }

    private String toReminder(String currentTasks, String passedTasks, int currentCount, int passedCount) {
        // No current and past tasks pending.
        if (currentCount == 0 && passedCount == 0) {
            return UiMessages.NO_DUE_DATES;
        }

        // There are passed tasks, but no current tasks.
        if (currentCount == 0) {
            return passedTasks;
        }

        // There are current tasks, but no passed tasks.
        if (passedCount == 0) {
            return currentTasks;
        }

        // There are both passed and current tasks.
        return currentTasks + passedTasks;
    }

    /**
     * Prints the list of all the tasks.
     */
    @Override
    public String toString() {
        StringBuilder taskString = new StringBuilder();
        for (int i = 0; i < this.tasks.size(); i++) {
            taskString.append(i + 1).append(") ").append(tasks.get(i).toString()).append("\n");
        }
        return taskString.toString();
    }

}
