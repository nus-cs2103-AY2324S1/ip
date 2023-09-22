package duke.task;

import duke.DukeException;
import duke.Storage;
import duke.Ui;

import java.util.ArrayList;
import java.util.Locale;

/**
 * TaskList is a class containing Tasks. Through the TaskList, users are able to
 * add, delete, view as well as mark a certain task as done.
 */
public class TaskList {
    private ArrayList<Task> tasks;
    private Ui ui;


    /**
     * Constructs a TaskList Object, with the ArrayList of Tasks being empty.
     */
    public TaskList() {
        this.tasks = new ArrayList<Task>();
        this.ui = new Ui();
    }

    /**
     * Constructs a TaskList Object with a given ArrayList being the current list of tasks.
     *
     * @param tasks An ArrayList of Tasks.
     */
    public TaskList(ArrayList<Task> tasks) {
        this.tasks = tasks;
        this.ui = new Ui();
    }

    /**
     * Adds a Task to the ArrayList of Tasks.
     *
     * @param task Task to be added to the list.
     */
    public String addTask(Task task) throws DukeException{
        assert (task != null) : "Adding null task";
        if (containsDuplicate(task)) {
            throw new DukeException("Cannot add duplicate task!");
        }
        this.tasks.add(task);
        return this.ui.addTaskMessage(task, tasks.size());
    }

    /**
     * Checks for duplicate task in the ArrayList.
     * @param task Task to be checked
     * @return Boolean value where true indicates duplicate task, and false otherwise.
     */

    public boolean containsDuplicate(Task task) {
        for (int i = 0; i < this.tasks.size(); i++) {
            Task currentTask = this.tasks.get(i);
            if (currentTask.description.equals(task.description)) {
                return true;
            }
        }
        return false;
    }

    /**
     * Deletes a Task at a given index from the list of tasks.
     *
     * @param index Integer containing index of Task to be deleted.
     * @throws DukeException When selected index is out of the list range.
     */
    public String deleteTask(int index) throws DukeException {
        try {
            Task deletedTask = tasks.get(index - 1);
            tasks.remove(index - 1);
            return this.ui.deleteTaskMessage(deletedTask, tasks.size());
        } catch (IndexOutOfBoundsException e) {
            throw new DukeException("Index is out of bounds! Please choose a valid index");
        }
    }

    /**
     * Prints the current list of tasks.
     */
    public String listTask() {
        if (tasks.size() == 0) {
            this.ui.emptyTaskMessage();
        }
        String output = "";
        for (int i = 0; i < tasks.size(); i++) {
            Task task = tasks.get(i);
            output += i + 1 + ". " + task + "\n";
        }
        return output;
    }

    /**
     * Modifies a task at given index by either marking it as done or not done.
     *
     * @param input String containing command whether to mark task as done or not done.
     * @param index Integer containing index of task to be modified.
     * @throws DukeException When selected index is out of the list range.
     */
    public String modifyTask(String input, int index) throws DukeException{
        try {
            Task task = tasks.get(index - 1);
            if (input.equals("mark")) {
                task.markAsDone();
                return this.ui.markDoneMessage(task);
            } else {
                task.markAsUndone();
                return this.ui.unmarkDoneMessage(task);
            }
        } catch (IndexOutOfBoundsException e) {
            throw new DukeException("Index is out of bounds! Please choose a valid index");
        }

    }

    /**
     * Displays all tasks containing the keyword given by the user.
     *
     * @param keyword String containing keyword to use.
     */
    public String findTask(String keyword) {
        ArrayList<Integer> taskIndexArray = new ArrayList<>();
        for (int i = 0; i < tasks.size(); i++) {
            Task currentTask = tasks.get(i);
            assert (currentTask != null) : "Current Task is null";
            if (currentTask.toString().toUpperCase().contains(keyword.toUpperCase())) {
                taskIndexArray.add(i);
            }
        }
        if (taskIndexArray.size() == 0) {
            return ui.noMatchMessage();
        } else {
            String output = ui.displayMatchMessage() + "\n";
            for (int i = 0; i < taskIndexArray.size(); i++) {
                output = output + ui.displayTaskMessage(i + 1, tasks.get(taskIndexArray.get(i))) + "\n";
            }
            return output;
        }
    }

    /**
     * Returns the ArrayList of Tasks.
     *
     * @return An ArrayList of Tasks.
     */
    public ArrayList<Task> getTasks() {
        return this.tasks;
    }
}
