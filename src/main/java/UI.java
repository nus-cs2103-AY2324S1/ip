import java.util.ArrayList;
import java.util.Scanner;

public class UI {
    private Scanner scanner = new Scanner(System.in);

    public void greet() {
        System.out.println("____________________________________________________________");
        String logo = "  __ _  ___ ___  _   _  _   _  \n" +
                " / _/ \\| o \\_ _|/ \\ | \\| | / \\ \n" +
                "( (( o )   /| || o || \\\\ || o |\n" +
                " \\__\\_/|_|\\\\|_||_n_||_|\\_||_n_|";
        System.out.println("Hello I'm Cortana, Microsoft killed me so now I'm here\n" + logo);
        System.out.println("____________________________________________________________");
    }

    public void exit() {
        scanner.close();
        System.out.println("Bye");
        System.out.println("____________________________________________________________");
    }

    public String readInput() {
        return scanner.nextLine();
    }

    public boolean hasInput() {
        return scanner.hasNextLine();
    }

    public void displayList(TaskList list) {
        if (list.isEmpty()) {
            System.out.println("There are no tasks in the list");
        }
        System.out.println(list);
        System.out.println("____________________________________________________________");
    }

    public void displayAddToList(Task task, int totalSize) {
        System.out.println("This task is added to the list");
        System.out.println(task);
        System.out.printf("You now have %d tasks in your list%n", totalSize);
        System.out.println("____________________________________________________________");
    }

    public void displayRemoveFromList(Task task, int totalSize) {
        System.out.println("This task is deleted from the list");
        System.out.println(task);
        System.out.printf("You now have %d tasks in your list:%n", totalSize);
        System.out.println("____________________________________________________________");
    }

    public void displayDoneTask(Task task) {
        System.out.println("This task is marked as done");
        System.out.println(task);
        System.out.println("____________________________________________________________");
    }

    public void displayNotDoneTask(Task task) {
        System.out.println("This task is marked as not done");
        System.out.println(task);
        System.out.println("____________________________________________________________");
    }

    public void displayTasksOnDate(String tasks) {
        System.out.println(tasks);
        System.out.println("____________________________________________________________");
    }

    public void displayException(DukeException exception) {
        System.out.println(exception.getMessage());
        System.out.println("____________________________________________________________");
    }
}
