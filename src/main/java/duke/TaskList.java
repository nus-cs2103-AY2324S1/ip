package duke;

import java.util.ArrayList;
import java.util.Collections;

import task.Deadline;
import task.Event;
import task.Task;
import utils.DeadlineSortingComparator;
import utils.EventSortingComparator;

/**
 * TaskList represent the list of tasks of the user.
 */
public class TaskList {
    private static ArrayList<Task> taskList;

    /**
     * The constructor of TaskList.
     *
     * @param taskList The ArrayList of tasks of the user.
     */
    public TaskList(ArrayList<Task> taskList) {
        this.taskList = taskList;
    }

    /**
     * Marks the specific task in the user's list of tasks as completed.
     *
     * @param num The index of the task which is to be mark as completed.
     * @return The message of paimonbot to indicate the task is marked.
     */
    public Task markTask(int num) {
        Task t = taskList.get(num - 1);
        t.markDone();
        return t;
    }

    /**
     * Marks the specific task in the user's list of tasks as incomplete.
     *
     * @param num The index of the task which is to be mark as incomplete.
     * @return The message of paimonbot to indicate the task is unmarked.
     */
    public Task unmarkTask(int num) {
        Task t = taskList.get(num - 1);
        t.markUndone();
        return t;
    }

    /**
     * Adds the task into the user's list of tasks.
     *
     * @param task The task to be added.
     */
    public void addTask(Task task) {
        this.taskList.add(task);
    }

    /**
     * Deletes the specific task from the user's list of tasks.
     *
     * @param num The index of the task to be deleted.
     * @return The message of paimonbot to indicate the task is deleted.
     */
    public Task deleteTask(int num) {
        Task deletedTask = taskList.remove(num - 1);
        return deletedTask;
    }

    /**
     * Finds the tasks in the list of tasks with the given keyword.
     *
     * @param keyword The keyword the user wants to search for in the tasks.
     * @return The message of paimonbot to indicate the tasks with the keyword given.
     */
    public ArrayList<Task> findTask(String keyword) {
        ArrayList<Task> matchingTasks = new ArrayList<>();
        if (taskList.isEmpty()) {
            return matchingTasks;
        } else {
            for (Task t : this.taskList) {
                String taskString = t.toString();
                if (taskString.contains(keyword)) {
                    matchingTasks.add(t);
                }
            }
            return matchingTasks;
        }
    }

    /**
     * To sort the Deadline objects in ascending order of their LocalDateTimes.
     *
     * @return The sorted ArrayList of Deadline objects.
     */
    public ArrayList<Deadline> sortDeadlines() {
        ArrayList<Deadline> sortedDeadlines = getDeadlineList();
        Collections.sort(sortedDeadlines, new DeadlineSortingComparator());
        return sortedDeadlines;
    }

    /**
     * To sort the Event objects in ascending order of their LocalDateTimes.
     *
     * @return The sorted ArrayList of Event objects.
     */
    public ArrayList<Event> sortEvents() {
        ArrayList<Event> sortedEvents = getEventList();
        Collections.sort(sortedEvents, new EventSortingComparator());
        return sortedEvents;
    }

    /**
     * Get the list of Deadline objects from the ArrayList of Tasks.
     *
     * @return The list of Deadline objects.
     */
    public ArrayList<Deadline> getDeadlineList() {
        ArrayList<Deadline> deadlineList = new ArrayList<>();
        for (Task task : taskList) {
            if (task instanceof Deadline) {
                Deadline deadline = (Deadline) task;
                deadlineList.add((deadline));
            }
        }
        return deadlineList;
    }

    /**
     * Get the list of Event objects from the ArrayList of Tasks.
     *
     * @return The list of Event objects.
     */
    public ArrayList<Event> getEventList() {
        ArrayList<Event> eventList = new ArrayList<>();
        for (Task task : taskList) {
            if (task instanceof Event) {
                Event event = (Event) task;
                eventList.add(event);
            }
        }
        return eventList;
    }

    /**
     * To find the size of the ArrayList of Task objects.
     *
     * @return The size of the ArrayList of Task objects.
     */
    public int size() {
        return this.taskList.size();
    }

    /**
     * A getter function to get the user's current list of tasks.
     *
     * @return The user's current list of tasks.
     */
    public static ArrayList<Task> getTaskList() {
        return taskList;
    }
}
