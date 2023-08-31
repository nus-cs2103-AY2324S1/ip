package duke.task;

import java.util.ArrayList;
import duke.DukeException;

/**
 * Represents a list of tasks with various operations.
 */
public class TaskList {
    private ArrayList<Task> list;

    /**
     * Constructs a TaskList with an existing task list.
     *
     * @param list The ArrayList containing tasks.
     */
    public TaskList(ArrayList<Task> list) {
        this.list = list;
    }

    /**
     * Constructs an empty TaskList.
     */
    public TaskList() {
        this.list = null;
    }

    /**
     * Adds a task to the list based on the given type and description.
     *
     * @param type The type of task ("todo", "deadline", or "event").
     * @param description The description of the task.
     * @return The added task.
     * @throws DukeException If the input type is invalid.
     */
    public Task addTask(String type, String description) throws DukeException {
        if (type.equals("todo")) {
            return ToDo.addTodo(description, list);
        } else if (type.equals("deadline")) {
            return Deadline.addDeadline(description, list);
        } else if (type.equals("event")) {
            return Event.addEvent(description, list);
        } else {
            throw new DukeException("That's not a valid input!");
        }
    }

    /**
     * Deletes a task at the specified position.
     *
     * @param pos The position of the task to be deleted.
     * @return The deleted task.
     * @throws DukeException If the position is invalid or not provided.
     */
    public Task deleteTask(String pos) throws DukeException {
        if (pos.isEmpty()) {
            throw new DukeException("☹ OOPS!!! Please indicate which item you wish to delete");
        }
        int amt = Integer.parseInt(pos.strip()) - 1;
        if (amt >= list.size()) throw new DukeException("☹ OOPS!!! That's not a valid item!");

        Task temp = list.get(amt);
        list.remove(amt);

        return temp;
    }

    /**
     * Marks or unmarks a task at the specified position.
     *
     * @param type The action ("mark" or "unmark").
     * @param pos The position of the task to be marked or unmarked.
     * @return The task after marking or unmarking.
     * @throws DukeException If the position is invalid or not provided.
     */
    public Task markTask(String type, String pos) throws DukeException {
        if (pos.isEmpty()) {
            throw new DukeException("☹ OOPS!!! Please indicate which item you wish to mark");
        }
        int amt = Integer.parseInt(pos.strip()) - 1;
        if (amt >= list.size()) throw new DukeException("☹ OOPS!!! That's not a valid item!");
        Task newTask = list.get(amt);

        if (type.equals("mark")) {
            newTask.mark();
        } else if (type.equals("unmark")) {
            newTask.unMark();
        }
        return newTask;
    }

    /**
     * Gets a task at the specified position.
     *
     * @param pos The position of the task to retrieve.
     * @return The task at the specified position.
     */
    public Task getTask(int pos) {
        return list.get(pos);
    }

    /**
     * Gets the entire task list.
     *
     * @return The ArrayList containing tasks.
     */
    public ArrayList<Task> getList() {
        return this.list;
    }
}
