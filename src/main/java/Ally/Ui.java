package Ally;

import java.util.Scanner;

public class Ui {
    private static final String line = "____________________________________________________________";
    private static final String greeting = "Hello! I'm ALLY\nWhat can I do for you?\n";
    private static final String bye = "Bye. Hope to see you again soon!";

    Scanner ipt = new Scanner(System.in);
    public String readCommand() {
        return this.ipt.nextLine();
    }
    public static void showLine() {
        System.out.println(line);
    }

    /**
     * Function that provides the starting message and greeting.
     */
    public void start() {
        showLine();
        System.out.println(greeting);
        showLine();
    }

    /**
     * Function that provides the bye message when the user
     * ends the chatbot.
     */
    public void bye() throws AllyException {
        System.out.println(line);
        System.out.println(bye);
        System.out.println(line);
    }

    public void showList(AllyList allyList) {
        showLine();
        System.out.println("Here are the tasks in your list:\n");
        for (int i = 0, len = allyList.getSize(); i < len; i++) {
            System.out.println((i + 1) +". " + allyList.getTask(i).toString());
        }
        showLine();
    }

    public void showDelete(Task task, int total) {
        showLine();
        System.out.println("Noted. I've removed this task:");
        System.out.println("\t" + task);
        System.out.println("Now you have " + total + " tasks in the list.");
        showLine();
    }

    public void showMarked(Task task) {
        task.setMarked();
        System.out.println("Nice! I've marked this task as done:");
        System.out.println("\t" + task.toString());
    }

    public void showNotMarked(Task task) {
        task.notDone();
        System.out.println("OK, I've marked this task as not done yet:");
        System.out.println("\t" + task.toString());
    }

    public void showLoadingError() throws AllyException {
        throw new AllyException("Unable to load!");
    }

    public void showError(String message) {
        System.out.println(message);
    }
}
