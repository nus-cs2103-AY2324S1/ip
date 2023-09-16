package sae.task;

import sae.util.Ui;

import java.time.LocalDateTime;
import java.util.ArrayList;

/**
 * The TaskList class represents a list of tasks.
 * Users can add, delete, mark, and unmark tasks in this list.
 */
public class TaskList {
    private ArrayList<Task> tasks;

    private Ui msg;

    /**
     * Constructs a new TaskList with an empty task list and a user interface.
     */
    public TaskList() {
        this.tasks = new ArrayList<>();
        this.msg = new Ui();
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
    public void deleteTask(int index) {
        if (index >= 0 && index < tasks.size()) {
            Task task = tasks.get(index);
            tasks.remove(index);
            msg.deleteTaskMessage(task, tasks.size());
        } else {
            System.out.println("Invalid task index.");
        }
    }

    /**
     * Marks a task as done in the task list at the specified index.
     *
     * @param index The index of the task to be marked as done.
     */
    public void markTaskAsDone(int index) {
        Task task = tasks.get(index);
        task.markTask();
        msg.markAsDoneMessage(task);
    }

    /**
     * Unmarks a task as done in the task list at the specified index.
     *
     * @param index The index of the task to be unmarked as done.
     */
    public void unMarkTaskAsDone(int index) {
        Task task = tasks.get(index);
        task.unMarkTask();
        msg.unMarkAsDoneMessage(task);
    }

    /**
     * Lists all tasks in the task list.
     */
    public void listTasks() {
        int len = tasks.size();
        StringBuilder str = new StringBuilder();
        str.append("1." + tasks.get(0).toString());
        for (int i = 1; i < len; i++) {
            Task curr = tasks.get(i);
            str = str.append("\n" + (i + 1) + "." + curr.toString());
        }
        System.out.println(str.toString());
    }

    /**
     * Adds a new ToDo task to the task list.
     *
     * @param description The description of the ToDo task.
     */
    public void addToDoTask(String description) {
        Todo task = new Todo(description);
        tasks.add(task);
        msg.addTaskMessage(task, tasks.size());
    }

    /**
     * Adds a new Deadline task to the task list.
     *
     * @param description The description of the Deadline task.
     * @param by          The deadline of the Deadline task.
     */
    public void addDeadlineTask(String description, LocalDateTime by) {
        Deadline task = new Deadline(description, by);
        tasks.add(task);
        msg.addTaskMessage(task, tasks.size());
    }

    /**
     * Adds a new Event task to the task list.
     *
     * @param description The description of the Event task.
     * @param from        The start time of the Event task.
     * @param to          The end time of the Event task.
     */
    public void addEventTask(String description, String from, String to) {
        Event task = new Event(description, from, to);
        tasks.add(task);
        msg.addTaskMessage(task, tasks.size());
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
}
