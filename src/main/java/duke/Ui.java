package duke;

import java.util.Scanner;

public class Ui {
    private Scanner input = new Scanner(System.in);

    public String getInput() {
        return input.nextLine();
    }

    public void printGreeting() {
        String greet = "Hi! I'm Uke\n" + "What can I do for you?\n";
        System.out.println(greet);
    }

    public void printList(TaskList storedTasks) {
        int len = storedTasks.getLength();
        if (len > 0) {
            System.out.println("Your added tasks:");

            for (int i = 1; i < len + 1; i++) {
                System.out.println(i + ". " + storedTasks.getTask(i - 1));
            }
        } else {
            System.out.println("No tasks found!");
        }
    }

    public void printMark(Task t) {
        System.out.println("The following task has been marked as done!");
        System.out.println(t);
    }

    public void printUnmark(Task t) {
        System.out.println("The following task has been marked as undone!");
        System.out.println(t);
    }

    public void printDelete(Task t) {
        System.out.println("The following task has been successfully deleted: " + t);
    }

    public void printNumberOfTasks(TaskList tl) {
        int len = tl.getLength();
        System.out.println("You now have " + len + " task(s) in your list.");
    }
    public void printExit() {
        String exit = "Bye. See you soon! :)\n";
        System.out.println(exit);
    }
    public void printError(Exception e) {
        System.out.println(e);
    }
}
