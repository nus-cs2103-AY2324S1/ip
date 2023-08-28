package duke.task;

import duke.exception.DukeException;

import java.util.ArrayList;
import java.util.List;

/**
 * Represents a list for the Task objects.
 */
public class TaskList {

    /** List of Task objects, encapsulated in the class. */
    private List<Task> taskList;

    public TaskList(List<Task> tasks) {
        this.taskList = tasks;
    }

    public TaskList() {
        this(new ArrayList<>());
    }

    /**
     * Getter for the list size.
     *
     * @return List size.
     */
    public int getListSize() {
        return taskList.size();
    }

    /**
     * Adds a Task object to the list.
     *
     * @param task Task object to be added.
     */
    public void addTask(Task task) {
        taskList.add(task);
    }

    /**
     * Deletes a Task object from the list based on its index.
     *
     * @param index Index of the Task object.
     * @return Task object that is deleted.
     * @throws DukeException If index does not exist in the list.
     */
    public Task deleteTask(int index) throws DukeException {
        if (index > taskList.size() || index <= 0) {
            throw new DukeException(String.format(" ☹ OOPS!!! Task %d does not exist.", index));
        }
        return taskList.remove(index - 1);
    }

    /**
     * Marks the Task object in the list based on its index.
     *
     * @param index Index of the Task object.
     * @return Task object that is marked.
     * @throws DukeException If index does not exist in the list.
     */
    public Task markTask(int index) throws DukeException {
        if (index > taskList.size() || index <= 0) {
            throw new DukeException(String.format(" ☹ OOPS!!! Task %d does not exist.", index));
        }
        Task task = taskList.get(index - 1);
        task.markIsDone();
        return task;
    }

    /**
     * Unmark the Task object in the list based on its index.
     *
     * @param index Index of the Task object.
     * @return Task object that is unmarked.
     * @throws DukeException If index does not exist in the list.
     */
    public Task unmarkTask(int index) throws DukeException {
        if (index > taskList.size() || index <= 0) {
            throw new DukeException(String.format(" ☹ OOPS!!! Task %d does not exist.", index));
        }
        Task task = taskList.get(index - 1);
        task.markNotDone();
        return task;
    }

    public String findTasks(String keyword) {
        StringBuilder msg = new StringBuilder();
        int foundCount = 0;
        for (int i = 1; i <= taskList.size(); i++) {
            Task task = taskList.get(i - 1);
            if (task.contains(keyword)) {
                foundCount += 1;
                msg.append(String.format("%d.%s", foundCount, task));
                if (i != taskList.size()) {
                    msg.append("\n");
                }
            }
        }
        return msg.toString();
    }

    /**
     * Represents a String to be written in an external file.
     *
     * @return String representation.
     */
    public String stringToFile() {
        StringBuilder msg = new StringBuilder();
        for (Task task : taskList) {
            msg.append(String.format("%s\n", task.stringToFile()));
        }
        return msg.toString();
    }

    @Override
    public String toString() {
        StringBuilder msg = new StringBuilder("Here are the tasks in your list:");
        for (int i = 1; i <= taskList.size(); i++) {
            if (i == 1) {
                msg.append("\n");
            }
            msg.append(String.format("%d.%s", i, taskList.get(i - 1)));
            if (i != taskList.size()) {
                msg.append("\n");
            }
        }
        return msg.toString();
    }
}
