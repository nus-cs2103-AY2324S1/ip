package duke;

import java.util.ArrayList;

/**
 * Represents a list of tasks with various operations for managing tasks.
 */
public class TaskList {
    private ArrayList<Task> tasks;

    /**
     * Constructs an empty TaskList.
     */
    public TaskList() {
        tasks = new ArrayList<>();
    }

    /**
     * Adds a deadline task to the list.
     *
     * @param description   The description of the deadline task.
     * @param isDone        Indicates whether the task is done or not.
     * @param byDateAndTime The date and time of the deadline.
     */
    public String addDeadline(String description, boolean isDone, String byDateAndTime) {
        Deadline deadline = new Deadline(description, isDone, byDateAndTime);
        tasks.add(deadline);
        return Ui.getAddConfirmation(deadline, this.getTotalTasks());
    }

    /**
     * Adds a todo task to the list.
     *
     * @param description The description of the todo task.
     * @param isDone      Indicates whether the task is done or not.
     */
    public String addTodo(String description, boolean isDone) {
        ToDo todo = new ToDo(description, isDone);
        tasks.add(todo);
        return Ui.getAddConfirmation(todo, this.getTotalTasks());
    }

    /**
     * Adds an event task to the list.
     *
     * @param description The description of the event task.
     * @param isDone      Indicates whether the task is done or not.
     * @param from        The starting date and time of the event.
     * @param to          The ending date and time of the event.
     * @return A message displaying event added to tasklist.
     */
    public String addEvent(String description, boolean isDone, String from, String to) {
        Event event = new Event(description, isDone, from, to);
        tasks.add(event);
        return Ui.getAddConfirmation(event, this.getTotalTasks());
    }

    /**
     * Removes a task from the list.
     *
     * @param index The index of the task to be removed.
     * @return A message displaying event removed from tasklist.
     */
    public String removeTask(int index) {
        Task tmp = tasks.get(index);
        int tmp1 = this.getTotalTasks() - 1;
        if (index >= 0 && index < tasks.size()) {
            tasks.remove(index);
        }
        return Ui.getRemoved(tmp, tmp1);

    }

    /**
     * Returns the list of tasks.
     *
     * @return The list of tasks.
     */
    public ArrayList<Task> getTasks() {
        return tasks;
    }

    /**
     * Returns the total number of tasks in the list.
     *
     * @return The total number of tasks.
     */
    public int getTotalTasks() {
        return tasks.size();
    }

    /**
     * Marks a task as done.
     *
     * @param index The index of the task to be marked as done.
     * @return A message displaying tasked marked as done.
     */
    public String markTask(int index) {
        tasks.get(index).markAsDone();
        return Ui.getMarkedAsDone(tasks.get(index));
    }

    /**
     * Unmarks a task as done.
     *
     * @param index The index of the task to be unmarked.
     *              * @return A message displaying tasked unmarked as not done.
     */
    public String unmarkTask(int index) {
        tasks.get(index).unmark();
        return Ui.getUnmarked(tasks.get(index));
    }

    /**
     * Sets the tasks in the list to the given tasks.
     *
     * @param taskList The ArrayList of tasks to be set.
     */
    public void setTasks(ArrayList<Task> taskList) {
        tasks.addAll(taskList);
    }

    /**
     * Finds tasks in the task list that match a given keyword and displays them to
     * the user.
     *
     * @param keyword The keyword to search for in task descriptions.
     * @return A message all tasks found.
     */

    public String findTasksByKeyword(String keyword) {
        ArrayList<Task> matchingTasks = new ArrayList<>();
        for (Task task : this.tasks) {
            if (task.toSaveString().contains(keyword)) {
                matchingTasks.add(task);
            }
        }

        return Ui.getMatchingTasks(matchingTasks);
    }
}
