package duke.utility;

import java.util.ArrayList;

import duke.exception.FailedSearchException;
import duke.exception.InvalidTaskException;
import duke.task.Task;

/**
 * TaskList class encapsulates an ArrayList of all the tasks
 * users have added into their Bobi task list.
 *
 * @author ruo-x
 */
public class TaskList {
    /** ArrayList of tasks in the task list */
    private final ArrayList<Task> tasks;

    /**
     * Constructor of a TaskList object.
     */
    public TaskList() {
        this.tasks = new ArrayList<>();
    }

    /**
     * Adds a new task into the task list.
     *
     * @param newTask New task to be added.
     */
    public void addTask(Task newTask) {
        // add new task into our tasks
        this.tasks.add(newTask);
    }

    /**
     * Retrieves the task at the specified task number.
     *
     * @param taskNumber Specified task number.
     * @return Task retrieved of that specified task number.
     * @throws InvalidTaskException if task number is not within the length of task list.
     */
    public Task getTask(int taskNumber) throws InvalidTaskException {
        if (taskNumber > this.tasks.size() || taskNumber == 0) {
            throw new InvalidTaskException();
        }
        return this.tasks.get(taskNumber - 1);
    }

    /**
     * Returns the length of task list.
     *
     * @return Length of task list.
     */
    public int getLength() {
        return this.tasks.size();
    }

    /**
     * Remove a task from the task list.
     *
     * @param task Task to remove.
     */
    public void deleteTask(Task task) {
        for (int i = 0; i < 100; i++) {
            if (this.tasks.get(i).equals(task)) {
                this.tasks.remove(i);
                break;
            }
        }
    }

    /**
     * Search the given keyword for all the task names in the task list
     * and returns the filtered task list.
     *
     * @param keyword Keyword to search for.
     * @return A new TaskList object containing all tasks that contains the given keyword.
     * @throws FailedSearchException if no task matches the given keyword.
     */
    public TaskList searchTask(String keyword) throws FailedSearchException {

        // copy all contents of task list into search list
        TaskList searchList = new TaskList();
        searchList.tasks.addAll(this.tasks);

        int originalSize = searchList.getLength();

        for (int i = 0; i < originalSize; i++) {
            for (int j = 0; j < searchList.getLength(); j++) {
                String taskName = searchList.tasks.get(j).getName();

                // remove tasks that do not contain the keyword
                if (!taskName.contains(keyword)) {
                    searchList.tasks.remove(j);
                    break;
                }
            }
        }

        // none of the tasks matches the keyword, throw an exception
        if (searchList.tasks.isEmpty()) {
            throw new FailedSearchException();
        }

        return searchList;
    }

    /**
     * Returns the task list in a String format.
     *
     * @return Task list in a String format.
     */
    @Override
    public String toString() {
        // display in numerical pointers
        String list = "";
        for (int i = 0; i < this.tasks.size(); i++) {
            Task task = this.tasks.get(i);
            if (task == null) {
                break;
            } else {
                list += (i + 1) + "." + task + "\n";
            }
        }
        return list;
    }
}
