package duke.tasks;

import java.util.ArrayList;

import duke.exceptions.DukeException;
import duke.exceptions.InvalidNumberException;
import duke.exceptions.NoSuchEntryException;

import java.time.LocalDate;
import java.time.LocalTime;

public class TaskList {
    protected ArrayList<Task> list;

    public TaskList() {
        this.list = new ArrayList<Task>();
    }

    /**
     * Adds a Todo to the list.
     * 
     * @param description the description of the todo.
     * @param isDone true if todo is done, false otherwise.
     * @return the string format of the newly added Todo in the list to display to
     *         the user.
     */
    public String add(String description, boolean isDone) {
        Todo todo = new Todo(description, isDone);
        list.add(todo);
        return todo.toString();
    }

    /**
     * Adds a Deadline to the list.
     *
     * @param description the description of the deadline. Cannot be empty.
     * @param isDone true if the deadline is done, false otherwise.
     * @param byDate the date to use for the deadline. Will not be displayed if null.
     * @param byTime the time to use for the deadline. Will not be displayed if null.
     * @return the string format of the newly added Deadline in the list to display
     *         to the user.
     */
    public String add(String description, boolean isDone, LocalDate byDate, LocalTime byTime) {
        Deadline deadline = new Deadline(description, isDone, byDate, byTime);
        list.add(deadline);
        return deadline.toString();
    }

    /**
     * Adds an Event to the list.
     * 
     * @param description the description of the deadline. Cannot be empty.
     * @param isDone true if the deadline is done, false otherwise.
     * @param fromDate the date to use for the event start. Will not be displayed if null.
     * @param fromTime the time to use for the event start. Will not be displayed if null.
     * @param toDate the date to use for the event end. Will not be displayed if null.
     * @param toTime the time to use for the event end. Will not be displayed if null.
     * @return the string format of the newly added Event in the list to display to
     *         the user.
     */
    public String add(String description, boolean isDone, LocalDate fromDate, LocalTime fromTime, LocalDate toDate,
            LocalTime toTime) {
        Event event = new Event(description, isDone, fromDate, fromTime, toDate, toTime);
        list.add(event);
        return event.toString();
    }

    /**
     * Sets or unsets task as done at the specified index.
     * 
     * @param isDone true to mark the task as done, false to unmark it.
     * @param index the index of the task.
     * @return a string describing the status of the operation to display to the
     *         user.
     * @throws DukeException if the operation is not successful or the index is
     *                       invalid.
     */
    public String setDone(boolean isDone, int index) throws DukeException {
        try {
            if (index < 0 || index + 1 > list.size()) {
                throw new NoSuchEntryException();
            } else {
                // Mark this task as done.
                if (isDone) {
                    if (list.get(index).isDone) {
                        return "This task is already marked as done!\n" + list.get(index).toString();
                    } else {
                        list.get(index).setDone(true);
                        return "This task is now marked as done!\n" + list.get(index).toString();
                    }
                } else {
                    // Mark this task as not done.
                    if (!list.get(index).isDone) {
                        return "This task is already marked as not done!\n" + list.get(index).toString();
                    } else {
                        list.get(index).setDone(false);
                        return "This task is now marked as not done!\n" + list.get(index).toString();
                    }
                }
            }
        } catch (NumberFormatException e) {
            throw new InvalidNumberException();
        }
    }

    /**
     * Deletes the entry at the specified index. This method is called by Duke to
     * delete an entry from the list.
     * 
     * @param index index of the entry to delete.
     * @return a String with information about the operation that was performed.
     * @throws DukeException if the operation was not successful or index was
     *                       invalid.
     */
    public String delete(int index) throws DukeException {
        try {
            if (index < 0 || index + 1 > list.size()) {
                throw new NoSuchEntryException();
            } else {
                String tempString = list.get(index).toString();
                list.remove(index);
                return String.format("I've removed this task:\n%s\nNow you have %d task(s) in the list.", tempString,
                        list.size());
            }
        } catch (NumberFormatException e) {
            throw new InvalidNumberException();
        }

    }

    /**
     * Returns a string containing all the tasks with the given description.
     * 
     * @return a string representation of the list in the form of a list of Task
     *         objects separated by new lines.
     */
    public String find(String description) throws DukeException {
        if (!description.equals("")) {
            String returnString = new String("");
            int i = 0;
            for (Task entry : list) {
                if (entry.description.contains(description)) {
                    returnString += (entry.toString() + "\n");
                    i++;
                }
            }
            // Returns a string that contains the task description and a summary of the valid tasks.
            if (!returnString.equals("")) {
                return (returnString + String.format("There were %d tasks containing %s.", i, description));
            }
        }
        return String.format("There were no tasks containing %s.", description);
    }
    
    /**
     * Returns a string representation of the list of all tasks to display to the
     * user.
     * 
     * @return a string representation of the list in the form of a list of Task
     *         objects separated by new lines.
     */
    public String toString() {
        String returnString = new String("");
        int i = 1;
        for (Task entry : list) {
            returnString += (i + "." + entry.toString() + "\n");
            i++;
        }
        returnString += String.format("You have %d task(s) in the list.", i - 1);
        return returnString;
    }

    public boolean isEmpty() {
        return list.size() == 0;
    }

    
    /**
     * Removes and retreives the first item in the list to save to file.
     * 
     * @return String containing the file
     */
    public String clearList() {
        String tempString = list.get(0).toFileString();
        list.remove(0);
        return tempString;
    }

    public int getSize() {
        return list.size();
    }
}
