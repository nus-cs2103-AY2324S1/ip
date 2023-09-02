package tasks;
import exceptions.EkudIllegalArgException;
import ui.Ui;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

/**
 * TaskList handles task modifications and invalid user inputs for
 * modifying tasks, and is a key component of the Ekud chatbot.
 */
public class TaskList {
    // Actual list storing the tasks
    private List<Task> tasks;
    // Constructor for TaskList
    public TaskList() {
        this.tasks = new ArrayList<>();
    }
    /**
     * Prints this TaskList to the console.
     */
    public void showTasks() {
        if (this.tasks.isEmpty()) {
            Ui.printMsg("Your to-do list is currently empty :o");
            return;
        }
        Ui.printDivider();
        System.out.println("Here is your to-do list:");
        int len = tasks.size();
        for (int i = 0; i < len; i++) {
            System.out.println(String.format("%d. %s", i + 1, tasks.get(i).toString()));
        }
        Ui.printDivider();
    }
    /**
     * Marks a specific task as done.
     * @param index Index number of task supplied by user.
     * @throws EkudIllegalArgException Illegal arg for index number.
     */
    public void markTaskAsDone(int index) throws EkudIllegalArgException {
        try {
            Task task = tasks.get(index);
            task.markAsDone();
            Ui.printMsg("The following task is marked done, sheeesh:\n"
                        + task.toString());
        } catch(IndexOutOfBoundsException e) {
            throw new EkudIllegalArgException("Task index number is out of bounds :/");
        }
    }

    /**
     * Same as the markTaskAsDone() function, but used when loading saved tasks.
     * @param index
     */
    public void markDoneOnStart(int index) {
        tasks.get(index).markAsDone();
    }
    /**
     * Marks a specific task as not done.
     * @param index Index number of task supplied by user.
     * @throws EkudIllegalArgException Illegal arg for index number.
     */
    public void markTaskAsNotDone(int index) throws EkudIllegalArgException {
        try {
            Task task = tasks.get(index);
            task.markAsNotDone();
            Ui.printMsg("The following task is marked as not done yet:\n"
                        + task.toString());
        } catch(IndexOutOfBoundsException e) {
            throw new EkudIllegalArgException("Task index number is out of bounds :/");
        }
    }
    /**
     * Prints a confirmation message for the user after adding a task.
     * @param task
     */
    private void printAddedTask(Task task) {
        Ui.printMsg(String.format(
                "Got it! I've added this task:\n%s\nNow you have %d task(s) in the list.",
                task.toString(),
                tasks.size()));
    }
    public void addTask(Task task) {
        this.tasks.add(task);
    }
    /**
     * Adds a to-do task to this TaskList.
     * @param description Description of to-do task by user.
     * @throws EkudIllegalArgException Illegal arg for to-do task.
     */
    public void addToDo(String description) throws EkudIllegalArgException {
        if (description.isBlank()) { // isBlank() checks if string is all whitespace
            throw new EkudIllegalArgException("Description shouldn't be empty :(");
        }
        ToDo newToDo = new ToDo(description);
        this.tasks.add(newToDo);
        this.printAddedTask(newToDo);
    }

    /**
     * Adds a deadline task to this TaskList.
     * @param description
     * @param dateTime
     * @throws EkudIllegalArgException
     */
    public void addDeadline(String description, LocalDateTime dateTime) throws EkudIllegalArgException {
        if (description.isBlank()) {
            throw new EkudIllegalArgException("Description shouldn't be empty :(");
        }
        Deadline newDeadline = new Deadline(description, dateTime);
        this.tasks.add(newDeadline);
        this.printAddedTask(newDeadline);
    }

    /**
     * Adds an event task to this TaskList.
     * @param description
     * @param fromDateTime
     * @param toDateTime
     * @throws EkudIllegalArgException Illegal arg(s) for event task.
     */
    public void addEvent(String description, LocalDateTime fromDateTime, LocalDateTime toDateTime)
            throws EkudIllegalArgException {
        if (description.isBlank()) {
            throw new EkudIllegalArgException("Description shouldn't be empty :(");
        }
        Event newEvent = new Event(description, fromDateTime, toDateTime);
        this.tasks.add(newEvent);
        this.printAddedTask(newEvent);
    }
    /**
     * Deletes a task from this TaskList and prints a confirmation message.
     * @param index Index number of task to be deleted as supplied by user.
     * @throws EkudIllegalArgException Illegal arg for index number.
     */
    public void deleteTask(int index) throws EkudIllegalArgException {
        if (tasks.isEmpty()) {
            throw new EkudIllegalArgException("You cannot delete from an empty task list :/");
        }
        try {
            Task task = tasks.get(index);
            this.tasks.remove(index);
            Ui.printMsg(String.format(
                    "Alright, this task has been removed:\n%s\nNow you have %d task(s) in the list.",
                    task.toString(),
                    tasks.size()));
        } catch(IndexOutOfBoundsException e) {
            throw new EkudIllegalArgException("Task index number is out of bounds :/");
        }
    }

    /**
     * Prints a list of tasks matching the user's keyword search.
     */
    public void findTasks(String keyword) {
        List<String> matchingTasks = new ArrayList<>();
        for (Task task: this.tasks) {
            if (task.description.contains(keyword)) {
                matchingTasks.add(task.toString());
            }
        }
        if (matchingTasks.isEmpty()) {
            Ui.printMsg(String.format(
                    "No tasks matching the keyword '%s' were found :(",
                    keyword
            ));
            return;
        }
        Ui.printDivider();
        System.out.println("Here are the matching tasks in your list: :>");
        for (String task: matchingTasks) {
            System.out.println(task);
        }
        Ui.printDivider();
    }

    public int getSize() {
        return this.tasks.size();
    }
    public String getSaveTaskFormat(int i) {
        return this.tasks.get(i).getSaveFormat();
    }

}
