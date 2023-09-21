package duke.task;

import java.util.ArrayList;

import duke.exception.ChatException;

/**
 * Stores current list of tasks.
 */
public class TaskList {
    private ArrayList<Task> taskList = new ArrayList<>();

    public int getSize() {
        return taskList.size();
    }

    public Task getTask(int taskNumber) throws ChatException {
        if (taskList.size() == 0) {
            throw new ChatException("OOPS!!! The list is empty.");
        } else if (taskNumber <= 0 || taskNumber > taskList.size()) {
            throw new ChatException("OOPS!!! Invalid task number inputted.");
        } else {
            return taskList.get(taskNumber - 1);
        }
    }

    /**
     * Adds task to the tasklist.
     * @param task The task to be added.
     */
    public void addTask(Task task) {
        assert task != null : "task cannot be null";
        taskList.add(task);
    }

    /**
     * Removes task from tasklist.
     * @param taskNumber Index of task in task list.
     * @throws ChatException If index is out of bounds of length of task list.
     */
    public void deleteTask(int taskNumber) throws ChatException {
        taskList.remove(taskNumber - 1);
    }

    /**
     * Marks task in tasklist as done.
     * @param taskNumber Index of task in task list.
     * @throws ChatException If index is out of bounds of length of task list.
     */
    public void markDone(int taskNumber) throws ChatException {
        try {
            Task task = taskList.get(taskNumber - 1);
            task.setDone(true);
        } catch (IndexOutOfBoundsException e) {
            throw new ChatException("OOPS!!! Please specify the correct task number.");
        }
    }

    /**
     * Marks task in tasklist as undone.
     * @param taskNumber Index of task in task list.
     * @throws ChatException If index is out of bounds of length of task list.
     */
    public void markUndone(int taskNumber) throws ChatException {
        try {
            Task task = taskList.get(taskNumber - 1);
            task.setDone(false);
        } catch (IndexOutOfBoundsException e) {
            throw new ChatException("OOPS!!! Please specify the correct task number.");
        }
    }

    /**
     * Returns list of task that fits the keyword given.
     * @param keyword User input that specifies task description.
     * @return Tasklist containing tasks that matches keyword.
     */
    public TaskList findTask(String keyword) {
        TaskList foundList = new TaskList();
        for (Task task : taskList) {
            if (task.isFound(keyword)) {
                foundList.addTask(task);
            }
        }
        return foundList;
    }
}
