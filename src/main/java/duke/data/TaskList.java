package duke.data;

import duke.data.task.ToDo;
import duke.data.task.Deadline;
import duke.data.task.Event;
import duke.data.task.Task;
import duke.data.exception.DukeException;

import java.io.IOException;
import java.util.ArrayList;

public class TaskList {
    private ArrayList<Task> tasklist;

    public TaskList(ArrayList<Task> tasklist) {
        this.tasklist = tasklist;
    }

    public TaskList() {
        this.tasklist = new ArrayList<>();
    }
    public ArrayList<Task> getTaskList() {
        return this.tasklist;
    }

    /**
     * Marks a Task in the TaskList
     * Returns the Task marked.
     *
     * @param input The number of the Task to be marked.
     * @return The marked Task.
     */
    public Task markTask(int input) throws DukeException {
        if (input < 1 || (input + 1) > tasklist.size()) {
            throw new DukeException("☹ OOPS!!! The task number is invalid.");
        }
        Task task = tasklist.get(input - 1);
        task.mark();
        return task;
    }

    /**
     * Unmarks a Task in the TaskList
     * Returns the Task unmarked.
     *
     * @param input The number of the Task to be unmarked.
     * @return The unmarked Task.
     */
    public Task unmarkTask(int input) throws DukeException {
        if (input < 1 || (input + 1) > tasklist.size()) {
            throw new DukeException("☹ OOPS!!! The task number is invalid.");
        }
        Task task = tasklist.get(input - 1);
        task.unmark();
        return task;
    }


    /**
     * Deletes a Task from the TaskList.
     * Returns the Task deleted.
     *
     * @param input The number of the Task to be deleted.
     * @return The deleted Task.
     */
    public Task deleteTask(int input) throws DukeException {
        if (input < 1 || input + 1 > tasklist.size()) {
            throw new DukeException("☹ OOPS!!! The task number is invalid.");
        }
        Task task = tasklist.get(input - 1);
        tasklist.remove(input);
        return task;
    }

    /**
     * Adds a new ToDo Task to the TaskList.
     * Returns the ToDo Task added.
     *
     * @param description Description of the Task to be added.
     * @return The newly created ToDo Task.
     */
    public Task addToDo(String description) {
        Task newTask = new ToDo(description);
        tasklist.add(newTask);
        return newTask;
    }

    /**
     * Adds a new Deadline Task to the TaskList.
     * Returns the Deadline Task added.
     *
     * @param description Description of the Task to be added.
     * @param deadline The deadline of the Deadline Task.
     * @return The newly created Deadline Task.
     */
    public Task addDeadline(String description, String deadline) {
        Task newTask = new Deadline(description, deadline);
        tasklist.add(newTask);
        return newTask;
    }

    /**
     * Searches the Tasklist for tasks with a specific description.
     *
     * @param description Description of the Tasks to be found.
     * @return A new ArrayList of the Tasks found in the search.
     */
    public ArrayList<Task> searchTasks(String description) {
        ArrayList<Task> result = new ArrayList<>();
        for (Task t : this.tasklist) {
            if (t.getDescription().contains(description)) {
                result.add(t);
            }
        }
        return result;
    }


    /**
     * Adds a new Event Task to the TaskList.
     * Returns the Event Task added.
     *
     * @param description Description of the Task to be added.
     * @param from The start time of the Event.
     * @param to The end time of the Event.
     * @return The newly created Event Task.
     */
    public Task addEvent(String description, String from, String to) {
        Task newTask = new Event(description, from, to);
        tasklist.add(newTask);
        return newTask;
    }
}
