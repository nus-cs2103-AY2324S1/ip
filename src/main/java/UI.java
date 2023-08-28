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
        System.out.println("Bye");
        System.out.println("____________________________________________________________");
    }

    public void displayList(ArrayList<Task> list) {
        if (list.isEmpty()) {
            System.out.println("There are no tasks in the list");
        }
        for (int i = 1; i <= list.size(); ++i) {
            System.out.printf("%d. %s%n", i, list.get(i - 1));
        }
    }

    public void displayAddToList(Task task, int totalSize) {
        System.out.println("This task is added to the list");
        System.out.println(task);
        System.out.printf("You now have %d tasks in your list%n", totalSize);
    }

    public void displayRemoveFromList(Task task, int totalSize) {
        System.out.println("This task is deleted from the list");
        System.out.println(task);
        System.out.printf("You now have %d tasks in your list%n", totalSize);
    }

    public void displayDoneTask(Task task) {
        System.out.println("This task is marked as done");
        System.out.println(task);
    }

    public void displayNotDoneTask(Task task) {
        System.out.println("This task is marked as not done");
        System.out.println(task);
    }
}
