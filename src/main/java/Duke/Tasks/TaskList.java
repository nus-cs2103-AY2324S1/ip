package Duke.Tasks;

import java.io.*;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.Set;


import Duke.Exceptions.DuplicateInput;
import Duke.Storage;
import Duke.Exceptions.InvalidInput;
import Duke.Exceptions.IncompleteInput;

public class TaskList {

    private ArrayList<Task> storagePile;

    private Set<String> duplicateTasks = new HashSet<>();

    /**
     * Constructs a TaskList and loads tasks from storage.
     */
    public TaskList() {
        storagePile = Storage.loadTasks();
    }

    /**
     * Constructs an empty TaskList.
     *
     * @param test just to set it off.
     */
    public TaskList(String test) {
        storagePile = new ArrayList<>();
    }

    public String toString() {
        int length = storagePile.size();
        String formattedList = "";
        for (int i = 1; i <= length; i++) {
            formattedList += String.format("%s - %s",
                    i, storagePile.get(i-1)) +" \n" ;
        }
        return formattedList;
    }

    /**
     * Marks the task at the specified index as done.
     *
     * @param index The index of the task to mark as done.
     */
    public void markItemAsDone(int index) {
        storagePile.get(index - 1).markDone();
    }

    /**
     * Marks the task at the specified index as undone.
     *
     * @param index The index of the task to mark as undone.
     */
    public void markItemAsUndone(int index) {
        storagePile.get(index - 1).markUndone();
    }

    /**
     * Deletes the task at the specified index.
     *
     * @param index The index of the task to delete.
     */
    public void deleteItem(int index) {
        storagePile.remove(index - 1);
    }

    /**
     * Retrieves the task at the specified index.
     *
     * @param index The index of the task to retrieve.
     * @return The task at the specified index.
     */
    public Task getTask(int index) {
        return storagePile.get(index - 1);
    }

    /**
     * Gets the number of items in the storage pile.
     *
     * @return The number of items in the storage pile.
     */
    public int getNumOfItems() {
        return storagePile.size();
    }


    /**
     * Adds a task to the TaskList based on user input.
     *
     * @param item The user input representing a task.
     * @throws InvalidInput    If the input is invalid.
     * @throws IncompleteInput If the input is incomplete.
     */
    public void addTask(String item) throws InvalidInput, IncompleteInput, DuplicateInput {
        String firstWord = item.split(" ")[0];
        String[] listOfWords = item.split(" ");

        if (item.split(" ").length == 1) {
            if (firstWord.equals("todo") || firstWord.equals("deadline") || firstWord.equals("event")) {
                throw new IncompleteInput("Not finished");
            } else {
                throw new InvalidInput("Invalid");
            }
        }
        assert listOfWords.length >= 2;
        if (firstWord.equals("todo")) {
            String taskDesc = item.split(" ", 2)[1];
            Task t = new ToDoTask(taskDesc);
            if (duplicateTasks.contains(taskDesc)) {
                storagePile.add(t);
            } else {
                for (Task task : storagePile) {
                    if (task.equals(t)) {
                        duplicateTasks.add(taskDesc);
                        throw new DuplicateInput("Duplicate");
                    }
                }
                storagePile.add(t);
            }
        } else if (firstWord.equals("deadline")) {
            String taskDesc = item.split(" ", 2)[1];
            Task t = new DeadlineTask(taskDesc);
            if (duplicateTasks.contains(taskDesc)) {
                storagePile.add(t);
            } else {
                for (Task task : storagePile) {
                    if (task.equals(t)) {
                        duplicateTasks.add(taskDesc);
                        throw new DuplicateInput("Duplicate");
                    }
                }
                storagePile.add(t);
            }
        } else {
            String taskDesc = item.split(" ", 2)[1];
            Task t = new EventTask(taskDesc);
            if (duplicateTasks.contains(taskDesc)) {
                storagePile.add(t);
            } else {
                for (Task task : storagePile) {
                    if (task.equals(t)) {
                        duplicateTasks.add(taskDesc);
                        throw new DuplicateInput("Duplicate");
                    }
                }
                storagePile.add(t);
            }
        }
        Storage.saveTasks(this);
    }

    /**
     * Filters the TaskList and returns a new TaskList containing tasks with descriptions that match the provided description.
     *
     * @param description The description to filter tasks by.
     * @return A filtered TaskList containing matching tasks.
     */
    public TaskList filterTasksByDescription(String description) {
        TaskList filteredTasks = new TaskList("empty");
        for (Task task : this.storagePile) {
            assert task != null;
            if (task.matchesDescription(description.trim())) {
                filteredTasks.storagePile.add(task);
            }
        }
        return filteredTasks;
    }


    /**
     * Saves the tasks in the TaskList to a PrintWriter.
     * @param pw The PrintWriter to write tasks to.
     */
    public void saveTasksToFile(PrintWriter pw) {
        for (Task item : storagePile) {
            String str = item.toString();
            pw.println(str);
        }
    }

}