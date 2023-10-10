package skye.data;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import skye.data.exception.DukeException;
import skye.data.exception.DukeExceptionType;
import skye.data.task.Deadline;
import skye.data.task.Task;

/**
 * Represents a container storing a list of tasks that allows
 * task related operations such as insertion, deletion and updating
 * of task completion status to be performed.
 */
public class TaskList {

    /** A list to store task objects  */
    private final List<Task> tasks;

    public TaskList() {
        this.tasks = new ArrayList<>();
    }

    public TaskList(List<Task> tasks) {
        this.tasks = tasks;
    }

    /**
     * Returns a list of tasks that is currently stored in the program.
     *
     * @return a list of tasks
     */
    public List<Task> getTasks() {
        return tasks;
    }

    /**
     * Adds a new task to the list.
     *
     * @param task A task which can be either a Deadline, Event or ToDo.
     */
    public void addTask(Task task) {
        tasks.add(task);
    }

    /**
     * Checks if a task number is within the valid range.
     *
     * @param taskNumber The numerical order of the task in the list.
     * @return true if 1 < taskNumber < number of tasks
     *         false otherwise
     */
    private boolean isInvalidTaskNumber(int taskNumber) {
        return (taskNumber <= 0) || (taskNumber > tasks.size());
    }

    /**
     * Marks a task as completed by specifying the task number.
     *
     * @param taskNumber The numerical order of the task in the list.
     * @return The task that was marked as completed
     * @throws DukeException if taskNumber < 1 or taskNumber > number of tasks
     */
    public Task markTask(int taskNumber) throws DukeException {
        if (isInvalidTaskNumber(taskNumber)) {
            throw new DukeException(DukeExceptionType.INVALID_RANGE);
        } else if (tasks.get(taskNumber - 1).isDone()) {
            throw new DukeException(DukeExceptionType.TASK_ALREADY_MARKED);
        } else {
            tasks.get(taskNumber - 1).markAsDone();
            return tasks.get(taskNumber - 1);
        }
    }

    /**
     * Unmark a completed task by specifying the task number.
     *
     * @param taskNumber The numerical order of the task in the list.
     * @return The task that was unmarked
     * @throws DukeException if taskNumber < 1 or taskNumber > number of tasks
     */
    public Task unmarkTask(int taskNumber) throws DukeException {
        if (isInvalidTaskNumber(taskNumber)) {
            throw new DukeException(DukeExceptionType.INVALID_RANGE);
        } else if (!tasks.get(taskNumber - 1).isDone()) {
            throw new DukeException(DukeExceptionType.TASK_ALREADY_UNMARKED);
        } else {
            tasks.get(taskNumber - 1).markAsNotDone();
            return tasks.get(taskNumber - 1);
        }
    }

    /**
     * Delete a task
     *
     * @param taskNumber The numerical order of the task in the list.
     * @return The task that was deleted
     * @throws DukeException if taskNumber < 1 or taskNumber > number of tasks
     */
    public Task deleteTask(int taskNumber) throws DukeException {
        if (isInvalidTaskNumber(taskNumber)) {
            throw new DukeException(DukeExceptionType.INVALID_RANGE);
        } else {
            return tasks.remove(taskNumber - 1);
        }
    }

    /**
     * Shows a list of tasks that are due on a specified deadline.
     *
     * @param date The deadline of the task.
     * @return A list of tasks due on the specified deadline.
     */
    public List<Task> showTasksDueOn(LocalDate date) {
        return tasks.stream()
                .filter(task -> task instanceof Deadline)
                .map(task -> (Deadline) task)
                .filter(deadline -> deadline.getBy().toLocalDate().equals(date))
                .collect(Collectors.toList());
    }

    /**
     * Finds a list of tasks containing a specified keyword in the task description.
     *
     * @param keyword Keyword specified by the user
     * @return A list of matching tasks
     */
    public List<Task> findTasksContaining(String keyword) {
        return tasks.stream()
                .filter(task -> task.getDescription().contains(keyword))
                .collect(Collectors.toList());
    }
}
