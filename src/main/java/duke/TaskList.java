package duke;

import java.util.ArrayList;

/**
 * Represents a list of Task as an ArrayList. Contains operations on the tasks in
 * the list.
 */
public class TaskList {
    ArrayList<Task> fullList;
    Ui ui;
    private final static String line = "------------------------------------";

    /**
     * Constructs a new TaskList by initializing a new ArrayList and
     * new Ui object.
     */
    public TaskList() {
        this.fullList = new ArrayList<>();
        this.ui = new Ui();
    }

    /**
     * Constructs a new TaskList with the specified list of tasks and
     * initializes a new Ui object.
     *
     * @param fullList The list of tasks to be included in the TaskList.
     */
    public TaskList(ArrayList<Task> fullList) {
        this.fullList = fullList;
        this.ui = new Ui();
    }

    /**
     * Adds a specified task to the current list of tasks.
     *
     * @param task The task to be added.
     */
    public void addToList(Task task) {
        this.fullList.add(task);
        ui.showAddMessage(task, this.fullList.size());
    };

    /**
     * Deletes a specified task from the current list of tasks.
     *
     * @param index The index of the task to be deleted.
     */
    public void deleteFromList(int index) {
        if (index >= 0 && index < fullList.size()) {
            Task item = fullList.get(index);
            this.fullList.remove(item);
            ui.showDeleteMessage(item, this.fullList.size());
        } else {
            ui.showNoItemMessage();
        }
    }

    /**
     * Marks a tasks as done from the current list of tasks.
     *
     * @param index The index of the task to be marked as done.
     */
    public void markItem(int index) {
        if (index >= 0 && index < fullList.size()) {
            Task curr = fullList.get(index);
            curr.markDone();
            ui.showMarkMessage(curr);
        } else {
            ui.showNoItemMessage();
        }
    }

    /**
     * Marks a tasks as not done from the current list of tasks.
     *
     * @param index The index of the task to be marked as not done.
     */
    public void unMarkItem(int index) {
        if (index >= 0 && index < fullList.size()) {
            Task curr = fullList.get(index);
            curr.markNotDone();
            ui.showUnmarkMessage(curr);
        } else {
            ui.showNoItemMessage();
        }
    }

    /**
     * Returns the list of tasks in the TaskList.
     *
     * @return An ArrayList containing all the current tasks in the TaskList.
     */
    public ArrayList<Task> getTask() {
        return this.fullList;
    }

    /**
     * Returns the number of tasks in the TaskList.
     *
     * @return The number of tasks in the TaskList.
     */
    public int getSize() {
        return this.fullList.size();
    }

    /**
     * Returns a formatted string representation of all the tasks in the TaskList.
     *
     * @return A formatted string containing the tasks in the TaskList.
     */
    @Override
    public String toString() {
        StringBuilder stringBuilder = new StringBuilder();
        for (int i = 0; i < fullList.size(); i++) {
            int index = i + 1;
            stringBuilder.append(index).append(". ")
                    .append(fullList.get(i).toString()).append("\n");
        }
        return line + "\n" + stringBuilder.toString() + "\n" + line;
    }
}
