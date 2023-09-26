package taskutil;

import java.time.LocalDateTime;
import java.util.ArrayList;

import duke.Parser;
import duke.Ui;

/**
 * Class for a list of tasks.
 */
public class TaskList {

    private final ArrayList<Task> taskList;

    /**
     * Constructor for TaskList using ArrayList.
     */
    public TaskList() {
        taskList = new ArrayList<>(1);
    }

    /**
     * Modifies status of task based on index.
     *
     * @param index ArrayList index of task to be modified.
     * @param isCompleted New status of task.
     * @return Chatbot output after setting status.
     */
    public String setStatusByIndex(int index, boolean isCompleted) {
        try {
            Task pendingTask = taskList.get(index);
            pendingTask.isDone = isCompleted;
            return "Task updated as requested:\n       " + pendingTask;
        } catch (IndexOutOfBoundsException e) { // When index falls outside ArrayList.
            return "I can't modify a task that doesn't exist...\n     You should try an actual number";
        }
    }

    /**
     * Adds task to taskList.
     *
     * @param task Task to be added.
     * @param printMessage Determines whether to show task added message.
     */
    public void addTask(Task task, boolean printMessage) {
        taskList.add(task);
        if (printMessage) {
            Ui.taskOutput(task, Ui.Action.ADD, taskList.size());
        }
    }

    /**
     * Deletes task from taskList.
     *
     * @param index ArrayList index of task.
     */
    public void deleteTask(int index) {
        try {
            Task delete = taskList.remove(index);
            Ui.taskOutput(delete, Ui.Action.REMOVE, taskList.size());
        } catch (IndexOutOfBoundsException e) { // When index falls outside ArrayList.
            Ui.setOutMessage("I can't remove a task that doesn't exist...\n     You should try an actual number");
        }
    }

    /**
     * Returns list of all tasks matching input query.
     *
     * @param query String to match tasks with.
     * @return Numbered list of all tasks matching query.
     */
    public String queryList(String query) {
        StringBuilder allTasks = new StringBuilder("These tasks match your query:");
        int i = 1;
        for (Task task: taskList) {
            if (task.contains(query)) {
                allTasks.append(String.format("\n     %d.%s", i++, task));
            }
        }
        return allTasks.toString();
    }

    /**
     * Returns schedule of unmarked tasks based on input datetime.
     *
     * @param localDateTime Schedule datetime.
     * @return Numbered list of all tasks matching input.
     */
    public String filterByTime(LocalDateTime localDateTime) {
        StringBuilder allTasks = new StringBuilder("These tasks are due for "
                + Parser.formatDate(localDateTime) + ":");
        int i = 1;
        for (Task task : taskList) {
            if (task.isUnderSchedule(localDateTime) && task.getStatus().equals(" ")) {
                allTasks.append(String.format("\n     %d.%s", i++, task));
            }
        }
        return allTasks.toString();
    }

    /**
     * Converts ArrayList of tasks to a formatted string suited for storing in data file.
     *
     * @return Formatted string of tasks to store in data file.
     */
    public String listToStringData() {
        StringBuilder fileData = new StringBuilder();
        for (Task task : taskList) {
            assert task != null : "Tasks in taskList cannot be null";
            String taskString = task.toFileString() + "\n";
            fileData.append(taskString);
        }
        return fileData.toString();
    }

    /**
     * Converts ArrayList of tasks to a string as a numbered list for chatbot output.
     *
     * @return List of all tasks, formatted as numbered list.
     */
    public String listToString() {
        StringBuilder allTasks = new StringBuilder("Here are your tasks:");
        int taskNo = taskList.size();
        for (int i = 0; i < taskNo; i++) {
            allTasks.append(String.format("\n     %d.%s", (i + 1), taskList.get(i).toString()));
        }
        return allTasks.toString();
    }
}
