package chatbot;

import java.util.ArrayList;
import java.util.List;

import chatbot.exception.InvalidCommandException;
import chatbot.exception.InvalidTaskNumberException;
import chatbot.task.Task;

public class TaskList {
    /**
     * TaskList for the Chatbot.
     */
    private final List<Task> taskList;

    /**
     * Constructor for the TaskList.
     */
    public TaskList() {
        this.taskList = new ArrayList<>();
    }

    /**
     * Adds given task to the taskList.
     *
     * @param newTask The task to be added.
     */
    public void add(Task newTask) {
        this.taskList.add(newTask);
    }

    /**
     * Returns the current size of the taskList.
     */
    public int size() {
        return this.taskList.size();
    }

    /**
     * Returns the String representation of the task with the given index.
     *
     * @param i The index of the task.
     * @throws InvalidTaskNumberException If there is no task with the given index in the taskList.
     * @returns The String representation of the task.
     */
    public String taskRep(int i) throws InvalidTaskNumberException {
        try {
            return taskList.get(i).toString();
        } catch (IndexOutOfBoundsException e) {
            throw new InvalidTaskNumberException(i + 1);
        }
    }

    /**
     * Marks the task with the given index as done.
     *
     * @param i The index of the task.
     * @throws InvalidTaskNumberException If there is no task with the given index in the taskList.
     */
    public void markTaskTrue(int i) throws InvalidTaskNumberException {
        try {
            taskList.get(i).markStatus(true);
        } catch (IndexOutOfBoundsException e) {
            throw new InvalidTaskNumberException(i + 1);
        }
    }

    /**
     * Marks the task with the given index as undone.
     *
     * @param i The index of the task.
     * @throws InvalidTaskNumberException If there is no task with the given index in the taskList.
     */
    public void markTaskFalse(int i) throws InvalidTaskNumberException {
        try {
            taskList.get(i).markStatus(false);
        } catch (IndexOutOfBoundsException e) {
            throw new InvalidTaskNumberException(i + 1);
        }
    }

    /**
     * Removes the task with the given index.
     *
     * @param i The index of the task.
     * @throws InvalidTaskNumberException If there is no task with the given index in the taskList.
     */
    public void delete(int i) throws InvalidTaskNumberException {
        try {
            this.taskList.remove(i);
        } catch (IndexOutOfBoundsException e) {
            throw new InvalidTaskNumberException(i + 1);
        }
    }

    /**
     * Returns a new TaskList containing all the tasks in this taskList with the matching keyword.
     *
     * @param keyword The keyword to match with.
     * @returns A TaskList with the matching tasks.
     */
    public TaskList find(String keyword) {
        TaskList res = new TaskList();
        for (Task task : this.taskList) {
            if (task.toString().toLowerCase().contains(keyword.toLowerCase())) {
                res.add(task);
            }
        }
        return res;
    }

    /**
     * Adds a tag to the task.
     *
     * @param taskNum The task number.
     * @param desc    The description of the tag to add.
     * @throws InvalidTaskNumberException If there is no task with the given index in the taskList.
     * @throws InvalidCommandException    If the input does not include a task number.
     */
    public void addTaskTag(String taskNum, String desc) throws InvalidTaskNumberException, InvalidCommandException {
        try {
            int i = Integer.parseInt(taskNum) - 1;
            taskList.get(i).addTag(desc);
        } catch (NumberFormatException e) {
            throw new InvalidCommandException();
        } catch (IndexOutOfBoundsException e) {
            throw new InvalidTaskNumberException(Integer.parseInt(taskNum));
        }
    }

}
