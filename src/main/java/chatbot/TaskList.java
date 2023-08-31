package chatbot;

import chatbot.exceptions.InvalidTaskIndexException;
import chatbot.tasks.Task;

import java.util.ArrayList;

/**
 * Class that represents the list of tasks the user has created.
 */
public class TaskList {
    private final ArrayList<Task> taskList;
    static final int MAX_NUMBER_OF_TASKS = 100;

    /**
     * Constructor that instantiates a TaskList object with specified list of task.
     * @param taskList the initial list of task of the TaskList object
     */
    public TaskList(ArrayList<Task> taskList) {
        this.taskList = taskList;
    }

    /**
     * Constructor that instantiates an empty TaskList object.
     */
    public TaskList() {
        this.taskList = new ArrayList<>(MAX_NUMBER_OF_TASKS);
    }

    /**
     * Add a task to the list of tasks.
     * @param task the Task object to be added
     */
    public void addTask(Task task) {
        taskList.add(task);
    }

    /**
     * Mark a task in the list to be completed or not completed.
     * @param isDone whether to mark the task as completed or not completed
     * @param index index of the task to be marked
     * @return String representation of the task after being marked
     * @throws InvalidTaskIndexException when the index provided does not correspond to any task
     */
    public String markAs(boolean isDone, int index) throws InvalidTaskIndexException {
        if (index < 1 || index > taskList.size()) {
            throw new InvalidTaskIndexException();
        }
        Task task = taskList.get(index-1);
        task.markAs(isDone);
        return task.toString();
    }

    /**
     * Delete a task from the list.
     * @param index index of the task to be deleted
     * @return String representation of the task being deleted
     * @throws InvalidTaskIndexException when the index provided does not correspond to any task
     */
    public String deleteTask(int index) throws InvalidTaskIndexException {
        if (index < 1 || index > taskList.size()) {
            throw new InvalidTaskIndexException();
        }
        Task task = taskList.get(index-1);
        taskList.remove(task);
        return task.toString();
    }

    /**
     * Obtain a String representation of all the tasks in the list, each in a line.
     * @return String representation of all tasks in the list
     */
    public String taskListToStrings() {
        StringBuilder sb = new StringBuilder();
        for (Task task: taskList) {
            sb.append(task.toString());
            sb.append("\n");
        }
        return sb.toString();
    }

    /**
     * Obtain an indexed String representation of all tasks, each in a line.
     * @return indexed String representation of all tasks
     */
    public String listTasks() {
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < taskList.size(); i++) {
            Task task = taskList.get(i);
            sb.append(String.format("\t%d.%s\n",
                    i+1,
                    task.toString()));
        }
        return sb.toString();
    }

    /**
     * Obtain the number of tasks in the task list.
     * @return number of tasks in the task list
     */
    public int getSize() {
        return this.taskList.size();
    }

}
