package qi.tasklist;

import java.util.ArrayList;

import qi.qiexception.QiException;
import qi.task.Task;

/**
 * Represents the list of task.
 */
public class TaskList {
    private final ArrayList<Task> taskList;

    /**
     * Initializes the inner data structure to store task.
     */
    public TaskList() {
        this.taskList = new ArrayList<>();
    }

    /**
     * Adds a task to the list.
     *
     * @param t Task to be added.
     */
    public void addTask(Task t) {
        this.taskList.add(t);
    }

    /**
     * Marks a task with the given Id as done or not done.
     *
     * @param id Integer representing the index (start from one)
     *           of the task in the list.
     * @param isDone Boolean representing whether a task is done or not.
     */
    public void mark(int id, boolean isDone) throws QiException {
        try {
            this.taskList.get(id - 1).mark(isDone);
        } catch (IndexOutOfBoundsException e) {
            throw new QiException("Please specify a valid task ID!");
        }
    }

    /**
     * Returns string description of the task with the given Id.
     *
     * @param id Integer representing the index (start from one)
     *           of the task in the list.
     * @return String description of the task with the given Id.
     */
    public String showTask(int id) {
        return this.taskList.get(id - 1).toString();
    }

    /**
     * Deletes the task with given Id from the list.
     *
     * @param taskId Integer representing the index (start from one)
     *               of the task in the list.
     * @return Task deleted.
     */
    public Task deleteTask(int taskId) throws QiException {
        try {
            return this.taskList.remove(taskId - 1);
        } catch (IndexOutOfBoundsException e) {
            throw new QiException("Please specify a valid task ID!");
        }
    }

    /**
     * Returns the number of tasks in the list.
     *
     * @return Integer indicating the number of tasks in the list.
     */
    public int size() {
        return this.taskList.size();
    }

    /**
     * Returns all the tasks whose description match
     * an input keyword.
     *
     * @param keyWord String representing the keyword being examined.
     * @return String representation of all the matching tasks.
     */
    public String matchingKeyWord(String keyWord) {
        StringBuilder ans = new StringBuilder();
        for (int i = 0; i < this.size(); i++) {
            if (!this.taskList.get(i).isMatching(keyWord)) {
                continue;
            }
            int id = i + 1;
            ans.append("     ").append(id).append(". ").append(showTask(id));
            ans.append('\n');
        }

        return ans.toString();
    }

    /**
     * Returns list of tasks in the form of string.
     *
     * @return String representation of the list.
     */
    @Override
    public String toString() {
        StringBuilder ans = new StringBuilder();
        for (int i = 0; i < this.taskList.size(); i++) {
            int id = i + 1;
            ans.append("     ").append(id).append(". ").append(showTask(id));
            ans.append('\n');
        }
        return ans.toString();
    }
}

