package oscar.essential;

import oscar.exception.OscarException;
import oscar.task.Task;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;

import java.util.ArrayList;

/**
 * Contains ArrayList of tasks that Oscar can interact with.
 */
public class TaskList {
    private ArrayList<Task> taskList;

    /**
     * Uses an empty task list.
     */
    public TaskList() {
        this.taskList = new ArrayList<>();
    }

    /**
     * Uses the saved task list.
     */
    public TaskList(ObjectInputStream stream) throws OscarException {
        this.taskList = load(stream);
    }

    /**
     * Loads the task list from the object input stream.
     * Solution adapted by <a href="https://howtodoinjava.com/java/collections/arraylist/
     * serialize-deserialize-arraylist/">...</a>
     *
     * @param stream Deserialized save file stream.
     * @return Saved task list.
     * @throws OscarException Unable to handle object input stream.
     */
    private ArrayList<Task> load(ObjectInputStream stream) throws OscarException {
        try {
            @SuppressWarnings("unchecked")
            ArrayList<Task> tempList = (ArrayList<Task>) stream.readObject();
            taskList = tempList;
            return taskList;
        } catch (IOException e) {
            throw new OscarException("Sorry! There is an error loading the saved task list.\n");
        } catch (ClassNotFoundException e) {
            throw new OscarException("Sorry! Class cannot be found.\n");
        }
    }

    /**
     * Saves the current task list by serializing it.
     *
     * @param stream Object output stream of saved file.
     * @throws OscarException Input or output error.
     */
    public void save(ObjectOutputStream stream) throws OscarException {
        try {
            stream.writeObject(taskList);
        } catch (IOException e) {
            throw new OscarException("Sorry! There is an issue with your input or output.\n");
        }
    }

    /**
     * Obtains the number of tasks in the task list.
     *
     * @return Count of tasks.
     */
    public int getSize() {
        return taskList.size();
    }

    /**
     * Lists stored tasks in chronological order of addition.
     */
    public void list() {
        System.out.println("Here are the tasks in your list:");
        for (int i = 1; i <= taskList.size(); i++) {
            Task currentTask = taskList.get(i - 1);
            System.out.println(i + "." + currentTask);
        }
        System.out.println();
    }

    /**
     * Displays the number of tasks stored in the task list.
     */
    public void listCount() {
        int listSize = getSize();
        if (listSize == 0) {
            System.out.println("You have no tasks in the list. Add some now!\n");
        } else if (listSize == 1) {
            System.out.println("You have 1 task in the list.\n");
        } else {
            System.out.println("You now have " + listSize + " tasks in the list.\n");
        }
    }

    /**
     * Marks a task as done.
     *
     * @param index Task number.
     * @return Description of task.
     */
    public String mark(int index) {
        Task currentTask = taskList.get(index);
        currentTask.markAsDone();
        return currentTask.toString();
    }

    /**
     * Marks a task as not done.
     *
     * @param index Task number.
     * @return Description of task.
     */
    public String unmark(int index) {
        Task currentTask = taskList.get(index);
        currentTask.markAsNotDone();
        return currentTask.toString();
    }

    /**
     * Deletes a task.
     *
     * @param index Task number.
     * @return Description of task.
     */
    public String delete(int index) {
        Task currentTask = taskList.remove(index);
        return currentTask.toString();
    }

    /**
     * Adds a task to the task list.
     *
     * @param task Task to be added.
     */
    public void add(Task task) {
        taskList.add(task);
    }

    /**
     * Lists all tasks in the task list containing the keyword.
     *
     * @param keyword String to match.
     */
    public void find(String keyword) {
        System.out.println("Here are the matching tasks in your list:");
        for (int i = 1; i <= taskList.size(); i++) {
            Task currentTask = taskList.get(i - 1);
            if (currentTask.getDescription().contains(keyword)) {
                System.out.println(i + "." + currentTask);
            }
        }
        System.out.println();
    }
}
