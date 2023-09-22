package chad.task;

import java.util.ArrayList;
import java.util.stream.Collectors;
import java.util.Collections;
import java.util.Comparator;
import java.time.LocalDateTime;

/**
 * Represents a list of tasks, providing methods to manipulate the tasks.
 */
public class TaskList {
    private ArrayList<Task> tasks;

    /**
     * Constructs a new empty TaskList.
     */
    public TaskList() {
        this.tasks = new ArrayList<>();
    }

    /**
     * Constructs a new TaskList with existing tasks.
     * @param tasks an ArrayList of existing tasks.
     */
    public TaskList(ArrayList<Task> tasks) {
        this.tasks = tasks;
    }

    /**
     * Adds a new ToDo task to the list.
     * @param description the description of the ToDo task.
     */
    public void addTodo(String description) {
        tasks.add(new ToDo(description));
    }

    /**
     * Adds a new Deadline task to the list.
     * @param description the description of the Deadline task.
     * @param dueDate the due date of the Deadline task.
     */
    public void addDeadline(String description, LocalDateTime dueDate) {
        tasks.add(new Deadline(description, dueDate));
    }

    /**
     * Adds a new Event task to the list.
     * @param description the description of the Event task.
     * @param start the start date and time of the Event task.
     * @param end the end date and time of the Event task.
     */
    public void addEvent(String description, LocalDateTime start, LocalDateTime end) {
        tasks.add(new Event(description, start, end));
    }

    /**
     * Marks a task as done.
     * @param index the index of the task to be marked as done.
     * @return a status message indicating success or failure.
     */
    public String markTaskAsDone(int index) {
        if (index >= 0 && index < tasks.size()) {
            Task task = tasks.get(index);
            task.mark();
            return "Yessir! This task is marked as done:\n" + task.toString();
        }
        return "Invalid task index.";
    }

    /**
     * Marks a task as not done.
     * @param index the index of the task to be marked as not done.
     * @return a status message indicating success or failure.
     */
    public String markTaskAsNotDone(int index) {
        if (index >= 0 && index < tasks.size()) {
            Task task = tasks.get(index);
            task.unMark();
            return "Aite, I've marked this task as not done yet:\n" + task.toString();
        }
        return "Invalid task index.";
    }

    /**
     * Returns a string representation of all tasks in the list.
     * @return a string containing all tasks.
     */
    public String displayTasks() {
        StringBuilder sb = new StringBuilder("Here's your tasks m8:\n");
        for (int i = 0; i < tasks.size(); i++) {
            sb.append((i + 1) + "." + tasks.get(i).toString()).append("\n");
        }
        sb.append("Now you have " + tasks.size() + " tasks in your list\n");
        return sb.toString();
    }

    /**
     * Deletes a task from the list.
     * @param taskIndex the index of the task to be deleted.
     * @return a status message indicating success or failure.
     */
    public String deleteTask(int taskIndex) {
        if (taskIndex >= 0 && taskIndex < tasks.size()) {
            Task task = tasks.get(taskIndex);
            tasks.remove(taskIndex);
            return "Aite, this task is gone bro: " + task.toString();
        }
        return "Invalid task index.";
    }

    /**
     * Returns the ArrayList of tasks.
     * @return an ArrayList containing all tasks.
     */
    public ArrayList<Task> getTasks() {
        return tasks;
    }

    /**
     * Sets the ArrayList of tasks.
     * @param tasks the new ArrayList of tasks.
     */
    public void setTasks(ArrayList<Task> tasks) {
        this.tasks = tasks;
    }

    public String findTasks(String keyword) {
        ArrayList<Task> matchingTasks = (ArrayList<Task>) tasks.stream()
                .filter(task -> task.getDescription().toLowerCase().contains(keyword.toLowerCase()))
                .collect(Collectors.toList());

        if (matchingTasks.isEmpty()) {
            return "No tasks found with that keyword.";
        }

        StringBuilder sb = new StringBuilder("Here are the matching tasks in your list:\n");
        for (int i = 0; i < matchingTasks.size(); i++) {
            sb.append((i + 1) + "." + matchingTasks.get(i).toString()).append("\n");
        }
        return sb.toString();
    }

    /**
     * Sorts the tasks in the list by their deadlines.
     * Tasks without a deadline (ToDo tasks) are moved to the end of the list.
     */
    public TaskList sortTasksByDeadline() {
        ArrayList<Task> sortedTasks = new ArrayList<>(tasks);
        Collections.sort(sortedTasks, new Comparator<Task>() {
            @Override
            public int compare(Task o1, Task o2) {
                if (o1 instanceof Deadline && o2 instanceof Deadline) {
                    Deadline d1 = (Deadline) o1;
                    Deadline d2 = (Deadline) o2;
                    return d1.getDueDate().compareTo(d2.getDueDate());
                } else if (o1 instanceof Deadline) {
                    return -1;
                } else if (o2 instanceof Deadline) {
                    return 1;
                }
                return 0;
            }
        });
        return new TaskList(sortedTasks);
    }

}
