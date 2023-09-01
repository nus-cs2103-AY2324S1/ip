package duke;

import java.util.ArrayList;
import java.util.Collection;

/**
 * List of tasks.
 */
public class StoreList {

    /** The list of task. */
    ArrayList<Task> list;

    /** Creates a new list of tasks. */
    public StoreList() {
        this.list = new ArrayList<>();
    }

    /**
     * Adds task corresponding to the Commands enum value and a string representing the description of the task.
     * If a task cannot be created from the given information, it returns an error message.
     *
     * @param type The Commands enum value representing the type of task to be added.
     * @param description The description of the task to be added.
     * @return A string representing the result of adding the task to the list or an error if unsuccessful.
     */
    String add(Commands type, String description) {
        try {

            Task task = Task.create(type, description);
            list.add(task);
            return String.format(
                    "added: %s\nYou have %d tasks.",
                    task,
                    list.size()
            );
        } catch (DukeException e) {
            return e.toString();
        }
    }

    /**
     * This method takes a Task object and adds it to the list.
     *
     * @param task The Task object to be added to the list.
     * @return A string representing the result of adding the task to the list.
     */
    public String add(Task task) {
        list.add(task);
        return String.format(
                "added: %s\nYou have %d tasks.",
                task,
                list.size()
        );
    }

    /**
     * Takes collection of Task objects and adds them all to the list.
     *
     * @param tasks The collection of Task objects to be added to the list.
     */
    public void addTasks(Collection<? extends Task> tasks) {
        list.addAll(tasks);
    }

    /**
     * Marks the task at the string representation of the position of the task.
     * If the position is not a valid integer, it returns an error message.
     * If the position is out of bounds, it returns an error message.
     *
     * @param position The position of the task in the list as a string.
     * @return A string representing the result of marking the task as done.
     */
    String markDone(String position) {
        try {
            int index = Integer.parseInt(position) - 1;
            Task task = list.get(index);
            task.markAsDone();
            return String.format("Nice! You have completed the task:\n    %s", task);
        } catch (NumberFormatException e) {
            return "Err: Index provided is not an integer";
        } catch (IndexOutOfBoundsException e) {
            return "Err: Index provided is out of position of the list";
        }
    }

    /**
     * Unmarks the task at the string representation of the position of the task.
     * If the position is not a valid integer, it returns an error message.
     * If the position is out of bounds, it returns an error message.
     *
     * @param position The position of the task in the list as a string.
     * @return A string representing the result of marking the task as not done.
     */
    String markUndone(String position) {
        try {
            int index = Integer.parseInt(position) - 1;
            Task task = list.get(index);
            task.markAsNotDone();
            return String.format("Ok! duke.Task marked undone:\n    %s", task);
        } catch (NumberFormatException e) {
            return "Err: Index provided is not an integer";
        } catch (IndexOutOfBoundsException e) {
            return "Err: Index provided is out of position of the list";
        }
    }

    /**
     * Deletes task based on the position from parsing the string.
     * If the position is not a valid integer, it returns an error message.
     * If the position is out of bounds, it returns an error message.
     *
     * @param position The position of the task in the list as a string.
     * @return A string representing the result of removing the task from the list.
     */
    String delete(String position) {
        try {
            int index = Integer.parseInt(position) - 1;
            Task task = list.remove(index);
            return String.format(
                    "removed: %s\nYou have %d tasks.",
                    task,
                    list.size()
            );
        } catch (NumberFormatException e) {
            return "Err: Index provided is not an integer";
        } catch (IndexOutOfBoundsException e) {
            return "Err: Index provided is out of position of the list";
        }
    }

    /**
     * Gives a string representation of the list of tasks in display format.
     *
     * @return A string representation of the list of tasks in display format.
     */
    @Override
    public String toString() {
        if (list.size() == 0) {
            return "You have no tasks :).";
        }
        String result = "";
        for (int i = 0; i < list.size(); i++) {
            result += String.format("    %d. %s\n", i + 1, list.get(i));
        }
        return result;
    }

    /**
     * Gives representation of the list of tasks in file format.
     *
     * @return A string representation of the list of tasks in file format.
     */
    public String showSaveText() {
        String saveText = "";
        for (int i = 0; i < list.size(); i++) {
            saveText += list.get(i).fileString() + (i + 1 == list.size() ? "" : "\n");
        }
        return saveText;
    }
}