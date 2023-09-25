package chatbot.task;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.Set;

import chatbot.exceptions.InvalidDescriptionException;
import chatbot.exceptions.InvalidIndexException;

/**
 * Representation of a list that takes in tasks,
 * and is able to modify their states
 *
 * @author Owen Yeo
 */
public class TaskList {

    //ArrayList to store the tasks
    private ArrayList<Task> tasks = new ArrayList<>();

    /**
     * Constructor for an isntance of TaskList
     *
     * @param tasks ArrayList for tasks
     */
    public TaskList(ArrayList<Task> tasks) {
        this.tasks = tasks;
    }

    /**
     * Empty constructor for a TaskList
     */
    public TaskList() {
        this.tasks = new ArrayList<>();
    }

    /**
     * Adds a task to the TaskList
     *
     * @param taskString representing the descriptor for the task
     * @param taskType type of the task getting added
     * @throws InvalidDescriptionException
     */
    public void addTask(String taskString, TaskType taskType)
            throws InvalidDescriptionException {
        if (taskString == "") {
            throw new InvalidDescriptionException(
                "What? Where's your label? Stop this.");
        }

        assert taskString.length() > 0 : "Task description cannot be empty";

        switch(taskType) {
        case TODO:
            tasks.add(new ToDo(taskString));
            break;

        case DEADLINE:
            try {
                String[] deadlineParts = taskString.split("/by");

                assert deadlineParts.length > 1 : "Deadline cannot be empty";

                tasks.add(new Deadline(deadlineParts[0].trim(), deadlineParts[1].trim()));
            } catch (IndexOutOfBoundsException e) {
                throw new InvalidDescriptionException(
                    "Are you stupid? Can you follow instructions?");
            }
            break;

        case EVENT:
            try {
                String[] eventParts = taskString.split("/from");

                assert eventParts.length > 1 : "Event fromtime cannot be empty";

                String eventLabel = eventParts[0];
                String[] eventParts2 = eventParts[1].split("/to");

                assert eventParts2.length > 1 : "Event totime cannot be empty";

                tasks.add(new Event(eventLabel.trim(), eventParts2[0].trim(), eventParts2[1].trim()));
            } catch (IndexOutOfBoundsException e) {
                throw new InvalidDescriptionException(
                    "Are you stupid? Can you follow instructions?");
            }
            break;

        default:
            break;
        }
    }

    /**
     * Detects if there are duplicate tasks in the list.
     * @param labelString the label of the task to check for duplicates.
     * @return true if there are duplicates, false otherwise.
     */
    public boolean detectDuplicate(String labelString) {
        for (int i = 0; i < tasks.size() - 1; i++) {
            if (tasks.get(i).getLabel().equals(labelString)) {
                return true;
            }
        }
        return false;
    }

    /**
     * Gets the duplicate tasks in the list.
     * @return ArrayList of duplicate tasks.
     */
    public ArrayList<Task> getDuplicateTasks() {
        ArrayList<Task> duplicateTasks = new ArrayList<>();
        Set<String> uniqueTasks = new HashSet<>();
        for (Task task : tasks) {
            if (!uniqueTasks.add(task.getLabel())) {
                duplicateTasks.add(task);
            }
        }
        return duplicateTasks;
    }

    /**
     * Deletes the duplicate tasks in the list.
     */
    public void deleteDuplicates() {
        ArrayList<Task> duplicateTasks = getDuplicateTasks();
        for (Task task : duplicateTasks) {
            tasks.remove(task);
        }
    }

    /**
     *  Deletes the item off the list.
     *
     * @param listNum Index of the item of the list to delete.
     */
    public void delete(int listNum) {
        int index = listNum - 1;
        tasks.remove(index);
    }

    /**
     * To mark tasks as done.
     *
     * @param listNum the index of the item on the list to mark.
     */
    public void mark(int listNum) throws InvalidIndexException {
        Task task = tasks.get(listNum - 1);
        task.done();
    }

    /**
      * To unmark a list item as undone.
      *
      * @param listNum Index of the item on the list to unmark.
      */
    public void unmark(int listNum) throws InvalidIndexException {
        Task task = tasks.get(listNum - 1);
        task.undone();
    }

    /**
     * To get the list of tasks.
     *
     * @return ArrayList of tasks.
     */
    public Task getTask(int listNum) {
        int index = listNum - 1;
        return tasks.get(index);
    }

    /**
     * To get the list of tasks.
     *
     * @return ArrayList of tasks.
     */
    public int getLength() {
        return tasks.size();
    }

}
