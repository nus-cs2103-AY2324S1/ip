package duke.tasks;

import duke.exceptions.NotFoundException;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

/**
 * Stores the list of tasks that the user has.
 * Provides additional methods for operating on the tasks.
 */
public class TaskList {
    private ArrayList<Task> list = new ArrayList<>();


    /**
     * The constructor for a basic TaskList.
     *
     * @param tasks The default tasks (if any) this should be initialised to
     */
    public TaskList(ArrayList<Task> tasks) {
        this.list = tasks;
    }

    /**
     * Adds an item to the list.
     *
     * @param task The user's task
     */
    public void addToList(Task task) {
        list.add(task);

    }

    /**
     * Removes an item from the list
     *
     * @param id the id of the task to remove.
     * @return the task that was removed
     */
    public Task removeFromList(int id) throws NotFoundException {
        int taskToDeleteIndex = -1;
        for (int i = 0; i < list.size(); i++) {
            if (list.get(i).getId() == id) {
                taskToDeleteIndex = i;
                break;
            }
        }
        if (taskToDeleteIndex == -1) {
            throw new NotFoundException();
        }
        Task task = list.get(taskToDeleteIndex);
        list.remove(taskToDeleteIndex);

        return task;
    }

    /**
     * Marks a specific task in the list as done.
     * Indexes start from 1, not 0
     *
     * @param id
     * @return the task that was modified
     */
    public Task markAsDone(int id) throws NotFoundException {

        for (Task t : this.list) {
            if (t.getId() == id) {
                t.setDone();
                return t;
            }
        }
        throw new NotFoundException();

    }

    /**
     * Marks a specific task in the list as undone.
     *
     * @param id
     * @return the task that was modified
     */
    public Task markAsUnDone(int id) throws NotFoundException {


        for (Task t : this.list) {
            if (t.getId() == id) {
                t.setUnDone();
                return t;
            }
        }
        throw new NotFoundException();
    }

    /**
     * Gets the number of non-null tasks in the list.
     *
     * @return integer representing the size
     */
    public int getSize() {
        return this.list.size();
    }

    /**
     * Gets all tasks.
     *
     */
    public ArrayList<Task> getTasks() {
        return this.list;
    }
    /**
     * Encodes the current Duke. Tasks in a string, each task separated by a newline.
     *
     * @return String representation
     */
    public String serialize() {
        StringBuilder stringBuilder = new StringBuilder();

        for (int i = 0; i < this.list.size(); i++) {
            Task task = this.list.get(i);
            stringBuilder.append(task.encodeTask());

            stringBuilder.append("\n");
        }


        return stringBuilder.toString();
    }

    /**
     * Finds the tasks that match the provided search string.
     *
     * @param searchString the string to match
     * @return tasks whose name contains the search string.
     */
    public ArrayList<Task> findTasksByName(String searchString) {
        List<Task> filtered = this.list.stream().filter(a -> a.getName().contains(searchString)).collect(Collectors.toList());

        return new ArrayList<>(filtered);

    }

    @Override
    public String toString() {
        StringBuilder resultMsg = new StringBuilder();

        for (int i = 0; i < list.size(); i++) {
            Task task = list.get(i);


            resultMsg.append(task);

            resultMsg.append("\n");


        }

        return resultMsg.toString();
    }

}
