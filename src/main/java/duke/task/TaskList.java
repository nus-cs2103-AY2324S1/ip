package duke.task;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Collections;
import java.util.stream.Collectors;

import duke.exception.DukeNoTaskFoundException;

/**
 * Represents a list of tasks.
 */
public class TaskList {
    private ArrayList<Task> taskList;

    /**
     * Constructs an empty TaskList.
     */
    public TaskList() {
        this.taskList = new ArrayList<>();
    }

    /**
     * Constructs a TaskList with the given list of tasks.
     *
     * @param taskList An ArrayList of tasks to initialise the TaskList.
     */
    public TaskList(ArrayList<Task> taskList) {
        Collections.sort(taskList);
        this.taskList = taskList;
    }

    /**
     * Adds a new todo task to the task list.
     *
     * @param description The description of the todo task.
     * @return The added todo task.
     */
    public Task addTodo(String description) {
        Task todo = new Todo(description);
        taskList.add(todo);
        Collections.sort(taskList);
        return todo;
    }

    /**
     * Adds a new deadline task to the task list.
     *
     * @param description The description of the deadline task.
     * @param by          The due date of the deadline task.
     * @return The added deadline task.
     */
    public Task addDeadline(String description, LocalDate by) {
        Task deadline = new Deadline(description, by);
        taskList.add(deadline);
        Collections.sort(taskList);
        return deadline;
    }

    /**
     * Adds a new event task to the task list.
     *
     * @param description The description of the event task.
     * @param from        The start date of the event.
     * @param to          The end date of the event.
     * @return The added event task.
     */
    public Task addEvent(String description, LocalDate from, LocalDate to) {
        Task event = new Event(description, from, to);
        taskList.add(event);
        Collections.sort(taskList);
        return event;
    }

    /**
     * Marks a task at the specified index as done.
     *
     * @param index The index of the task to mark as done.
     * @return The marked task.
     * @throws DukeNoTaskFoundException If the task is not found at the specified index.
     */
    public Task markAsDone(int index) throws DukeNoTaskFoundException {
        try {
            Task task = taskList.get(index - 1);
            task.markAsDone();
            return task;
        } catch (IndexOutOfBoundsException e) {
            throw new DukeNoTaskFoundException();
        }
    }

    /**
     * Marks a task at the specified index as undone.
     *
     * @param index The index of the task to mark as undone.
     * @return The marked task.
     * @throws DukeNoTaskFoundException If the task is not found at the specified index.
     */
    public Task markAsUndone(int index) throws DukeNoTaskFoundException {
        try {
            Task task = this.taskList.get(index - 1);
            task.markAsUndone();
            return task;
        } catch (IndexOutOfBoundsException e) {
            throw new DukeNoTaskFoundException();
        }
    }

    /**
     * Deletes a task at the specified index.
     *
     * @param index The index of the task to delete.
     * @return The deleted task.
     * @throws DukeNoTaskFoundException If the task is not found at the specified index.
     */
    public Task delete(int index) throws DukeNoTaskFoundException {
        try {
            Task task = taskList.get(index - 1);
            taskList.remove(index - 1);
            assert !taskList.contains(task) : "The task should be removed from taskList";
            Collections.sort(taskList);
            return task;
        } catch (IndexOutOfBoundsException e) {
            throw new DukeNoTaskFoundException();
        }
    }

    /**
     * Finds and returns a list of tasks that contain the specified target keyword in their descriptions.
     *
     * @param target The target keyword to search for in task descriptions.
     * @return An ArrayList of tasks that match the search criteria.
     */
    public ArrayList<Task> find(String target) {
        return taskList.stream()
                .filter(task -> task.getDescription().contains(target))
                .collect(Collectors.toCollection(ArrayList::new));
    }


    /**
     * Retrieves the number of tasks in the task list.
     *
     * @return The number of tasks in the task list.
     */
    public int getSize() {
        return this.taskList.size();
    }

    /**
     * Converts the task list to an ArrayList of string representations of tasks.
     *
     * @return An ArrayList of strings representing the tasks in the task list.
     */
    public ArrayList<String> stringify() {
        return this.taskList.stream()
                .map(Task::toString)
                .collect(Collectors.toCollection(ArrayList::new));
    }
}
