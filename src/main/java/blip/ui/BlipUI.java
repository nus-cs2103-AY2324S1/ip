package blip.ui;

import blip.tasks.*;

/**
 * Represents the user interface of Blip ChatBot.
 */
public class BlipUI {

    /**
     * Prints the intro message.
     */
    public void showIntro() {
        // Intro message by Blip.
        String intro = "Hello! I'm Blip\n"
                + "What can I do for you?";
        System.out.println(intro);
    }

    /**
     * Prints the out message.
     */
    public void showOutro() {
        // Outro message by Blip.
        String outro = "Bye. Hope to see you again soon!";
        System.out.println(outro);
    }

    /**
     * Prints the empty task number error message.
     */
    public void showEmptyTaskNumErr() {
        System.out.println("\nOh no! The task number cannot be empty.");
        System.out.println("Please key in the task number you would like to mark/unmark/delete.");
    }

    /**
     * Prints the invalid task number error message.
     */
    public void showInvalidTaskNumErr() {
        System.out.println("Oh no! The task number does not exist.");
        System.out.println("You can find out the tasks and their numbers by typing list.");
        System.out.println("Please re-enter the correct task number to mark/unmark/delete.");
    }

    /**
     * Prints the empty description error message.
     */
    public void showEmptyDescErr() {
        System.out.println("Oh no! The task description cannot be empty.");
        System.out.println("Please key in the task description, with date time where applicable.");
        System.out.println("1. deadline [task description] /by [yyyy-MM-dd HH:mm]");
        System.out.println("2. event [task description] /from [yyyy-MM-dd HH:mm] /to [yyyy-MM-dd HH:mm]");
        System.out.println("3. todo [task description].");
    }

    /**
     * Prints the invalid command error message.
     */
    public void showInvalidCmdErr() {
        System.out.println("Oh no! I don't understand what you mean. Please key in either");
        System.out.println("1. deadline [task description] /by [yyyy-MM-dd HH:mm]");
        System.out.println("2. event [task description] /from [yyyy-MM-dd HH:mm] /to [yyyy-MM-dd HH:mm]");
        System.out.println("3. todo [task description].");
    }

    /**
     * Prints the loading data file error message.
     */
    public void showLoadingErr() {
        System.out.println("Error loading file: ");
    }

    /**
     * Prints the saving data file error message.
     */
    public void showSavingErr() {
        System.out.println("Error saving file: ");
    }

    public void showDateTimeFormatErr() {
        System.out.println("Please format your date time to be yyyy-mm-dd HH:mm");
    }

    /**
     * Prints the list of other commands.
     */
    public void showListOfOtherCmds() {
        System.out.println("4. mark [task number]");
        System.out.println("5. unmark [task number]");
        System.out.println("6. delete [task number]");
        System.out.println("7. list");
    }

    /**
     * Prints the list of tasks.
     * @param tasks The task list to print tasks from
     */
    public static void listsTasksMsg(TaskList tasks) {
        System.out.println("Here are the tasks in your list:");
        for (int i = 0; i < tasks.size(); i++) {
            System.out.println((i + 1) + "." + tasks.getTask(i).toString());
        }
    }

    /**
     * Prints the message that task has been added.
     * @param task The task that was added to task list
     * @param size The size of the task list after it has added new task
     */
    public void addsTasksMsg(Task task, int size) {
        System.out.println("Alright! I've added this task: ");
        System.out.println(task.toString());
        System.out.println("Now you have " + size + " tasks in the list.");
    }

    /**
     * Prints the message that task has been deleted.
     * @param task The task that was deleted from task list
     * @param size The size of the task list after it has deleted a task
     */
    public void deletesTasksMsg(Task task, int size) {
        System.out.println("Ok, I've removed this task:");
        System.out.println(task.toString());
        System.out.println("Now you have " + size + " tasks in the list.");
    }

    /**
     * Prints the message that task has been marked.
     * @param task The task that was marked as done
     */
    public void marksTasksMsg(Task task) {
        System.out.println("Nice! I've marked this task as done:");
        System.out.println(task.toString());
    }

    /**
     * Prints the message that task has been unmarked.
     * @param task The task that was unmarked as not done
     */
    public void unmarksTasksMsg(Task task) {
        System.out.println("Nice! I've marked this task as not done yet:");
        System.out.println(task.toString());
    }


}
