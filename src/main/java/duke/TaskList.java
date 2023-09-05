package duke;

import java.util.ArrayList;

import duke.exceptions.DukeException;
import duke.task.Task;

/**
 * contains the list of tasks, and provides methods to edit the list and interact with storage/hard drive
 */
public class TaskList {
    /**
     * ArrayList of tasks
     */
    private ArrayList<Task> tasks = new ArrayList<>();
    /**
     * constructor
     */
    public TaskList() {
    }

    /**
     * saves current tasks to hard drive
     */
    public void saveList() {
        Storage.saveTask(tasks);
    }

    /**
     * loads tasks from hard drive into tasks, prints a message if there
     * are no tasks found
     */
    public void updateFromStorage() {
        try {
            tasks = Storage.loadTasks();
        } catch (DukeException e) {
            Ui.print("No tasks found in storage, starting new list");
        }
    }

    /**
     * prints the list of task in their string format,
     * each task starts on a new line
     * @throws DukeException if there are no tasks in the list
     */
    public void printList() throws DukeException {
        if (tasks.size() == 0) {
            throw new DukeException("There are no tasks yet");
        }
        Ui.printArrayList(tasks);
    }

    /**
     * add a task to the current list
     * @param task task to be added
     */
    public void addTask(Task task) {
        tasks.add(task);
        Ui.print("Got it. I've added this task: \n" + task + "\nnow you have "
                + tasks.size() + " tasks in the list");
    }

    /**
     * deletes a task from the list
     * @param i index of task which is to be deleted
     * @throws DukeException if index is out of range or there are no tasks to delete
     */
    public void deleteTask(int i) throws DukeException {
        if (tasks.size() <= 0) {
            throw new DukeException("There are no tasks to delete");
        } else if (tasks.size() < i) {
            throw new DukeException("Task.Task index out of range");
        }
        Task deleted = tasks.remove(i);
        Ui.print("Noted. I've removed this task:\n" + deleted + "\nNow you have "
                + tasks.size() + " tasks in the list");
    }

    /**
     * marks a task at an index as done
     * @param i index of task to be marked as done
     * @throws DukeException if task is already done
     */
    public void markTask(int i) throws DukeException {
        tasks.get(i).mark();
    }

    /**
     * unmarks task at an index as undone
     * @param i index of task which is to be marked undone
     * @throws DukeException if task is still undone
     */
    public void unmarkTask(int i) throws DukeException {
        tasks.get(i).unmark();
    }

    /**
     * getter for task from list
     * @param i index of task to be gotten
     * @return task at index i
     */
    public Task getTask(int i) {
        return tasks.get(i);
    }

    /**
     * find tasks containing a certain word
     * @param word word to look for
     * @return ArrayList of Tasks containing the word
     */
    public ArrayList<Task> find(String word) {
        String wordToFind = word.split(" ")[1];
        ArrayList<Task> result = new ArrayList<>();
        for (Task task : tasks) {
            if (task.getDescription().contains(wordToFind)) {
                result.add(task);
            }
        }
        return result;
    }
}
