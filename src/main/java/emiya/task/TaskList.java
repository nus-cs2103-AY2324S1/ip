package emiya.task;

import java.util.ArrayList;

import emiya.emiyaexception.CannotFindWordException;
import emiya.emiyaexception.ListEmptyException;

/**
 * A class that represents the list of Tasks within the application.
 */
public class TaskList {

    private ArrayList<Task> taskArrayList;

    public TaskList() {
        this.taskArrayList = new ArrayList<>();
    }

    public ArrayList<Task> getTaskArrayList() {
        return this.taskArrayList;
    }

    public Task get(int pos) {
        return this.taskArrayList.get(pos);
    }

    public void remove(Task task) {
        this.taskArrayList.remove(task);
    }

    public int size() {
        return this.taskArrayList.size();
    }

    public void add(Task task) {
        this.taskArrayList.add(task);
    }

    /**
     * Returns the contents of the list as a String.
     *
     * @return Returns the contents of the list as a String.
     * @throws ListEmptyException An exception that is thrown when the user tries to access the list when
     *     the list is empty.
     */
    public String list() throws ListEmptyException {
        int listPointer = 1;
        StringBuilder listString = new StringBuilder("Lots of things to do! Get to it!:\n");
        if (taskArrayList.size() == 0) {
            throw new ListEmptyException();
        }
        for (Task task : taskArrayList) {
            String listItem = listPointer + "." + task + "\n";
            listPointer++;
            listString.append(listItem);
        }
        return listString.toString();
    }

    /**
     * Return the tasks in the task list that contains the input in their description.
     * @param word The input given by the user to search for among the tasks.
     * @return The tasks within the task list that contain the input as a String.
     * @throws ListEmptyException An exception that is thrown when the user tries to access the list when
     *     the list is empty.
     * @throws CannotFindWordException An exception that is thrown when the program is unable to find
     *     a specified word from the task list.
     */

    public String find(String word) throws ListEmptyException, CannotFindWordException {
        int listPointer = 1;
        boolean isFound = false;
        StringBuilder listString = new StringBuilder("Sure, here are all the tasks that have this word in them:\n");
        if (taskArrayList.size() == 0) {
            throw new ListEmptyException();
        }
        for (Task task : taskArrayList) {
            String taskDescription = task.taskDescription;
            if (taskDescription.contains(word)) {
                String listItem = listPointer + "." + task + "\n";
                listPointer++;
                listString.append(listItem);
                isFound = true;
            }
        }
        if (!isFound) {
            throw new CannotFindWordException();
        }
        return listString.toString();
    }
}
