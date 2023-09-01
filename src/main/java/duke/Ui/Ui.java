package duke.Ui;

import duke.DukeException.DukeException;
import duke.Task.Task;
import duke.TaskList.TaskList;

/**
 * Collection of the Ui
 */
public class Ui {
    private final String Border = "____________________________________________________________";

    /**
     * Print a chat with specified message.
     * @param chat Message to be printed.
     */
    public void printChat(String chat) {
        System.out.println(Border + "\n" + chat + "\n" + Border);
    }

    /**
     * Print introduction.
     */
    public void Hello() {
        printChat("Hello! I'm Chrainx\n" + "What can I do for you?" );
    }

    /**
     * Print new task added to TaskList.
     * @param task Task to be added.
     * @param numberOfTask Number of task in the TaskList.
     */
    public void addTask(Task task, int numberOfTask) {
        printChat("Got it, I have added this task:\n"
                + task.toString() + "\n"
                + "Now you have " + numberOfTask
                + " tasks in the list"
        );
    }

    /**
     * Print delete task from TaskList.
     * @param task Task to be deleted.
     * @param numberOfTask Number of task in the TaskList.
     */
    public void deleteTask(Task task, int numberOfTask) {
        printChat("Noted, I have removed this task:\n"
                + task.toString() + "\n"
                + "Now you have " + numberOfTask
                + " tasks in the list"
        );
    }

    /**
     * Print task that the status is changed.
     * @param task Target task.
     */
    public void markAsDone(Task task) {
        printChat("You have marked this task as done\n" + task.toString());
    }

    /**
     * Print task that the status is changed.
     * @param task Target task.
     */
    public void markAsNotDone(Task task) {
        printChat("You have marked this task as not done\n" + task.toString());
    }

    /**
     * Print bye message.
     */
    public void bye() {
        printChat("Bye. Hope to see you again soon!\n" + "Wish You a wonderful day");
    }

    /**
     * Print message when there is unknown input.
     * @param e Exception where there is unknown input.
     */
    public void error(DukeException e) {
        printChat(e.toString());
    }

    /**
     * Print all tasks as a list.
     * @param tasks TaskList of the saved tasks.
     */
    public void listing(TaskList tasks) {
        String listOfTask = "Here are the list of your task:\n";
        for (int i = 0; i < tasks.getNumberOfTask(); i++) {
            listOfTask = listOfTask + tasks.getTask(i).toString() + "\n";
        }
        printChat(listOfTask);
    }

    /**
     * Find the task with certain name.
     * @param tasks List of the tasks.
     * @param input Name of the task.
     */
    public void find(TaskList tasks, String input) {
        String match = "Here are the matching tasks in your list:\n";
        for (int i = 0; i < tasks.getNumberOfTask(); i++) {
            if (tasks.getTask(i).getName().contains(input)) {
                match = match + tasks.getTask(i).toString() + "\n";
            }
        }
        printChat(match);
    }
}
