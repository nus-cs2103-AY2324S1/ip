package duke.tasks;

import java.util.ArrayList;
import java.util.Comparator;

/**
 * Encapsulates a task list to be managed by the chatbot.
 * Utilizes an ArrayList to store the Tasks.
 *
 * @author Wu Jingya
 */
public class TaskList {
    private ArrayList<Task> tasks;

    /**
     * Creates an empty TaskList.
     */
    public TaskList() {
        this.tasks = new ArrayList<>();
    }

    /**
     * Returns the string representation of the Task at the specified index in the TaskList.
     *
     * @param index The index of the Task in the TaskList.
     * @return The string representation of the Task.
     */
    public String getTaskAsString(int index) {
        return tasks.get(index).toString();
    }

    /**
     * Prints the entire TaskList to console as a numbered list.
     */
    public String printTasksAsList() {
        String str = "";
        int index = 1;
        for (Task task : tasks) {
            str += index + "." + task.toString() + "\n";
            index++;
        }
        return str;
    }

    /**
     * Adds the specified Task to the TaskList.
     *
     * @param newTask The Task to be added.
     */
    public void addTask(Task newTask) {
        tasks.add(newTask);
    }

    /**
     * Removes the Task at the specified index from the TaskList.
     *
     * @param index The index of the Task to be removed.
     */
    public void removeTask(int index) {
        tasks.remove(index);
    }

    /**
     * Returns the Task at the specified index in the TaskList.
     *
     * @param index The index of the Task.
     * @return The Task at the specified index.
     */
    public Task getTask(int index) {
        return tasks.get(index);
    }

    /**
     * Returns the total number of Tasks in the TaskList.
     *
     * @return The size of the TaskList.
     */
    public int getTaskCount() {
        return tasks.size();
    }

    /**
     * Marks or unmarks the Task at the specified index as done.
     * Marks the Task as done if the argument done is true, and unmarks the Task as done if otherwise.
     *
     * @param index The index of the Task.
     * @param done Whether the Task has been completed.
     */
    public void markTaskAsDone(int index, boolean done) {
        getTask(index).markTaskCompleted(done);
    }

    /**
     * Returns the string representation of the data of all the Tasks in the TaskList.
     * This string contains all the data that is written to hard drive when the TaskList
     * is saved.
     *
     * @return The string representation of the TaskList data.
     */
    public String getTaskListData() {
        String data = "";
        for (Task task : tasks) {
            data = data.concat(task.toData() + "\n");
        }
        return data;
    }

    /**
     * Returns a TaskList only containing Tasks whose descriptions contain the specified keyword.
     * Search is case-insensitive.
     *
     * @param keyword The keyword.
     * @return A TaskList containing Tasks with the keyword.
     */
    public TaskList findTasksByKeyword(String keyword) {
        TaskList output = new TaskList();
        for (Task task : tasks) {
            if (task.containsWord(keyword)) {
                output.addTask(task);
            }
        }
        return output;
    }

    /**
     * Sorts the TaskList using the specified SortOrder.
     *
     * @param sortOrder The order by which to sort the TaskList.
     */
    public void sort(SortOrder sortOrder) {
        tasks.sort(sortOrder.comparator);
    }

    /**
     * Enumeration for sort orders.
     * These are the available ways to sort the task list provided to users.
     */
    public enum SortOrder {
        TASK_DESCRIPTION((t1, t2) -> t1.compareDescription(t2)),
        TASK_DATE((t1, t2) -> t1.compareDate(t2)),
        TASK_TYPE((t1, t2) -> t1.compareType(t2));

        private Comparator<? super Task> comparator;
        SortOrder(Comparator<? super Task> comparator) {
            this.comparator = comparator;
        }

        @Override
        public String toString() {
            switch (this) {
            case TASK_DESCRIPTION:
                return "description";
            case TASK_DATE:
                return "date";
            case TASK_TYPE:
                return "type";
            default:
                return "null";
            }
        }
    }
}
