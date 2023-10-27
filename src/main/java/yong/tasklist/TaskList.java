package yong.tasklist;

import yong.tasks.Deadline;
import yong.tasks.Event;
import yong.tasks.Task;

import yong.exception.DukeException;
import yong.tasks.ToDo;

import java.util.ArrayList;
import java.util.Comparator;

/**
 * Represents the list of tasks in the chatbot.
 */
public class TaskList {

    private ArrayList<Task> taskList;

    /**
     * Constructor for the taskList object.
     */
    public TaskList() {
        this.taskList = new ArrayList<Task>();
    }

    /**
     * Marks the specified task as being done.
     *
     * @param number Number of the task, starting from index 1.
     * @return The task being marked as done.
     */
    public Task mark(Integer number) {
        if (number > taskList.size() || number < 0) {
            throw new DukeException("Wrong Param");
        }

        Task task = taskList.get(number - 1);

        task.markAsDone();

        return task;
    }

    /**
     * Unmarks the specified task as being done.
     *
     * @param number Number of the task, starting from index 1.
     * @return The task being unmarked.
     */
    public Task unmark(Integer number) {
        if (number > taskList.size() || number < 0) {
            throw new DukeException("Wrong Param");
        }

        Task task = taskList.get(number - 1);

        task.unmarkAsDone();

        return task;
    }

    /**
     * Deleted the specified task.
     *
     * @param number Number of the task, starting from index 1.
     * @return The task being deleted.
     */
    public Task delete(Integer number) {
        if (number > taskList.size() || number < 0) {
            throw new DukeException("Wrong Param");
        }

        int previousSize = taskList.size();

        Task task = taskList.remove(number - 1);

        int nextSize = taskList.size();

        assert(previousSize > nextSize);

        return task;
    }

    /**
     * Adds a specific task to the tasklist.
     *
     * @param task Task object to be added.
     */
    public void add(Task task) {
        int previousSize = taskList.size();

        taskList.add(task);

        int newSize = taskList.size();

        assert(previousSize < newSize);
    }

    /**
     * Returns string of all the task in the taskList.
     */
    public String list() {
        String outputString = "";

        for (int i = 0; i < taskList.size(); i++) {
            if (i != 0) {
                outputString = outputString.concat("\n" + (i + 1) + ": " + taskList.get(i));
            } else {
                outputString = outputString.concat((i + 1) + ": " + taskList.get(i));
            }
        }

        return outputString;
    }

    /**
     * Returns the task list as an ArrayList object to be stored.
     *
     * @return task list.
     */
    public ArrayList<Task> get() {
        return this.taskList;
    }

    /**
     * Allows the task list to be set after reading the saved file.
     * @param taskList Stored tasklist.
     */
    public void set(ArrayList<Task> taskList) {
        this.taskList = taskList;
    }

    /**
     * Returns a string for all the tasks in the taskList matching the lookupString.
     *
     * @param lookupString String that user wants to find within task description
     * @return a string for all the tasks in the taskList matching the lookupString
     */
    public String find(String lookupString) {
        String outputString = "";

        for (int i = 0; i < taskList.size(); i++) {
            Task currentTask = taskList.get(i);
            if (!currentTask.getDescription().contains(lookupString)) {
                continue;
            }
            if (i != 0) {
                outputString = outputString.concat("\n" + (i + 1) + ": " + currentTask);
            } else {
                outputString = outputString.concat((i + 1) + ": " + currentTask);
            }
        }


        return outputString;

    }

    /**
     * Returns the size of the tasklist.
     *
     * @return size of the tasklist
     */
    public int size() {
        return this.taskList.size();
    }

    /**
     * Sorts taskList according to user specification.
     *
     * @param sortTaskType Task type to sort
     * @param sortBy Sort method
     */
    public void sort(String sortTaskType, String sortBy) {
        sortBy = sortBy.toUpperCase();
        sortTaskType = sortTaskType.toUpperCase();

        switch (sortTaskType) {
        case "TODO":
            sortToDo(sortBy);
            break;
        case "DEADLINE":
            sortDeadline(sortBy);
            break;
        case "EVENT":
            sortEvent(sortBy);
            break;
        case "ALL":
            sortAll(sortBy);

        }

    }

    /**
     * Sorts ToDo tasks only and adds them to front of taskList.
     *
     * @param sortBy sort method.
     */
    public void sortToDo(String sortBy) {
        ArrayList<Task> arrayToSort = new ArrayList<>();
        for (int i = 0; i < taskList.size(); i++) {
            Task currTask = taskList.get(i);
            if (currTask instanceof ToDo) {
                arrayToSort.add(currTask);
            }
        }

        for (int i = 0; i < arrayToSort.size(); i++) {
            Task currTask = arrayToSort.get(i);
            taskList.remove(currTask);
        }

        switch (sortBy) {
        case "ALPHABETICALLY":
            arrayToSort.sort(Comparator.comparing(Task::getDescription));
            break;
        case "CHRONOLOGICALLY":
            arrayToSort.sort(Comparator.comparing(Task::getCompareDate));
        default:

        }

        arrayToSort.addAll(taskList);
        taskList = arrayToSort;
    }

    /**
     * Sorts Deadline tasks only and adds them to front of taskList.
     *
     * @param sortBy sort method.
     */
    private void sortDeadline(String sortBy) {
        ArrayList<Task> arrayToSort = new ArrayList<>();
        for (int i = 0; i < taskList.size(); i++) {
            Task currTask = taskList.get(i);
            if (currTask instanceof Deadline) {
                arrayToSort.add(currTask);
            }
        }

        for (int i = 0; i < arrayToSort.size(); i++) {
            Task currTask = arrayToSort.get(i);
            taskList.remove(currTask);
        }

        switch (sortBy) {
        case "ALPHABETICALLY":
            arrayToSort.sort(Comparator.comparing(Task::getDescription));
            break;
        case "CHRONOLOGICALLY":
            arrayToSort.sort(Comparator.comparing(Task::getCompareDate));
        default:

        }

        arrayToSort.addAll(taskList);
        taskList = arrayToSort;
    }

    /**
     * Sorts Event tasks only and adds them to front of taskList.
     *
     * @param sortBy sort method.
     */
    private void sortEvent(String sortBy) {
        ArrayList<Task> arrayToSort = new ArrayList<>();
        for (int i = 0; i < taskList.size(); i++) {
            Task currTask = taskList.get(i);
            if (currTask instanceof Event) {
                arrayToSort.add(currTask);
            }
        }

        for (int i = 0; i < arrayToSort.size(); i++) {
            Task currTask = arrayToSort.get(i);
            taskList.remove(currTask);
        }

        switch (sortBy) {
        case "ALPHABETICALLY":
            arrayToSort.sort(Comparator.comparing(Task::getDescription));
            break;
        case "CHRONOLOGICALLY":
            arrayToSort.sort(Comparator.comparing(Task::getCompareDate));
        default:

        }

        arrayToSort.addAll(taskList);
        taskList = arrayToSort;
    }

    /**
     * Sorts all tasks.
     *
     * @param sortBy sort method.
     */
    private void sortAll(String sortBy) {

        switch (sortBy) {
        case "ALPHABETICALLY":
            taskList.sort(Comparator.comparing(Task::getDescription));
            break;
        case "CHRONOLOGICALLY":
            taskList.sort(Comparator.comparing(Task::getCompareDate));
        default:

        }
    }
}
