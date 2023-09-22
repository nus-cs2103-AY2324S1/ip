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
        } else if (taskNumber <= 0) {
            throw new ChatException("OOPS!!! Task number needs to be positive.");
        } else if (taskNumber > taskList.size()) {
            throw new ChatException("OOPS!!! Task number out of bounds of index.");
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
     * @param taskNumbers Indexes of task in task list.
     * @throws ChatException If index is out of bounds of length of task list.
     */
    public TaskList deleteTask(String[] taskNumbers) throws ChatException {
        TaskList deletedList = new TaskList();
        for (int i = taskNumbers.length - 1; i > -1; i--) {
            int taskNumber = Integer.parseInt(taskNumbers[i]);
            deletedList.addTask(taskList.get(taskNumber - 1));
            taskList.remove(taskNumber - 1);
        }
        return deletedList;
    }

    /**
     * Marks task in tasklist as done.
     * @param taskNumbers Indexes of tasks in task list.
     * @throws ChatException If index is out of bounds of length of task list.
     */
    public TaskList markDone(String[] taskNumbers) throws ChatException {
        try {
            TaskList markList = new TaskList();
            for (int i = 0; i < taskNumbers.length; i++) {
                int taskNumber = Integer.parseInt(taskNumbers[i]);
                markList.addTask(taskList.get(taskNumber - 1));
                Task task = taskList.get(taskNumber - 1);
                task.setDone(true);
            }
            return markList;
        } catch (IndexOutOfBoundsException e) {
            throw new ChatException("OOPS!!! Please specify the correct task number.");
        }
    }

    /**
     * Marks task in tasklist as undone.
     * @param taskNumber Index of task in task list.
     * @throws ChatException If index is out of bounds of length of task list.
     */
    public TaskList markUndone(String[] taskNumbers) throws ChatException {
        try {
            TaskList unmarkList = new TaskList();
            for (int i = 0; i < taskNumbers.length; i++) {
                int taskNumber = Integer.parseInt(taskNumbers[i]);
                unmarkList.addTask(taskList.get(taskNumber - 1));
                Task task = taskList.get(taskNumber - 1);
                task.setDone(false);
            }
            return unmarkList;
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
