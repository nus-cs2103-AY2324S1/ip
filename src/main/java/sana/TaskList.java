package sana;
import java.util.ArrayList;
import java.util.Scanner;

/**
 * Represents a list of tasks.
 *
 * This class manages a list of tasks and provides methods for adding, deleting,
 * marking as done, and updating tasks. It also supports loading tasks from a txt file
 * retrieving the list size, and generating a formatted string representation of the tasks.
 */
public class TaskList {

    private ArrayList<Task> tasksList = new ArrayList<>();

    /**
     * Constructs a TaskList instance containing the tasks from the list of formatted tasks.
     *
     * @param tasks list of formatted saved tasks loaded from the file.
     */
    public TaskList(String tasks) {
        Scanner scanner = new Scanner(tasks);
        while (scanner.hasNextLine()) {
            String formattedTask = scanner.nextLine();
            tasksList.add(Task.getTask(formattedTask));
        }
    }

    /**
     * Returns the number of tasks in the list.
     *
     * @return the number of tasks in the list.
     */
    public int size() {
        return tasksList.size();
    }

    /**
     * Returns a string representation of the tasks in the list.
     *
     * @return a string representation of the tasks.
     */
    public String toString() {
        StringBuilder task = new StringBuilder();
        for (int i = 0; i < tasksList.size(); i++) {
            int id = i + 1;
            task.append(id + "." + tasksList.get(i).toString() + "\n");
        }
        return ("Here are the tasks in your list:\n" + task);
    }

    /**
     * Adds a task to the list.
     *
     * @param task the task to be added.
     */
    public void add(Task task) {
        tasksList.add(task);
    }

    /**
     * Deletes a task from the list based on its ID.
     *
     * @param taskId the ID of the task to be deleted.
     * @return the deleted task.
     * @throws SanaException if the specified task ID is invalid.
     */
    public Task delete(int taskId) throws SanaException {
        if (taskId > tasksList.size() || taskId <= 0) {
            throw new SanaException("No such task!");
        }
        Task temp = tasksList.get(taskId - 1);
        tasksList.remove(taskId - 1);
        return temp;
    }

    /**
     * Updates the stored tasks in the file by first clearing the previous tasks in the file.
     *
     * @param storage the Storage instance to use for updating tasks.
     */
    public void update(Storage storage) {
        storage.clear("/Users/ariellacallista/Desktop/SanaTasks.txt");
        for (Task task : tasksList) {
            storage.save("/Users/ariellacallista/Desktop",
                    "/Users/ariellacallista/Desktop/SanaTasks.txt", task);
        }
    }

    /**
     * Marks a task as done based on its ID.
     *
     * @param taskId the ID of the task to be marked as done.
     * @throws SanaException if the specified task ID is invalid.
     */
    public void mark(int taskId) throws SanaException {
        if (taskId > tasksList.size() || taskId <= 0) {
            throw new SanaException("No such task!");
        }
        tasksList.get(taskId - 1).markAsDone();
        System.out.println("Nice! I've marked this task as done:\n"
                + tasksList.get(taskId - 1).toString());
    }

    /**
     * Marks a task as not done based on its ID.
     *
     * @param taskId the ID of the task to be marked as not done.
     * @throws SanaException if the specified task ID is invalid.
     */
    public void unmark(int taskId) throws SanaException {
        if (taskId > tasksList.size() || taskId <= 0) {
            throw new SanaException("No such task!");
        }
        tasksList.get(taskId - 1).markAsNotDone();
        System.out.println("OK, I've marked this task as not done yet:\n"
                + tasksList.get(taskId - 1).toString());
    }

    public Task get(int id) {
        return tasksList.get(id);
    }
}
