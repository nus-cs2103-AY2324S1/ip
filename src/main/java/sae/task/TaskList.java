package sae.task;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import sae.util.Ui;


/**
 * The TaskList class represents a list of tasks.
 * Users can add, delete, mark, and unmark tasks in this list.
 */
public class TaskList {
    private ArrayList<Task> tasks;

    private Ui message;

    /**
     * Constructs a new TaskList with an empty task list and a user interface.
     */
    public TaskList() {
        this.tasks = new ArrayList<>();
        this.message = new Ui();
    }

    /**
     * Adds a task to the task list.
     *
     * @param task The task to be added.
     */
    public void addTask(Task task) {
        tasks.add(task);
    }

    /**
     * Deletes a task from the task list at the specified index.
     *
     * @param index The index of the task to be deleted.
     */
    public String deleteTask(int index) {
        if (index >= 0 && index < tasks.size()) {
            Task task = tasks.get(index);
            tasks.remove(index);
            return message.deleteTaskMessage(task, tasks.size());
        } else {
            return "Invalid task index.";
        }
    }

    /**
     * Deletes a range of tasks from the task list, specified by their indices (inclusive).
     *
     * @param start The index of the first task to delete (0-based).
     * @param end   The index of the last task to delete (0-based).
     * @return A response message indicating the deleted tasks or an error message if the range is invalid.
     */
    public String deleteTask(int start, int end) {
        ArrayList<Task> deletedTasks = new ArrayList<>();
        if (start >= 0 && end < tasks.size()) {
            Iterator<Task> iterator = tasks.iterator();
            int currentIndex = 0;

            while (iterator.hasNext()) {
                Task task = iterator.next();
                if (currentIndex >= start && currentIndex <= end) {
                    deletedTasks.add(task);
                    iterator.remove(); // Safely remove the task
                }
                currentIndex++;
            }

            return message.groupDeleteMessage(start, end, deletedTasks);
        } else {
            return "Invalid task index range.";
        }
    }


    /**
     * Marks a task as done in the task list at the specified index.
     *
     * @param index The index of the task to be marked as done.
     */
    public String markTaskAsDone(int index) {
        Task task = tasks.get(index);
        task.markTask();
        return message.markAsDoneMessage(task);
    }

    /**
     * Unmarks a task as done in the task list at the specified index.
     *
     * @param index The index of the task to be unmarked as done.
     */
    public String unMarkTaskAsDone(int index) {
        Task task = tasks.get(index);
        task.unMarkTask();
        return message.unMarkAsDoneMessage(task);
    }

    /**
     * Returns a string containing a list of all tasks in the task list.
     * Each task is numbered in the list.
     *
     * @return A formatted string listing all tasks, or an error message if the task list is empty.
     */
    public String listTasks() {
        int len = tasks.size();
        if (len > 0) {
            StringBuilder str = new StringBuilder();
            str.append("1." + tasks.get(0).toString());
            for (int i = 1; i < len; i++) {
                Task curr = tasks.get(i);
                str = str.append("\n" + (i + 1) + "." + curr.toString());
            }
            return str.toString();
        } else {
            return message.invalidListCommand();
        }
    }

    /**
     * Adds a new ToDo task to the task list.
     *
     * @param description The description of the ToDo task.
     */
    public String addToDoTask(String description) {
        Todo task = new Todo(description);
        tasks.add(task);
        return message.addTaskMessage(task, tasks.size());
    }

    /**
     * Adds a new Deadline task to the task list.
     *
     * @param description The description of the Deadline task.
     * @param by          The deadline of the Deadline task.
     */
    public String addDeadlineTask(String description, LocalDateTime by) {
        Deadline task = new Deadline(description, by);
        tasks.add(task);
        return message.addTaskMessage(task, tasks.size());
    }

    /**
     * Adds a new Event task to the task list.
     *
     * @param description The description of the Event task.
     * @param from        The start time of the Event task.
     * @param to          The end time of the Event task.
     */
    public String addEventTask(String description, String from, String to) {
        Event task = new Event(description, from, to);
        tasks.add(task);
        return message.addTaskMessage(task, tasks.size());
    }

    /**
     * Retrieves a task from the task list at the specified position.
     *
     * @param pos The position of the task to be retrieved.
     * @return The task at the specified position.
     */
    public Task get(int pos) {
        return tasks.get(pos);
    }

    /**
     * Gets the number of tasks in the task list.
     *
     * @return The number of tasks in the task list.
     */
    public int size(){
        return tasks.size();
    }

    /**
     * Searches for tasks containing a specified keyword and returns a list of matching tasks.
     *
     * @param keyword The keyword to search for within task descriptions.
     * @return A string containing a list of matching tasks or a message indicating no matches.
     */
    public String findKeyword(String keyword) {
        List<String> relatedTasks = new ArrayList<>();

        for (int i = 0; i < tasks.size(); i++) {
            Task task = tasks.get(i);
            String taskName = task.toString();
            if (taskName.contains(keyword)) {
                relatedTasks.add((i + 1) + "." + taskName);
            }
        }

        if (!relatedTasks.isEmpty()) {
            return "Here are the matching tasks in your list:\n" + String.join("\n", relatedTasks);
        } else {
            return "No tasks match the keyword given.";
        }
    }


}
