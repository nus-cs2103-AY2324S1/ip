package sally;// deals with interactions with the user

import java.util.Scanner;

public class Ui {
    
    public String readCommand() {
        Scanner scanner = new Scanner(System.in);
        return scanner.nextLine();
    }
    
    public void showLoadingError() {
        System.out.println("OOPS! Something went wrong while loading your tasks.");
    }

    public void showWelcomeMessage() {
        System.out.println("Hello! It's sally.Sally here!");
        System.out.println("How can I help you today?");
    }

    public void showGoodbyeMessage() {
        System.out.println("Bye! See you again next time.");
    }

    public void showDeletedTask(Task task, int size) {
        System.out.println("Noted. I've removed this task:");
        System.out.println(" " + task);
        System.out.println("Now you have " + size + " tasks in the list.");
    }

    public void showTasks(TaskList tasks) {
        System.out.println("My list:");
        for (int i = 0; i < tasks.getSize(); i++) {
            System.out.println((i + 1) + "." + tasks.getTask(i));
        }
    }

    public void showMarkedTask(Task task) {
        System.out.println("Nice! I've marked this task as done:");
        System.out.println(" " + task);
    }

    public void showUnmarkedTask(Task task) {
        System.out.println("Ok, I've marked this task as not done yet:");
        System.out.println(" " + task);
    }

    public void showAddedTask(Task task, int size) {
        System.out.println("Added to My List: ");
        System.out.println(" " + task);
        System.out.println("Now you have " + size + " tasks in the list.");
    }

    public void showError(String errorMessage) {
        System.out.println(errorMessage);
    }

}
