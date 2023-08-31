import java.util.ArrayList;
import java.util.Scanner;


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
        System.out.println("\nOh no! The task number cannot be empty.\n");
        System.out.println("Please key in the task number you would like to mark/unmark.\n");
    }

    public void showInvalidTaskNumErr() {
        System.out.println("Oh no! The task number does not exist.\n");
        System.out.println("You can find out the tasks and their numbers by typing list.\n");
        System.out.println("Please re-enter the correct task number to mark/unmark.");
    }

    public void showEmptyDescErr() {
        System.out.println("Oh no! The task description cannot be empty.\n");
        System.out.println("Please key in the task description, with timings where applicable.\n");
    }

    public void showInvalidCmdErr() {
        System.out.println("Oh no! I don't understand what you mean. Please key in either\n");
        System.out.println("1. deadline [task description] /by [deadline datetime]\n");
        System.out.println("2. event [task description] /from [start datetime] /to [end datetime]\n");
        System.out.println("3. todo [task description].");
    }

    public void showLoadingErr() {
        System.out.println("Error loading file: ");
    }

    public void showSavingErr() {
        System.out.println("Error saving file: ");
    }

    public void showDateTimeFormatErr() {
        System.out.println("Error with date time format: ");
    }

    public static void listsTasksMsg(ArrayList<Task> tasks) {
        System.out.println("Here are the tasks in your list:");
        for (int i = 0; i < tasks.size(); i++) {
            System.out.println((i + 1) + "." + tasks.get(i).toString());
        }
    }

    public void addsTasksMsg(Task task, int size) {
        System.out.println("Alright! I've added this task:\n ");
        System.out.println(task.toString());
        System.out.println("\nNow you have " + size + " tasks in the list.");
    }

    public void deletesTasksMsg(Task task, int size) {
        System.out.println("Ok, I've removed this task:\n");
        System.out.println(task.toString());
        System.out.println("\nNow you have " + size + " tasks in the list.");
    }

    public void marksTasksMsg(Task task) {
        System.out.println("Nice! I've marked this task as done:\n");
        System.out.println(task.toString());
    }

    public void unmarksTasksMsg(Task task) {
        System.out.println("Nice! I've marked this task as not done yet:\n");
        System.out.println(task.toString());
    }

}
