package duke.utils;

import duke.DukeException;
import duke.task.Deadline;
import duke.task.Event;
import duke.task.Task;
import duke.task.Todo;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

/**
 * The `TaskList` class represents a list of tasks and provides methods for managing tasks within the list.
 */
public class TaskList {
    private List<Task> toDoList;

    /**
     * Constructs a `TaskList` with an existing list of tasks.
     * @param toDoList The list of tasks to initialize the `TaskList`.
     */
    public TaskList(List<Task> toDoList) {
        this.toDoList = toDoList;
    }

    /**
     * Constructs an empty `TaskList`.
     */
    public TaskList() {
        this.toDoList = new ArrayList<>();
    }

    /**
     * Retrieves the list of tasks contained in the `TaskList`.
     * @return The list of tasks.
     */
    public List<Task> getTasks() {
        return toDoList;
    }

    /**
     * Retrieves the number of tasks in the `TaskList`.
     * @return The number of tasks in the list.
     */
    public int getSize() {
        return toDoList.size();
    }

    /**
     * Marks a task as done based on its index in the list.
     * @param taskIndex The index of the task to mark as done.
     * @return The task that was marked as done.
     * @throws DukeException If the task index is invalid.
     */
    public Task markTaskDone(int taskIndex) throws DukeException {
        try {
            Task task = toDoList.get(taskIndex);
            task.markDone();
            return task;
        } catch (NullPointerException e) {
            throw new DukeException("Invalid task number");
        } catch (IndexOutOfBoundsException e) {
            throw new DukeException("Invalid task number");
        }
    }

    /**
     * Unmarks a task as done based on its index in the list.
     * @param taskIndex The index of the task to unmark as done.
     * @return The task that was unmarked as done.
     * @throws DukeException If the task index is invalid.
     */
    public Task unmarkTask(int taskIndex) throws DukeException {
        try {
            Task task = toDoList.get(taskIndex);
            task.markNotDone();
            return task;
        } catch (NullPointerException e) {
            throw new DukeException("Invalid task number");
        } catch (IndexOutOfBoundsException e) {
            throw new DukeException("Invalid task number");
        }
    }

    /**
     * Adds a new todo task to the list.
     * @param todoDescription The description of the todo task.
     * @return The newly added todo task.
     * @throws DukeException If there is an issue adding the task.
     */
    public Task addTodoTask(String todoDescription) throws DukeException {
        Task newTask = new Todo(todoDescription);
        toDoList.add(newTask);
        return newTask;
    }

    /**
     * Adds a new deadline task to the list.
     * @param description The description of the deadline task.
     * @param dateTime The deadline date and time of the task.
     * @return The newly added deadline task.
     * @throws DukeException If there is an issue adding the task.
     */
    public Task addDeadlineTask(String description, LocalDateTime dateTime) throws DukeException {
        Task newTask = new Deadline(description, dateTime);
        toDoList.add(newTask);
        return newTask;
    }

    /**
     * Adds a new event task to the list.
     * @param description The description of the event task.
     * @param fromDatetime The starting date and time of the event.
     * @param toDateTime The ending date and time of the event.
     * @return The newly added event task.
     * @throws DukeException If there is an issue adding the task.
     */
    public Task addEventTask(String description, LocalDateTime fromDatetime, LocalDateTime toDateTime) throws DukeException {
        Task newTask = new Event(description, fromDatetime, toDateTime);
        toDoList.add(newTask);
        return newTask;
    }

    /**
     * Deletes a task from the list based on its index.
     * @param taskNumber The index of the task to be deleted.
     * @return The task that was deleted.
     * @throws DukeException If the task index is invalid.
     */
    public Task deleteTask(int taskNumber) throws DukeException {
        try {
            Task task = toDoList.get(taskNumber);
            toDoList.remove(taskNumber);
            return task;
        } catch (IndexOutOfBoundsException e) {
            throw new DukeException("Invalid task number");
        } catch (NullPointerException e) {
            throw new DukeException("Invalid task number");
        }
    }

    /**
     * Searches for tasks in a to-do list based on a specified keyword in their descriptions.
     * @param keyword The keyword to search for within task descriptions.
     * @return A list of tasks that contain the specified keyword in their descriptions.
     */
    public List<Task> findTasksByKeyword(String keyword) {
        List<Task> matchingTasks = new ArrayList<>();
        for (int i = 0; i < toDoList.size(); i++) {
            Task task = toDoList.get(i);
            if (task.getDescription().contains(keyword)) {
                matchingTasks.add(task);
            }
        }
        if (matchingTasks.size() < 1) {
            System.out.println("OOPS! No matching tasks found");
        }
        return matchingTasks;
    }
}
