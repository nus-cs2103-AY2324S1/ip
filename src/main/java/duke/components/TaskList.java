package duke.components;

import java.util.ArrayList;

import duke.task.Task;

/**
 * Represents a list of Task as an ArrayList. Contains operations on the tasks in
 * the list.
 */
public class TaskList {
    private static final String line = "------------------------------------";
    private ArrayList<Task> fullList;
    private Ui ui;

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
     * @return A string representing the message to be displayed to the user.
     */
    public String addToList(Task task) {
        this.fullList.add(task);
        return ui.showAddMessage(task, this.fullList.size());
    };

    /**
     * Deletes a specified task from the current list of tasks.
     *
     * @param indices The index of the task to be deleted.
     * @return A string representing the message to be displayed to the user.
     */
    public String deleteFromList(int... indices) {
        if (indices.length == 0) {
            return ui.showInvalidMessage();
        }

        StringBuilder result = new StringBuilder();
        int count = 0;
        for (int index : indices) {
            int actualIndex = index - count - 1;
            if (actualIndex >= 0 && actualIndex < fullList.size()) {
                int currSize = this.fullList.size();
                Task item = fullList.get(actualIndex);
                this.fullList.remove(item);
                count++;
                result.append(ui.showDeleteMessage(item, currSize - 1)).append('\n').append('\n');
            } else {
                result.append(ui.showNoItemMessage(index)).append('\n');
            }
        }
        return result.toString();
    }

    /**
     * Marks a tasks as done from the current list of tasks.
     *
     * @param indices The array of index of the task to be marked as done.
     * @return A string representing the message to be displayed to the user.
     */
    public String markItem(int... indices) {
        if (indices.length == 0) {
            return ui.showInvalidMessage();
        }

        StringBuilder result = new StringBuilder();
        for (int index : indices) {
            int actualIndex = index - 1;
            if (actualIndex >= 0 && actualIndex < fullList.size()) {
                Task curr = fullList.get(actualIndex);
                curr.markDone();
                result.append(ui.showMarkMessage(curr)).append('\n').append('\n');
            } else {
                result.append(ui.showNoItemMessage(index));
            }
        }
        return result.toString();
    }

    /**
     * Marks a tasks as not done from the current list of tasks.
     *
     * @param indices The index of the task to be marked as not done.
     * @return A string representing the message to be displayed to the user.
     */
    public String unMarkItem(int... indices) {
        if (indices.length == 0) {
            return ui.showInvalidMessage();
        }

        StringBuilder result = new StringBuilder();
        for (int index : indices) {
            int actualIndex = index - 1;
            if (actualIndex >= 0 && actualIndex < fullList.size()) {
                Task curr = fullList.get(actualIndex);
                curr.markNotDone();
                result.append(ui.showUnmarkMessage(curr)).append('\n').append('\n');
            } else {
                result.append(ui.showNoItemMessage(index));
            }
        }
        return result.toString();
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
     * Returns a new TaskList object with all the tasks in the current
     * list with description that partially matches a keyword.
     *
     * @param keyword The keyword to match current tasks to.
     * @return A new TaskList with all the tasks which match the keyword.
     */
    public TaskList findTask(String keyword) {
        ArrayList<Task> tempArray = new ArrayList<>();
        for (Task task : fullList) {
            if (task.getDescription().contains(keyword)) {
                tempArray.add(task);
            }
        }
        return new TaskList(tempArray);
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
