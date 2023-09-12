package cringebot.tasks;

import cringebot.exceptions.DukeException;
import cringebot.parser.Parser;
import cringebot.ui.Ui;

import java.io.Serializable;
import java.util.ArrayList;

/**
 * Temporary storage for the user's tasks.
 */
public class TaskList implements Serializable {

    public ArrayList<Task> tasks;

    /**
     * Constructor for TaskList.
     *
     * @param tasks tasks loaded from the storage.
     */
    public TaskList(ArrayList<Task> tasks) {
        this.tasks = tasks;
    }

    /**
     * Constructor when there are no tasks loaded from the storage.
     */
    public TaskList() {
        this.tasks = new ArrayList<>();
    }

    /**
     * Removes an item from the list of tasks.
     *
     * @param input input from the user.
     * @throws DukeException Lets the user know if task cannot be removed.
     */
    public String deleteItem(String input) throws DukeException {
        int index = this.getIndex(input) - 1;
        if (this.checkOutOfBounds(index)) {
            throw new DukeException(":(( OOPS!!! I'm sorry, but the index you have inputted is out of bounds :-(");
        } else {
            Task deletedTask = this.tasks.remove(index);
            return Ui.deleteItem(this.tasks.size(), deletedTask);
        }
    }

    /**
     * Adds item to the list of tasks.
     *
     * @param task type of task.
     * @param input input from the user.
     * @throws DukeException Lets the user know if the task cannot be added.
     */
    public String addItem(Parser.taskType task, String input) throws DukeException {
        String[] splitSentence = input.split(" /");
        String taskName = getRestOfSentence(splitSentence[0]).strip();
        Task newTask;

        switch(task) {
            case DEADLINE:
                checkEmpty(taskName, "deadline");
                if (splitSentence.length < 2 || !splitSentence[1].contains("by")) {
                    throw new DukeException(":((  OOPS!!! Please indicate a deadline with the /by keyword");
                }
                String date = splitSentence[1].replaceAll("by", "").strip();
                newTask = new Deadline(taskName, date);
                break;
            case EVENT:
                checkEmpty(taskName, "event");
                if (splitSentence.length < 3 || (!splitSentence[1].contains("from") && !splitSentence[2].contains("to"))) {
                    throw new DukeException(":((  OOPS!!! Please indicate a duration for the event with the /from and /to keywords");
                }
                String fromDatetime = splitSentence[1].replaceAll("from", "from:");
                String toDatetime = splitSentence[2].replaceAll("to", "to:");
                taskName = String.format("%s (%s %s)", taskName, fromDatetime, toDatetime);
                newTask = new Event(taskName);
                break;
            case TODO:
                checkEmpty(taskName, "todo");
                newTask = new Todo(taskName);
                break;
            default:
                throw new DukeException(":((  OOPS!!! I'm sorry, but I don't know what that means :-(");
        }

        this.tasks.add(newTask);

        return "Got it. I've added this task:\n"
                + newTask
                + "\nNow you have "
                + this.tasks.size()
                + " tasks in the list.";
    }

    /**
     * Find all items with the keyword from the list, then prints them.
     *
     * @param input input from the user.
     */
    public String findItems(String input) {
        String keyword = input.split(" ")[1].strip();
        StringBuilder tasksFound = new StringBuilder("Here are the matching tasks in your list:");
        for (int i = 0; i < this.tasks.size(); i++) {
            if (this.tasks.get(i).toString().contains(keyword)) {
                tasksFound.append(String.format("\n%d.%s", i + 1, this.tasks.get(i)));
            }
        }
        return tasksFound.toString();
    }

    /**
     * Marks and Un marks the task.
     *
     * @param status To mark of un mark the task.
     * @param input input from the user.
     * @throws DukeException Lets the user know if the task cannot be modified.
     */
    public String modifyStatus(Parser.modifyStatus status, String input) throws DukeException {
        int index = getIndex(input) - 1;
        this.checkOutOfBounds(index);
        switch(status) {
            case MARK:
                this.tasks.get(index).markTask();
                String markStatement = "Nice! I've marked this task as done:";
                return String.format("%s\n%s", markStatement, this.tasks.get(index));
            case UNMARK:
                this.tasks.get(index).unMarkTask();
                String unmarkStatement = "OK, I've marked this task as not done yet:";
                return String.format("%s\n%s", unmarkStatement, this.tasks.get(index));
            default:
                throw new DukeException(":((  OOPS!!! I'm sorry, but an error occurred when modifying your task :-(");
        }
    }

    /**
     * Returns the task when given an index.
     *
     * @param index index of the task.
     * @return task that has been found.
     */
    public Task getTaskWithIndex(int index) {
        return tasks.get(index);
    }

    /**
     * Returns the size of the task.
     *
     * @return size of the task.
     */
    public int size() {
        return tasks.size();
    }

    /**
     * Returns the list of task.
     *
     * @return List of tasks.
     */
    public ArrayList<Task> getTasks() {
        return this.tasks;
    }

    /**
     * Gets the index given by from the user input.
     *
     * @param input Input from the user.
     * @return Index given by the user.
     */
    public int getIndex(String input) {
        String[] parts = input.split(" ");

        if (parts.length >= 2) {
            String secondPart = parts[1];
            return Integer.parseInt(secondPart);
        }
        return -1;
    }

    /**
     * Checks of the index given exceeds the size of the list.
     *
     * @param index index to check.
     */
    public boolean checkOutOfBounds(int index) {
        return index > this.tasks.size() - 1;
    }

    /**
     * Checks if the input is empty.
     *
     * @param input input from the user.
     * @param taskName content for the task.
     * @throws DukeException Lets the user know if the description is invalid.
     */
    public static void checkEmpty(String input, String taskName) throws DukeException {
        if (input.isEmpty()) {
            throw new DukeException(String.format(":((  OOPS!!! The description of a %s cannot be empty.", taskName));
        }
    }

    /**
     * Gets the rest of the sentence, excluding the first word.
     *
     * @param input Input from the user.
     * @return rest of the sentence.
     */
    public static String getRestOfSentence(String input) {
        String[] parts = input.split(" ");
        StringBuilder result = new StringBuilder();

        if (parts.length > 1) {
            for (int i = 1; i < parts.length - 1; i++) {
                result.append(parts[i]).append(" ");
            }
            result.append(parts[parts.length - 1]);
        }
        return result.toString();
    }
}
