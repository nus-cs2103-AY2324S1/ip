package trackerbot.task;

import trackerbot.command.CommandType;
import trackerbot.exception.TrackerBotException;
import trackerbot.task.Task;
import trackerbot.utils.Parser;

import java.util.ArrayList;

public class TaskList {
    /**
     * Task Array - as TrackerBot is not instantiated, this must be static.
     * The Task List array itself should be immutable, in case we override it
     * during runtime.
     */
    private final ArrayList<Task> TASKS = new ArrayList<>();

    public String markTask(int index) throws TrackerBotException {
        Task task = getTask(index);
        // happy path: the task does not exist.
        if (task == null) {
            throw new TrackerBotException("The specified task does not exist.");
        }

        if (!task.markTask()) {
            throw new TrackerBotException("The specified task is already completed.");
        }

        return "This task has been marked as completed.\n  " + task;
    }

    public String unmarkTask(int index) throws TrackerBotException {
        Task task = getTask(index);
        // happy path: the task does not exist.
        if (task == null) {
            throw new TrackerBotException("The specified task does not exist.");
        }

        // we can use an exception here to denote the task is completed
        if (!task.unmarkTask()) {
            throw new TrackerBotException("This task is already in progress.");
        }

        return "The task has been marked as incomplete.\n  " + task;
    }

    /**
     * Delete function for the app. <br>
     * Attempts to delete the item in the task list. If the Task does not exist,
     * prints an appropriate error message.
     * @param index The index of the list to unmark.
     */
    public String delete(int index) throws TrackerBotException {
        Task task = getTask(index);
        // happy path: the task does not exist.
        if (task == null) {
            throw new TrackerBotException("The specified task does not exist.");
        }

        TASKS.remove(index - 1);
        return "I have removed this task off of my list.\n  " + task + "\n"
                + TASKS.size() + " task(s) remain on my list.";
    }

    public String find(String searchStr) {
        StringBuilder result = new StringBuilder();

        for (int i = 1; i < TASKS.size() + 1; i++) {
            if (TASKS.get(i - 1).contains(searchStr)) {
                result.append(i);
                result.append(". ");
                result.append(TASKS.get(i - 1).toString());
                result.append("\n");
            }
        }

        if (result.length() == 0) {
            // we do not throw an error here - having no matches is a valid result from find.
            return "No results match your search.";
        }

        // delete the overflow \n from the last append operation
        if (result.charAt(result.length() - 1) == '\n') {
            result.deleteCharAt(result.length() - 1);
        }

        return result.toString();
    }

    /**
     * Function that adds a task to the app. <br>
     * Adds a To-Do, Event or Deadline task to the task list.
     * @param input The Pair&lt;Command, String&gt; of the task to add to the list.
     */
    public String add(CommandType type, String commandField) throws TrackerBotException {
        Task newTask = Parser.parseAdd(type, commandField);
        TASKS.add(newTask);
        return "I am tracking this task now:\n  " + newTask.toString();
    }

    public String list() {
        // happy path: prints an appropriate message and exit the method.
        if (TASKS.size() == 0) {
            return "No tasks have been added to the list yet.";
        }
        return "I am tracking these tasks:\n" + getListOfTasks();
    }

    public void importSave(Task task) {
        TASKS.add(task);
    }

    public void clear() {
        TASKS.clear();
    }

    public String exportSave() {
        StringBuilder result = new StringBuilder();
        for (int i = 1; i < TASKS.size() + 1; i++) {
            result.append(TASKS.get(i - 1).toSaveString());
            result.append("\n");
        }
        return result.toString();
    }

    /**
     * Gets the Task at the provided index.
     * @param index The index of the list to check.
     * @return The Task object at the index, if it exists, and null otherwise.
     */
    private Task getTask(int index) {
        // happy path: return null if out of bounds.
        if (index <= 0 || index > TASKS.size()) {
            return null;
        }
        return TASKS.get(index - 1);
    }

    private String getListOfTasks() {
        StringBuilder result = new StringBuilder();

        for (int i = 1; i < TASKS.size() + 1; i++) {
            result.append(i);
            result.append(". ");
            result.append(TASKS.get(i - 1).toString());
            if (i != TASKS.size()) {
                result.append("\n");
            }
        }

        return result.toString();
    }
}
