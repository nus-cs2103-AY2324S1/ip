package duke.Ui;

import duke.DukeException.DukeException;
import duke.Storage.Storage;
import duke.Task.Task;
import duke.TaskList.TaskList;

/**
 * Collection of the Ui
 */
public class Ui {

    /**
     * Prints a chat with specified message.
     * @param chat Message to be printed.
     */
    public String printChat(String chat) {

        return chat + " Meow";
    }

    /**
     * Prints introduction.
     */
    public String hello() {
        return "Hello! I'm Chrainx\n" + "What can I do for you?";
    }

    /**
     * Prints new task added to TaskList.
     * @param task Task to be added.
     * @param numberOfTask Number of task in the TaskList.
     */
    public String addTask(Task task, int numberOfTask) {
        assert numberOfTask >= 0;
        return "Got it, I have added this task:\n"
                + task.toString() + "\n"
                + "Now you have " + numberOfTask
                + " tasks in the list";
    }

    /**
     * Prints delete task from TaskList.
     * @param task Task to be deleted.
     * @param numberOfTask Number of task in the TaskList.
     */
    public String deleteTask(Task task, int numberOfTask) {
        assert numberOfTask >= 0;
        return "Noted, I have removed this task:\n"
                + task.toString() + "\n"
                + "Now you have " + numberOfTask
                + " tasks in the list";
    }

    /**
     * Prints task that the status is changed.
     * @param task Target task.
     */
    public String markAsDone(Task task) {
        return "You have marked this task as done\n" + task.toString();
    }

    /**
     * Prints task that the status is changed.
     * @param task Target task.
     */
    public String markAsNotDone(Task task) {
        return "You have marked this task as not done\n" + task.toString();
    }

    /**
     * Prints bye message.
     */
    public String bye(Storage storage, TaskList tasks) throws DukeException {
        storage.editStorage(tasks);
        return "Bye. Hope to see you again soon!\n" + "Wish You a wonderful day";
    }

    /**
     * Prints message when there is unknown input.
     * @param e Exception where there is unknown input.
     */
    public String error(DukeException e) {
        return e.toString();
    }

    /**
     * Prints all tasks as a list.
     * @param tasks TaskList of the saved tasks.
     */
    public String listing(TaskList tasks) {
        String listOfTask = "Here are the list of your task:\n";
        assert tasks.getNumberOfTask() >= 0;
        for (int i = 0; i < tasks.getNumberOfTask(); i++) {
            listOfTask = listOfTask + + (i+1) + ". " + tasks.getTask(i).toString() + "\n";
        }
        return listOfTask;
    }

    /**
     * Find the tasks with certain name.
     * @param tasks List of the tasks.
     * @param input Name of the task.
     */
    public String find(TaskList tasks, String input) {
        String match = "Here are the matching tasks in your list:\n";
        assert tasks.getNumberOfTask() >= 0;
        for (int i = 0; i < tasks.getNumberOfTask(); i++) {
            if (tasks.getTask(i).getName().contains(input)) {
                match = match + (i+1) + ". " + tasks.getTask(i).toString() + "\n";
            }
        }
        return match;
    }
}
