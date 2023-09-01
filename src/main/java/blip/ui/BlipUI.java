package blip.ui;

import blip.tasks.*;

public class BlipUI {

    public void showIntro() {
        // Intro message by Blip.
        String intro = "Hello! I'm Blip\n"
                + "What can I do for you?";
        System.out.println(intro);
    }

    public void showOutro() {
        // Outro message by Blip.
        String outro = "Bye. Hope to see you again soon!";
        System.out.println(outro);
    }

    public void showEmptyTaskNumErr() {
        System.out.println("\nOh no! The task number cannot be empty.");
        System.out.println("Please key in the task number you would like to mark/unmark/delete.");
    }

    public void showInvalidTaskNumErr() {
        System.out.println("Oh no! The task number does not exist.");
        System.out.println("You can find out the tasks and their numbers by typing list.");
        System.out.println("Please re-enter the correct task number to mark/unmark/delete.");
    }

    public void showEmptyDescErr() {
        System.out.println("Oh no! The task description cannot be empty.");
        System.out.println("Please key in the task description, with date time where applicable.");
        System.out.println("1. deadline [task description] /by [yyyy-MM-dd HH:mm]");
        System.out.println("2. event [task description] /from [yyyy-MM-dd HH:mm] /to [yyyy-MM-dd HH:mm]");
        System.out.println("3. todo [task description].");
    }

    public void showInvalidCmdErr() {
        System.out.println("Oh no! I don't understand what you mean. Please key in either");
        System.out.println("1. deadline [task description] /by [yyyy-MM-dd HH:mm]");
        System.out.println("2. event [task description] /from [yyyy-MM-dd HH:mm] /to [yyyy-MM-dd HH:mm]");
        System.out.println("3. todo [task description].");
    }

    public void showLoadingErr() {
        System.out.println("Error loading file: ");
    }

    public void showSavingErr() {
        System.out.println("Error saving file: ");
    }

    public void showDateTimeFormatErr() {
        System.out.println("Please format your date time to be yyyy-mm-dd HH:mm");
    }

    public void showListOfOtherCmds() {
        System.out.println("4. mark [task number]");
        System.out.println("5. unmark [task number]");
        System.out.println("6. delete [task number]");
        System.out.println("7. list");
    }

    public static void listsTasksMsg(TaskList tasks) {
        System.out.println("Here are the tasks in your list:");
        for (int i = 0; i < tasks.size(); i++) {
            System.out.println((i + 1) + "." + tasks.getTask(i).toString());
        }
    }

    public void addsTasksMsg(Task task, int size) {
        System.out.println("Alright! I've added this task: ");
        System.out.println(task.toString());
        System.out.println("Now you have " + size + " tasks in the list.");
    }

    public void deletesTasksMsg(Task task, int size) {
        System.out.println("Ok, I've removed this task:");
        System.out.println(task.toString());
        System.out.println("Now you have " + size + " tasks in the list.");
    }

    public void marksTasksMsg(Task task) {
        System.out.println("Nice! I've marked this task as done:");
        System.out.println(task.toString());
    }

    public void unmarksTasksMsg(Task task) {
        System.out.println("Nice! I've marked this task as not done yet:");
        System.out.println(task.toString());
    }

    public void showBlipErr() {
        System.out.println("Some error occurred!");
    }
}
