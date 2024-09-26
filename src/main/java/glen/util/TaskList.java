package glen.util;

import glen.task.Deadline;
import glen.task.Event;
import glen.task.Task;
import glen.task.Todo;

import java.util.ArrayList;

/**
 * TaskList class that stores the list of tasks.
 * Contains methods to add, delete, and list tasks.
 */
public class TaskList {
    private static ArrayList<Task> tasks;

    /**
     * Constructor for TaskList class.
     * Creates a new ArrayList of tasks.
     */
    public TaskList() {
        this.tasks = new ArrayList<Task>();
    }

    /**
     * Deletes a task from the list of tasks.
     * 
     * @param storage   Storage object that handles the writing to the file.
     * @param taskIndex Index of the task to be deleted.
     * @return String that confirms the deletion of the task.
     */
    String del(Storage storage, int taskIndex) {
        assert taskIndex >= 0 && taskIndex < tasks.size() : "Invalid task index.";
        String temp = tasks.get(taskIndex).toString();
        tasks.remove(taskIndex);
        
        // short of defining a separate storage.delete method (with very similar code), the best way is to updateTask to null
        storage.updateTask(taskIndex, null);
        return "Noted. I've removed this task:\n  " + temp + "\n" + "Now you have " + tasks.size() + " tasks in the list.\n";
    }

    /**
     * Adds a Deadline task to the list of tasks.
     * 
     * @param storage   Storage object that handles the writing to the file.
     * @param inp       Description of the task.
     * @param by        Deadline of the task.
     * @return String that confirms the addition of the Deadline task.
     */
    String addDeadline(Storage storage, String inp, String by) {
        assert inp != null && !inp.trim().isEmpty() : "Task description cannot be empty";
        Deadline newTask = new Deadline(inp, false, by);
        String newTaskString = newTask.toFileString();
        tasks.add(newTask);
        storage.addTask(newTaskString);
        return "Got it. I've added this task:\n  " + newTask.toString() + "\nNow you have " + tasks.size() + " tasks in the list.\n";
    }

    /**
     * Adds an Event task to the list of tasks.
     * 
     * @param storage   Storage object that handles the writing to the file.
     * @param inp       Description of the task.
     * @param from      Start time of the task.
     * @param to        End time of the task.
     * @return String that confirms the addition of the Event task.
     */
    String addEvent(Storage storage, String inp, String from, String to) {
        assert inp != null && !inp.trim().isEmpty() : "Task description cannot be empty";
        Event newTask = new Event(inp, false, from, to);
        String newTaskString = newTask.toFileString();
        tasks.add(newTask);
        storage.addTask(newTaskString);
        return "Got it. I've added this task:\n  " + newTask.toString() + "\nNow you have " + tasks.size() + " tasks in the list.\n";
    }

    /**
     * Adds a Todo task to the list of tasks.
     * 
     * @param storage   Storage object that handles the writing to the file.
     * @param inp       Description of the task.
     * @return String that confirms the addition of the Todo task.
     */
    String addTodo(Storage storage, String inp) {
        assert inp != null && !inp.trim().isEmpty() : "Task description cannot be empty";
        Todo newTask = new Todo(inp, false);
        String newTaskString = newTask.toFileString();
        tasks.add(newTask);
        storage.addTask(newTaskString);
        return "Got it. I've added this task:\n  " + newTask.toString() + "\nNow you have " + tasks.size() + " tasks in the list.\n";
    }

    /**
     * Toggles the status of a task.
     * 
     * @param storage       Storage object that handles the writing to the file.
     * @param requestType   Type of request (mark/unmark).
     * @param taskIndex     Index of the task to be toggled.
     * @return String that confirms that the task has been marked/unmarked.
     */
    String toggle(Storage storage, String requestType, int taskIndex) {
        if (taskIndex > tasks.size()) {
            return "Invalid item selected. Please try again.";
        } else {
            Task task = tasks.get(taskIndex);
            if (requestType.equals("mark") && task.getStatusIcon().equals("[X] ")) {
                return "Task is currently marked. Did you mean to unmark the task?\n";
            } else if (requestType.equals("unmark") && task.getStatusIcon().equals("[ ] ")) {
                return "Task is currently unmarked. Did you mean to mark the task?\n";
            } else {
                task.toggle();
                String updatedTaskString = "";
                if (task instanceof Deadline) {
                    updatedTaskString = ((Deadline) task).toFileString();
                } else if (task instanceof Todo) {
                    updatedTaskString = ((Todo) task).toFileString();
                } else if (task instanceof Event) {
                    updatedTaskString = ((Event) task).toFileString();
                }
                storage.updateTask(taskIndex, updatedTaskString);
                if (task.getStatusIcon().equals("[X] ")) {
                    return "Nice! I've marked this task as done:\n"
                         + "  [X] " + task.getDescription() + "\n";
                } else {
                    return "OK, I've marked this task as not done yet:\n"
                         + "  [ ] " + task.getDescription() + "\n";
                }
            }
        }
    }

    /**
     * Finds all the tasks that contain the keyword.
     * 
     * @param keyword   Keyword to be searched for.
     * @return String that lists all the tasks that contain the keyword.
     */
    String find(String keyword) {
        assert keyword.trim() != null : "Keyword for task search cannot be null.";
        String temp = "Here are the matching tasks in your list:\n";
        int count = 0;
        for(int i = 0; i < tasks.size(); i++) {
            Task tempTask = tasks.get(i);
            if (tempTask.getDescription().contains(keyword)) {
                temp += String.valueOf(i + 1) + "." + tempTask.toString() + "\n";
                count++;
            }
        }
        if (count == 0) {
            return "No matching tasks found.\n";
        } else {
            return temp;
        }
    }

    /**
     * Lists all the tasks in the list of tasks.
     * 
     * @return String that lists all the tasks.
     */
    String lst() {
        String temp = "Here are the tasks in your list:\n";
        int len = tasks.size();
        if (len == 0) {
            return "There are currently no tasks in your list.";
        }
        for (int i = 0; i < len; i++) {
            Task tempTask = tasks.get(i);
            temp += String.valueOf(i + 1) + "." + tempTask.toString() + "\n";
        }
        return temp;
    }

    /**
     * Extends .add method in ArrayList class to TaskList class.
     * 
     * @param task  Task to be added to the list of tasks.
     */
    void add(Task task) {
        assert task != null : "Task to be added cannot be null.";
        this.tasks.add(task);
    }

    /**
     * Extends .get method in ArrayList class to TaskList class.
     * 
     * @return Size of the list of tasks.
     */
    int size() {
        return this.tasks.size();
    }
}
