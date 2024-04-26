package duke.tasklist;

import java.util.ArrayList;

import duke.task.Deadline;
import duke.task.Event;
import duke.task.Task;

/**
 * The TaskList class encapsulates a list of tasks.
 */
public class TaskList {
    private ArrayList<Task> taskList;

    /**
     * Constructs a TaskList object with a given list of tasks.
     *
     * @param taskList An ArrayList of Task objects.
     */
    public TaskList(ArrayList<Task> taskList) {
        this.taskList = taskList;
    }

    /**
     * Constructs a TaskList object with an empty list of tasks.
     */
    public TaskList() {
        this.taskList = new ArrayList<>();
    }

    /**
     * Adds a Task object to the taskList.
     *
     * @param task The Task object being added to the taskList.
     */
    public void add(Task task) {
        this.taskList.add(task);
    }

    /**
     * Deletes a Task object from the taskList using its number.
     *
     * @param taskNumber The number of the task to be deleted.
     */
    public void delete(int taskNumber) {
        // change number to index
        int index = taskNumber - 1;
        this.taskList.remove(index);
    }

    /**
     * Retrieves the number of tasks in the tasklist.
     *
     * @return The number of tasks in the tasklist.
     */
    public int size() {
        return this.taskList.size();
    }


    /**
     * Retrieves a task from the tasklist using its number.
     *
     * @param taskNumber The number of the task to be retrieved.
     * @return The Task object at the specified position in the list.
     */
    public Task get(int taskNumber) {
        // change number to index
        int index = taskNumber - 1;
        return this.taskList.get(index);
    }

    /**
     * Updates a specific field of a task in the task list with a new value.
     *
     * @param taskNum The number of the task in the task list to update
     * @param field the field of the task to update; should be one of "taskDescription", "/by", "/from", or "/to"
     * @param newValue the new value to set for the specified field
     */
    public void updateTask(int taskNum, String field, String newValue) {
        Task task = this.taskList.get(taskNum - 1);
        if (field.equals("taskDescription")) {
            task.updateTaskName(newValue);
        } else if (field.equals("/by")) {
            if (task instanceof Deadline) {
                Deadline deadline = (Deadline) task;
                deadline.updateDueDate(newValue);
            }
        } else if (field.equals("/from")) {
            if (task instanceof Event) {
                Event event = (Event) task;
                event.updateStartDate(newValue);
            }
        } else if (field.equals("/to")) {
            if (task instanceof Event) {
                Event event = (Event) task;
                event.updateDueDate(newValue);
            }
        }
    }
}
