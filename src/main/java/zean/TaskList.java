package zean;

import java.util.ArrayList;

import zean.exception.ZeanException;
import zean.task.Deadline;
import zean.task.Event;
import zean.task.Task;
import zean.task.Todo;

/**
 * The class that contains the task list, with operations to add/delete tasks in the list.
 *
 * @author Zhong Han
 */
public class TaskList {
    private ArrayList<Task> tasks;
    private int count;

    private Storage storage;

    /**
     * An empty constructor for TaskList.
     * For the purpose of testing.
     */
    public TaskList() {
        this.tasks = new ArrayList<>();
    }

    /**
     * Constructor for TaskList.
     * Loads the tasks from storage.
     *
     * @param storage The storage object that reads and write data from the file.
     */
    public TaskList(Storage storage) {
        this.storage = storage;
        this.tasks = storage.load();
        this.count = this.tasks.size();
    }

    /**
     * Adds a todo task to the array list and writes to the disk.
     *
     * @param description The description of the todo task.
     * @return The output to be printed on the console.
     */
    public String add(String description) {
        assert description != null;
        Todo task = new Todo("0", description);
        this.tasks.add(task);
        this.count++;
        this.storage.addToDisk(task);
        return printAddTask(task);
    }

    /**
     * Adds a deadline task to the array list and writes to the disk.
     *
     * @param description The description of the deadline task.
     * @param by The due date of the deadline task.
     * @return The output to be printed on the console.
     */
    public String add(String description, String by) {
        assert description != null;
        Deadline task = new Deadline("0", description, by);
        this.tasks.add(task);
        this.count++;
        this.storage.addToDisk(task);
        return printAddTask(task);
    }

    /**
     * Adds an event task to the array list and writes to the disk.
     *
     * @param description The description of the event task.
     * @param from The start date of the event task.
     * @param to The end date of the event task.
     * @return The output to be printed on the console.
     */
    public String add(String description, String from, String to) {
        assert description != null;
        Event task = new Event("0", description, from, to);
        this.tasks.add(task);
        this.count++;
        this.storage.addToDisk(task);
        return printAddTask(task);
    }

    private String printAddTask(Task task) {
        return "Got it. I've added this task:\n  " + task + "\n" + this.printNumOfTasks();
    }

    /**
     * Returns the string of the list of tasks that the list holds.
     *
     * @return The output to be printed on the console.
     */
    public String list() {
        if (this.count == 0) {
            return "There are currently no tasks in your list:\n";
        }
        StringBuilder output = new StringBuilder("As requested, here are the tasks in your list:\n");
        for (int i = 0; i < this.count; i++) {
            output.append(String.format("%d.%s\n", i + 1, this.tasks.get(i)));
        }
        return output.toString();
    }

    /**
     * Marks the task corresponding to the index as done and writes to the disk.
     *
     * @param index The index of the task seen by the user, which starts from 1.
     * @return The output to be printed on the console.
     * @throws ZeanException An exception related to the chatbot.
     */
    public String markTaskDone(int index) throws ZeanException {
        if (index > this.count || index <= 0) {
            throw new ZeanException("Hmm, this task does not exist :|");
        }
        String taskOutput = this.tasks.get(index - 1).markTaskDone();
        this.storage.rewriteToDisk(this.tasks);
        return "Nice! I've marked this task as done:\n" + taskOutput;
    }

    /**
     * Marks the task corresponding to the index as not done and writes to the disk.
     *
     * @param index The index of the task seen by the user, which starts from 1.
     * @return The output to be printed on the console.
     * @throws ZeanException An exception related to the chatbot.
     */
    public String markTaskNotDone(int index) {
        if (index > this.count || index <= 0) {
            throw new ZeanException("Hmm, this task does not exist :|");
        }
        String taskOutput = this.tasks.get(index - 1).markTaskNotDone();
        this.storage.rewriteToDisk(this.tasks);
        return "Sure, I've marked this task as not done yet:\n" + taskOutput;

    }

    /**
     * Updates the description of the task.
     *
     * @param index The index of the task seen by the user, which starts from 1.
     * @param description The new description of the task.
     */
    public void updateTaskDescription(int index, String description) {
        if (index > this.count || index <= 0) {
            throw new ZeanException("Hmm, this task does not exist :|");
        }
        if (!description.isBlank()) {
            this.tasks.get(index - 1).updateDescription(description);
            this.storage.rewriteToDisk(this.tasks);
        }
    }

    /**
     * Updates the date of the task. Depending on what type of task it is, error is thrown
     * when the task does not support the updating of a particular date.
     *
     * @param index The index of the task seen by the user, which starts from 1.
     * @param by The new deadline date for deadline task.
     * @param from The new start date for event task.
     * @param to The new end date for event task.
     */
    public void updateTaskDates(int index, String by, String from, String to) {
        if (index > this.count || index <= 0) {
            throw new ZeanException("Hmm, this task does not exist :|");
        }
        this.tasks.get(index - 1).updateDates(by, from, to);
        this.storage.rewriteToDisk(this.tasks);
    }

    private String printNumOfTasks() {
        if (this.count < 2) {
            return String.format("Now you have %d task in the list.", this.count);
        } else {
            return String.format("Now you have %d tasks in the list.", this.count);
        }
    }

    /**
     * Deletes the task corresponding to the index and writes to the disk.
     *
     * @param index The index of the task seen by the user, which starts from 1.
     * @return The output to be printed on the console.
     * @throws ZeanException An exception related to the chatbot.
     */
    public String deleteTask(int index) throws ZeanException {
        if (index < 1 || index > this.count) {
            throw new ZeanException("Hmm, this task does not exist :|");
        }
        this.tasks.remove(index - 1);
        this.count--;
        this.storage.rewriteToDisk(this.tasks);
        return "Noted. I've removed this task.\n" + printNumOfTasks();
    }

    /**
     * Returns the string of the list of tasks that matches the search string.
     *
     * @param description The search string.
     * @return The output to be printed on the console.
     */
    public String find(String description) {
        assert description != null;
        ArrayList<Task> subList = new ArrayList<>();
        this.tasks.forEach((task) -> {
            if (task.getDescription().toLowerCase().contains(description.trim().toLowerCase())) {
                subList.add(task);
            }
        });
        if (subList.isEmpty()) {
            return "There are no matching tasks in your list.";
        }
        StringBuilder output = new StringBuilder("Here are the matching tasks in your list:\n");
        for (int i = 0; i < subList.size(); i++) {
            output.append(String.format("%d.%s\n", i + 1, subList.get(i)));
        }
        return output.toString();
    }
}
