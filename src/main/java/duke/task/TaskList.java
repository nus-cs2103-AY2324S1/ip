package duke.task;

import duke.DukeException;
import duke.Ui;

import java.util.ArrayList;

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
    public void addTask(Task task) {
        this.tasks.add(task);
        this.ui.addTaskMessage(task, tasks.size());

    }

    /**
     * Deletes a Task at a given index from the list of tasks.
     *
     * @param index Integer containing index of Task to be deleted.
     * @throws DukeException When selected index is out of the list range.
     */
    public void deleteTask(int index) throws DukeException {
        try {
            Task deletedTask = tasks.get(index - 1);
            tasks.remove(index - 1);
            this.ui.deleteTaskMessage(deletedTask, tasks.size());
        } catch (IndexOutOfBoundsException e) {
            throw new DukeException("Index is out of bounds! Please choose a valid index");
        }
    }

    /**
     * Prints the current list of tasks.
     */
    public void listTask() {
        if (tasks.size() == 0) {
            this.ui.emptyTaskMessage();
        }
        for (int i = 0; i < tasks.size(); i++) {
            Task task = tasks.get(i);
            System.out.println(i + 1 + ". " + task);
        }
    }

    /**
     * Modifies a task at given index by either marking it as done or not done.
     *
     * @param input String containing command whether to mark task as done or not done.
     * @param index Integer containing index of task to be modified.
     * @throws DukeException When selected index is out of the list range.
     */
    public void modifyTask(String input, int index) throws DukeException{
        try {
            Task task = tasks.get(index - 1);
            if (input.equals("mark")) {
                task.markAsDone();
                this.ui.markDoneMessage(task);
            } else {
                task.markAsUndone();
                this.ui.unmarkDoneMessage(task);
            }
        } catch (IndexOutOfBoundsException e) {
            throw new DukeException("Index is out of bounds! Please choose a valid index");
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
