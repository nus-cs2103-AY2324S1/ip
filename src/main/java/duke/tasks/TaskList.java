package duke.tasks;


import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.stream.Collectors;

import duke.exceptions.NotFoundException;

/**
 * Stores the list of tasks that the user has.
 * Provides additional methods for operating on the tasks.
 */
public class TaskList {
    private ArrayList<Task> list;

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
        return this.list.stream()
                .filter(a -> a.getName().contains(searchString)).collect(Collectors.toCollection(ArrayList::new));
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

    public String getTasksAsText(SortType sortType, SortOrder sortOrder) {
        // assume ASC by default
        ArrayList<Task> result = new ArrayList<>();

        result = getTasks(sortType, sortOrder, result);


        StringBuilder resultMsg = new StringBuilder();

        for (int i = 0; i < result.size(); i++) {
            Task task = result.get(i);
            resultMsg.append(task);
            resultMsg.append("\n");
        }


        return resultMsg.toString();
    }

    private ArrayList<Task> getTasks(SortType sortType, SortOrder sortOrder, ArrayList<Task> result) {
        switch (sortType) {
        case ID: {
            // default case: sorted by ID
            // don't do anything
            result = getTasksSortedByID();
            break;
        }
        case NAME: {
            // sort by name
            result = getTasksSortedByName();
            break;
        }
        case TYPE: {
            result = getTasksSortedByType();
            break;
        }
        case DEADLINE: {
            result = getTasksSortedByDeadline();


            break;
        }
        default: {
            System.out.println("meh");
        }
        }

        if (sortOrder == SortOrder.DESC) {
            // reverse the array
            Collections.reverse(result);
        }
        return result;
    }

    private ArrayList<Task> getTasksSortedByDeadline() {
        // sort by todos first, then events and deadlines by the start time
        ArrayList<Task> result = new ArrayList<>();
        ArrayList<TimedTask> nonTodos = new ArrayList<>();
        for (Task t : this.list) {
            if (t instanceof TimedTask) {
                nonTodos.add((TimedTask) t);
            } else {
                result.add(t);
            }
        }

        Collections.sort(nonTodos);
        result.addAll(nonTodos);

        return result;
    }

    private ArrayList<Task> getTasksSortedByType() {
        ArrayList<Task> result = new ArrayList<>();
        // sort by type: todos first, then deadlines, then events
        for (Task t : this.list) {
            if (t instanceof TodoTask) {
                result.add(t);
            }
        }
        for (Task t : this.list) {
            if (t instanceof DeadlineTask) {
                result.add(t);
            }
        }
        for (Task t : this.list) {
            if (t instanceof EventTask) {
                result.add(t);
            }
        }

        return result;
    }

    private ArrayList<Task> getTasksSortedByName() {
        ArrayList<Task> result;
        result = new ArrayList<>(this.list);
        result.sort(Comparator.comparing(Task::getName));
        return result;
    }

    private ArrayList<Task> getTasksSortedByID() {
        ArrayList<Task> result;
        result = new ArrayList<>(this.list);
        return result;
    }

}
