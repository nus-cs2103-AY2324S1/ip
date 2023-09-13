package jarvis.tasks;

import java.util.ArrayList;

import jarvis.exceptions.DukeException;

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
            throw new DukeException("I'm unable to perform the mark/unmark operation due to an invalid index!");
        }
        if (this.tasks.get(index).isCompleted() == isMark) {
            throw new DukeException("I'm unable to perform the mark/unmark operation because the task"
                    + " is already marked/unmarked!");
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
            throw new DukeException("I'm unable to perform the delete operation due to an invalid index!");
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
        StringBuilder filteredString = new StringBuilder();
        for (Task t : this.tasks) {
            if (t.contains(name)) {
                filteredString.append(count).append(") ").append(t.toString()).append("\n\n");
                count++;
            }
        }
        return filteredString.toString();
    }

    public String getDeadlines() {
        int count = 1;
        int count1 = 1;
        StringBuilder deadlineString = new StringBuilder("Just a friendly reminder, you have the following deadlines"
                + " due soon! \n\n");
        StringBuilder passedDeadlineString = new StringBuilder("The following tasks have passed the due date!\n\n");
        for (Task t : this.tasks) {
            if (t instanceof Deadline) {
                Deadline d = (Deadline) t;
                if (d.isDue() && !d.isCompleted()) {
                    deadlineString.append(count).append(") ").append(d.toString()).append("\n");
                    count++;
                } else if (!d.isDue() && !d.isCompleted()) {
                    passedDeadlineString.append(count1).append(") ").append(d.toString()).append("\n");
                    count1++;
                }
            }
        }
        return count == 1 && count1 == 1
                ? "You have no passed/current deadlines! Good Job!"
                : count == 1 && count1 != 1
                    ? passedDeadlineString.toString()
                    : count != 1 && count1 == 1
                        ? deadlineString.toString()
                        : deadlineString.toString() + "\n" + passedDeadlineString.toString();
    }

    /**
     * Prints the list of all the tasks.
     */
    @Override
    public String toString() {
        StringBuilder taskString = new StringBuilder();
        for (int i = 0; i < this.tasks.size(); i++) {
            taskString.append(i + 1).append(") ").append(tasks.get(i).toString()).append("\n\n");
        }
        return taskString.toString();
    }

}
