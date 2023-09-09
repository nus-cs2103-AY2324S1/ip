package ratspeak.data;

import ratspeak.exception.DukeException;
import ratspeak.storage.Storage;
import ratspeak.task.Task;

import java.util.ArrayList;

/**
 * represents a task list that stores  a list of tasks
 * performs add, delete, mark, unmark
 */
public class TaskList {
    private ArrayList<Task> tasklist;
    private static final Storage STORAGE = new Storage();

    /**
     * initialize TaskList object
     * @param tasks ArrayList of tasks
     */
    public TaskList(ArrayList<Task> tasks) {
        this.tasklist = tasks;
    }

    private static boolean isInteger(String taskNumber) {
        for (int i = 0; i < taskNumber.length(); i++) {
            if (!Character.isDigit(taskNumber.charAt(i))) {
                return false;
            }
        }
        return true;
    }

    /**
     * returns string for output
     * adds the input task to the ArrayList of tasks
     * @param task task to be added to TaskList
     * @return string for output
     */
    public String addToList(Task task) {
        this.tasklist.add(task);
        return ("Got it. I've added this task:\n"
                + task.toString() + "\nNow you have "
                + tasklist.size() + " tasks in the list.\n");

    }


    private int getTaskIndex(String taskNumber) throws DukeException {
        if (!isInteger(taskNumber)) {
            throw new DukeException("Number not found: Please enter a valid task number");
        }

        int taskIndex = Integer.parseInt(taskNumber) - 1;

        if (taskIndex >= tasklist.size()) {
            throw new DukeException("Task number out of range: please enter a valid task number");
        }
        return taskIndex;
    }

    /**
     * returns output string for printing to the user
     * mark task as done, then set the task as the
     * writes back to the file to update the file of the changes
     * @param taskNumber string of the task number
     * @return output string for printing
     * @throws DukeException if taskNumber is not a number or there is no task with that number
     */

    public String mark(String taskNumber) throws DukeException {
        int taskIndex = getTaskIndex(taskNumber);
        assert(taskIndex < tasklist.size());
        Task markedTask = this.tasklist.get(taskIndex).done();
        tasklist.set(taskIndex, markedTask);
        STORAGE.writeAllToFile(tasklist);
        return ("Nice! I've marked this task as done:\n" + markedTask.toString() + "\n");
    }

    /**
     * returns output string for printing to the user
     * mark task as undone, then set the task as the
     * writes back to the file to update the file of the changes
     * @param taskNumber string of the task number
     * @return output string for printing
     * @throws DukeException if taskNumber is not a number or there is no task with that number
     */
    public String unMark(String taskNumber) throws DukeException {
        int taskIndex = getTaskIndex(taskNumber);
        Task unmarkedTask = this.tasklist.get(taskIndex).undone();
        tasklist.set(taskIndex, unmarkedTask);
        STORAGE.writeAllToFile(tasklist);
        return ("OK, I've marked this task as not done yet:\n" + unmarkedTask.toString() + "\n");
    }

    /**
     * returns output string for printing to the user
     * delete the task from the ArrayList of tasks
     * writes back to the file to update the file of the changes
     * @param taskNumber string of the task number
     * @return output string for printing
     * @throws DukeException if taskNumber is not a number or there is no task with that number
     */
    public String delete(String taskNumber) throws DukeException {
        int taskIndex = getTaskIndex(taskNumber);
        Task removedTask = tasklist.remove(taskIndex);
        STORAGE.writeAllToFile(tasklist);
        return ("Noted. I've removed this task:\n"
                + removedTask.toString()
                + "\nNow you have " + tasklist.size()
                + " tasks in the list.\n");
    }

    /**
     * returns output string for printing to user
     * @param keyword words or letters to match with tasklist
     * @return output string for printing
     */
    public String find(String keyword) {
        String output = "Here are the matching tasks in your list:\n";
        int matchNum = 1;

        for (int i = 0; i < tasklist.size(); i++) {
            if (tasklist.get(i).getTask().contains(keyword)) {
                output = output + matchNum
                        + ". " + tasklist.get(i).toString() + "\n";
                matchNum++;
            }
        }
        return output;
    }

    /**
     * returns string of all the tasks in the ArrayList of tasks
     * @return string of all the task in the ArrayList of tasks
     */
    public String listContent() {
        String listOfContents = "Sure, here are the list of tasks:\n";
        if (tasklist.isEmpty()) {
            return ("Oops! It seems you do not have anything in your task list");
        }
        for (int i = 0; i < tasklist.size(); i++) {
            listOfContents = listOfContents + ((i + 1) + ": " + tasklist.get(i).toString() + "\n");
        }
        return listOfContents;
    }

}
