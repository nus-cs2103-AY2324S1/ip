package duke;

import java.util.ArrayList;

/**
 * Represents a TaskList.
 */
public class TaskList {
    private ArrayList<Task> taskList;

    /**
     * Constructs a TaskList object.
     */
    public TaskList() {
        this.taskList = new ArrayList<Task>();
    }

    /**
     * Constructs a TaskList object.
     * 
     * @param savedTaskList ArrayList of type Task retrieved from saved data.
     * @throws DukeException If there is an error loading the file.
     */
    public TaskList(ArrayList<Task> savedTaskList) throws DukeException {
        // this.taskList.addAll(savedTaskList);
        this.taskList = savedTaskList;
    }

    /**
     * Returns the size of the task list.
     * @return Size of the task list as an integer.
     */
    public int size() {
        return taskList.size();
    }

    /**
     * Adds a task to the task list.
     * @param task Task object to be added to the task list.
     */
    public void addToTaskList(Task task) {
        if (taskList.size() < 100) {
            taskList.add(task);
        } else {
            System.out.println("Error: List is full.");
        }
    }

    /**
     * Returns the task at the specified index.
     * @param i Index of the task of interest.
     * @return String representation of the task at the specified index with numbering.
     */
    public String taskToString(int i) {
        return "\t" + (i + 1) + ". " + taskList.get(i);
    }

    /**
     * Returns a string representation of the TaskList object.
     * @return String representation of the TaskList object.
     */
    @Override
    public String toString() {
        String outputString = "";
        for (int i = 0; i < taskList.size(); i++) {
            outputString += taskToString(i) + "\n";
        }

        return outputString;
    }

    /**
     * Marks the task at the specified index as done.
     * @param taskIndex Index of the task to be marked as done.
     */
    public void markTaskAsDone(int taskIndex) {
        taskList.get(taskIndex).markAsDone();
    }

    /**
     * Unmarks the task at the specified index as done.
     * @param taskIndex Index of the task to be unmarked as done.
     */
    public void unmarkTaskAsDone(int taskIndex) {
        taskList.get(taskIndex).unmarkAsDone();
    }

    /**
     * Returns a string representation of the task at the specified index to be saved in the file.
     * @param i Index of the task of interest.
     * @return String representation of the task at the specified index to be saved in the file.
     */
    public String getTaskItemSaveString(int i) {
        return taskList.get(i).saveString();
    }
    
    public Boolean isEmpty() {
        return taskList.isEmpty();
    }

    /**
     * Deletes the task at the specified index.
     * @param taskIndex Index of the task to be deleted.
     */
    public void deleteTask(int taskIndex) {
        taskList.remove(taskIndex);
    }

    public void setPriority(int taskIndex, int priority) {
        taskList.get(taskIndex).setPriority(priority);
    }
}
