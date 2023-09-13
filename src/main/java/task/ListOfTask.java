package task;

import java.time.LocalDateTime;
import java.util.ArrayList;

import dukeExceptions.DukeException;
import storage.Storage;

/**
 * This class holds and controls all interactions with tasks and holds a list of them.
 */
public class ListOfTask {
    private ArrayList<Task> listOfTask = new ArrayList<>();

    /**
     * The size of the task list.
     *
     * @return Returns the size of the task list.
     */
    public int size() {
        return listOfTask.size();
    }


    private String addTask(Task task, boolean print) {
        assert(task != null);
        listOfTask.add(task);
        if (print) {
            Storage.save(listOfTask);
            return "added: " + task;
        }
        return null;
    }

    /**
     * Adds the ToDo task into the task list.
     *
     * @param task The task that is to be added.
     * @param print True to print messages, false to not print messages.
     * @return Returns a message string if print is true and null if false.
     */
    public String addToDo(String task, boolean print) {
        return addTask(new ToDo(task), print);
    }

    /**
     * Adds the Deadline task into the task list.
     *
     * @param task The task that is to be added.
     * @param dayDate The deadline of the task.
     * @param print True to print messages, false to not print messages.
     * @return Returns a message string if print is true and null if false.
     */
    public String addDeadline(String task, LocalDateTime dayDate, boolean print) {
        return addTask(new Deadline(task, dayDate), print);
    }

    /**
     * Adds the Event task into the task list.
     *
     * @param task The task that is to be added.
     * @param startDayDateTime The date and time of the start of the task.
     * @param endDayDateTime The date and time of the end of the task.
     * @param print True to print messages, false to not print message
     * @return Returns a message string if print is true and null if false.
     */
    public String addEvent(String task, LocalDateTime startDayDateTime,
            LocalDateTime endDayDateTime, boolean print) {
        return addTask(new Event(task, startDayDateTime, endDayDateTime), print);
    }

    /**
     * Prints out the list of task.
     *
     * @return Returns the list of tasks if there are tasks, and a message if there are no tasks.
     */
    public String listTasks() {
        String[] list = new String[1];
        assert(list[0] == null);
        int[] i = new int[1];
        i[0] = 1;
        listOfTask.forEach(x -> {
            if (list[0] == null) {
                list[0] = i[0] + "." + x + "\n";
            } else {
                list[0] += i[0] + "." + x + "\n";
            }
            i[0]++;
        });
        if (list[0] == null) {
            return "There is nothing in the list";
        }
        return list[0];
    }


    /**
     * Finds and prints index and tasks that contains the string str in its name.
     *
     * @param str The string that will be searched.
     * @return Returns a string of a list of tasks found and a message if nothing is found.
     */
    public String find(String str) {
        String[] list = new String[1];
        int[] foundCounter = new int[1];
        assert(foundCounter[0] == 0);
        int[] counter = new int[1];
        counter[0] = 1;

        listOfTask.forEach(task -> {
            if (task.getTaskDescription().contains(str)) {
                if (list[0] == null) {
                    list[0] = counter[0] + "." + task + "\n";
                } else {
                    list[0] += counter[0] + "." + task + "\n";
                }
                foundCounter[0]++;
            }
            counter[0]++;
        });

        if (foundCounter[0] == 0) {
            return "Whoopys uWu, sorry I couldnyt fynd any taysk that contyain that strying. XD uWu\n";
        }
        return list[0];
    }

    /**
     * Mark or unmark a task as done.
     *
     * @param index The index of the task based on the current task list.
     * @param markOrUnmark True to mark, false to unmark.
     * @param print True to print messages, false to not print messages.
     * @return Returns an empty string if print is false and a message if print is true.
     * @throws DukeException If the number is outside the range of indexes in the list.
     */
    public String markOrUnmark(int index, boolean markOrUnmark, boolean print) throws DukeException {
        try {
            if (markOrUnmark) {
                listOfTask.get(index - 1).setDone();
            } else {
                listOfTask.get(index - 1).setNotDone();
            }
            if (print) {
                Storage.save(listOfTask);
                return listOfTask.get(index - 1).toString();
            }
            return "";
        } catch (IndexOutOfBoundsException e) {
            throw new DukeException("Please select from index 1 to " + listOfTask.size());
        }
    }


    /**
     * Delete a task from the task list.
     *
     * @param index The index of the task based on the current task list.
     * @return Returns a message string if print is true and null if false.
     * @throws DukeException If the number is outside the range of indexes in the list.
     */
    public String delete(int index) throws DukeException {
        try {
            Task removed = listOfTask.remove(index - 1);
            Storage.save(listOfTask);
            return removed + " has been deleted";
        } catch (IndexOutOfBoundsException e) {
            throw new DukeException("Please select from index 1 to " + listOfTask.size());
        }
    }
}

