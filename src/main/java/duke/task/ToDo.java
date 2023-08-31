package duke.task;

import java.util.ArrayList;
import duke.DukeException;

/**
 * Represents a ToDo task.
 */
public class ToDo extends Task {
    public String description;

    /**
     * Constructs a ToDo task with the given description.
     *
     * @param description The description of the ToDo task.
     */
    public ToDo(String description) {
        super(description);
        this.description = description;
    }

    /**
     * Returns a string representation of the ToDo task.
     *
     * @return A string representation of the ToDo task.
     */
    @Override
    public String toString() {
        return "[T]" + super.toString();
    }

    /**
     * Returns a string representation of the ToDo task for writing to a file.
     *
     * @return A string representation of the ToDo task for file output.
     */
    public String toStringFile() {
        return "T | " + super.toStringFile();
    }

    /**
     * Gets the type of the task.
     *
     * @return The type of the task.
     */
    @Override
    public String getType() {
        return "TODO";
    }

    /**
     * Adds a ToDo task to the list of tasks.
     *
     * @param description The description of the ToDo task.
     * @param list The ArrayList to which the task will be added.
     * @return The added ToDo task.
     * @throws DukeException If the description is empty.
     */
    public static ToDo addTodo(String description, ArrayList<Task> list) throws DukeException {
        if (description.isBlank()) {
            throw new DukeException("☹ OOPS!!! The description of a todo cannot be empty.");
        }
        ToDo newTask = new ToDo(description);
        list.add(newTask);
        return newTask;
    }

    /**
     * Adds a ToDo task to the list of tasks from a file.
     *
     * @param description The description of the ToDo task.
     * @param list The ArrayList to which the task will be added.
     * @param isMarked The value indicating whether the task is marked.
     */
    public static void addToDoFromFile(String description, ArrayList<Task> list, String isMarked) {
        ToDo newTask = new ToDo(description);
        newTask.markFromRead(isMarked);
        list.add(newTask);
    }

    /**
     * Compares this ToDo task to another object for equality.
     *
     * @param obj The object to compare.
     * @return True if the objects are equal, false otherwise.
     */
    @Override
    public boolean equals(Object obj) {
        if (obj instanceof ToDo toDo) {
            return this.description.equals(toDo.description);
        }
        return false;
    }
}
