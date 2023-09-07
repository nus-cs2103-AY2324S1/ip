package duke.util;

import duke.CheeException;
import duke.task.Task;

import java.util.ArrayList;
import java.util.Scanner;

public class Ui {
    private Scanner input;

    public Ui(){
        input = new Scanner(System.in);
    }

//    /**
//     * Print the error message.
//     * @param error The error message.
//     */
//    public void showError(String error) {
//        System.out.println(error);
//    }

    /**
     * Print a line.
     */
    public String showLine() {
        return "________________________________________________________";
    }

    /**
     * read the command that the users input.
     * @return String representation of the user input.
     */
    public String readCommand() {
        return input.nextLine();
    }

    /**
     * Print out the Task that is added into the TaskList and the number of tasks
     * in the TaskList.
     * @param task The task that got added into the TaskList.
     * @param size The size of the TaskList.
     */
    public String printAddedTask(Task task, int size) {
        return "Got it. I've added this task:"
                + "\n  " + task.toString()
                + "\nNow you have " + size + " tasks in the list.";
    }

    /**
     * Print the task that got deleted and the remaining number of
     * tasks left in the TaskList.
     * @param task The task that is getting deleted.
     * @param size The size of the TaskList
     */
    public String printDeletedTask(Task task, int size) {
        return "Noted. I've removed this task:"
                + "\n  " + task.toString()
                + "Now you have " + size + " tasks in the list.";
    }

    public String printFind (ArrayList<Task> tasks) {
        String output = "Here are the matching tasks in your list:";
        for (int i = 0; i < tasks.size(); i++) {
            output += "\n" + i + ". " + tasks.get(i).toString();
        }
        return output;
    }

    /**
     * Print the exit message.
     */
    public String printExitMessage() {
        return "Bye. Hope to see you again soon!";
    }

    /**
     * Print the task that got marked as done.
     * @param task The task that got marked as done.
     */
    public String printMarkMessage(Task task) {
        return "Nice! I've marked this task as done:"
                    + "\n  " + task.toString();

    }

    public String printUnmarkMessage(Task task) {
        return "OK, I've marked this task as not done yet:"
                + "\n  " + task.toString();
    }

    /**
     * Print error message when there is an invalid command.
     */
    public String printInvalidMessage() {
        try {
            throw new CheeException("OOPS!!! I'm sorry, but I don't know what that means :-(");
        } catch (CheeException e) {
            return e.getMessage();
        }
    }

    /**
     * Print the tasks present in the TaskList.
     * @param tasklist The TaskList with all the Tasks.
     */
    public String printList(TaskList tasklist) {
        String output = "";
        for (int i = 0; i < tasklist.size(); i++){
            output += tasklist.getTaskDescription(i);
        }
        return output;
    }
}
