package trackerbot.task;

import java.util.ArrayList;
import java.util.HashSet;

import trackerbot.command.CommandType;
import trackerbot.exception.TrackerBotException;
import trackerbot.utils.Parser;

/**
 * Collection of Tasks for use in TrackerBot.
 *
 * @author WZWren
 * @version A-CodeQuality
 */
public class TaskList {
    /**
     * The ArrayList of Tasks to store in the TaskList.
     */
    private final ArrayList<Task> tasks;

    public TaskList() {
        tasks = new ArrayList<>();
    }

    /**
     * Attempts to add a new Task into the list.
     *
     * @param type The enumerated type of Command to add into the Task.
     * @param commandField The description of the Command.
     * @return The reply String to be passed into Ui.
     * @throws TrackerBotException if the addition of the task encounters an error during parse.
     * @see trackerbot.utils.Parser#parseAdd
     */
    public String addTask(CommandType type, String commandField) throws TrackerBotException {
        Task newTask = Parser.parseAdd(type, commandField);
        tasks.add(newTask);
        return "I am tracking this task now:\n  " + newTask.toString();
    }

    /**
     * Attempts to toggle the item at index as complete.
     *
     * @param index The index of the Task in the TaskList, starting from 1.
     * @return The reply String to be passed into Ui.
     * @throws TrackerBotException if the Task specified does not exist, or is
     *                             already completed.
     */
    public String markTask(int index) throws TrackerBotException {
        Task task = getTask(index);
        task.markTask();
        return "This task has been marked as completed.\n  " + task;
    }

    /**
     * Attempts to toggle a collection of tasks in the list as complete.
     *
     * @param indexes The Set of all indexes to mark in the list.
     * @return The reply String to be passed into Ui.
     */
    public String markTasks(HashSet<Integer> indexes, StringBuilder errorLog) {
        ArrayList<Task> markedTasks = new ArrayList<>();
        indexes.iterator().forEachRemaining((index) -> {
            try {
                Task task = getTask(index);
                task.markTask();
                markedTasks.add(task);
            } catch (TrackerBotException e) {
                errorLog.append("\n");
                errorLog.append(index);
                errorLog.append(" - ");
                errorLog.append(e.getMessage());
            }
        });

        return "These tasks have been marked as completed:\n\n"
                + getListOfTasks(markedTasks) + "\n\n";
    }

    /**
     * Attempts to toggle the item at index as incomplete.
     *
     * @param index The index of the Task in the TaskList, starting from 1.
     * @return The reply String to be passed into Ui.
     * @throws TrackerBotException if the Task specified does not exist, or is
     *                             still incompleted.
     */
    public String unmarkTask(int index) throws TrackerBotException {
        Task task = getTask(index);
        task.unmarkTask();
        return "The task has been marked as incomplete.\n  " + task;
    }

    /**
     * Attempts to toggle a collection of tasks in the list as incomplete.
     *
     * @param indexes The Set of all indexes to unmark in the list.
     * @return The reply String to be passed into Ui.
     */
    public String unmarkTasks(HashSet<Integer> indexes, StringBuilder errorLog) {
        ArrayList<Task> unmarkedTasks = new ArrayList<>();
        indexes.iterator().forEachRemaining((index) -> {
            try {
                Task task = getTask(index);
                task.unmarkTask();
                unmarkedTasks.add(task);
            } catch (TrackerBotException e) {
                errorLog.append("\n");
                errorLog.append(index);
                errorLog.append(" - ");
                errorLog.append(e.getMessage());
            }
        });

        return "These tasks have been marked as incomplete:\n\n"
                + getListOfTasks(unmarkedTasks) + "\n\n";
    }

    /**
     * Attempts to delete the item at index.
     *
     * @param index The index of the Task in the TaskList, starting from 1.
     * @return The reply String to be passed into Ui.
     * @throws TrackerBotException if the Task specified does not exist.
     */
    public String deleteTask(int index) throws TrackerBotException {
        Task task = getTask(index);
        tasks.remove(index - 1);
        return "I have removed this task off of my list.\n  " + task + "\n"
                + tasks.size() + " task(s) remain on my list.";
    }

    /**
     * Attempts to delete a collection of tasks from the list.
     *
     * @param indexes The Set of all indexes to remove from the list.
     * @return The reply String to be passed into Ui.
     */
    public String deleteTasks(HashSet<Integer> indexes, StringBuilder errorLog) {
        ArrayList<Task> tasksToDelete = new ArrayList<>();
        indexes.iterator().forEachRemaining((index) -> {
            try {
                tasksToDelete.add(getTask(index));
            } catch (TrackerBotException e) {
                errorLog.append("\n");
                errorLog.append(index);
                errorLog.append(" - ");
                errorLog.append(e.getMessage());
            }
        });

        tasks.removeAll(tasksToDelete);
        return "I have removed these tasks off of my list:\n\n"
                + getListOfTasks(tasksToDelete) + "\n"
                + tasks.size() + " task(s) remain on my list.\n\n";
    }

    /**
     * Deletes all items in the TaskList.
     */
    public void clear() {
        tasks.clear();
    }

    /**
     * Finds all Tasks matching the query, along with the associated index in the list.
     *
     * @param searchStr The query to match at any point in the description of the Task.
     * @return A String representation of all the Tasks matching the query, in list form.
     *         If no Tasks match the query, returns a String indicating no matches.
     */
    public String findAll(String searchStr) {
        StringBuilder result = new StringBuilder();

        for (int i = 1; i < tasks.size() + 1; i++) {
            if (tasks.get(i - 1).doesContain(searchStr)) {
                result.append(i);
                result.append(". ");
                result.append(tasks.get(i - 1).toString());
                result.append("\n");
            }
        }

        if (result.length() == 0) {
            return "No results match your search.";
        }

        // delete the overflow \n from the last append operation
        if (result.charAt(result.length() - 1) == '\n') {
            result.deleteCharAt(result.length() - 1);
        }

        return result.toString();
    }

    /**
     * Returns a UI-friendly String representation of the Task List.
     *
     * @return A String representation of the Task List, to pass directly into Ui.
     */
    public String list() {
        if (tasks.size() == 0) {
            return "No tasks have been added to the list yet.";
        }
        return "I am tracking these tasks:\n" + getListOfTasks(tasks);
    }

    /**
     * Adds the Task directly into TaskList.
     *
     * <p>This is a method used by Storage.</p>
     * @param task The Task to add into TaskList.
     */
    public void importSave(Task task) {
        tasks.add(task);
    }

    /**
     * Exports all items in the TaskList into a save-compatible String form.
     *
     * @return The String representation of all the Tasks in the TaskList, in
     *         a save-compatible form.
     */
    public String exportSave() {
        StringBuilder result = new StringBuilder();
        for (int i = 1; i < tasks.size() + 1; i++) {
            result.append(tasks.get(i - 1).toSaveString());
            result.append("\n");
        }
        return result.toString();
    }

    /**
     * Gets the Task at the provided index.
     *
     * @param index The index of the list to check.
     * @return The Task object at the index, if it exists, and null otherwise.
     */
    private Task getTask(int index) throws TrackerBotException {
        if (index <= 0 || index > tasks.size()) {
            throw new TrackerBotException("The specified task does not exist.");
        }
        return tasks.get(index - 1);
    }

    /**
     * Gets the String representation of the TaskList.
     *
     * @return the String representation of the TaskList
     */
    private String getListOfTasks(ArrayList<Task> tasks) {
        StringBuilder result = new StringBuilder();

        for (int i = 1; i < tasks.size() + 1; i++) {
            result.append(i);
            result.append(". ");
            result.append(tasks.get(i - 1).toString());
            if (i != tasks.size()) {
                result.append("\n");
            }
        }

        return result.toString();
    }
}
