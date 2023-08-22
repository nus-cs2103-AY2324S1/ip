import java.util.Scanner;
import java.util.ArrayList;

public class Duke {
    private static String horizontalLine = "____________________________________________________________";
    private String myName = "Quack-NKN";
    private Scanner scanner = new Scanner(System.in);
    private ArrayList<Task> taskList = new ArrayList<>();

    public Duke() {
        interact();
    }

    private void start() {
        System.out.println(Duke.horizontalLine);
        System.out.print("Hello from ");
        System.out.println(this.myName);
        System.out.println("What can I do for you?");
        System.out.println(Duke.horizontalLine);
    }

    private void exit() {
        System.out.println("Bye. Hope to see you again soon!");
        System.out.println(Duke.horizontalLine);
    }

    private void interact() {
        this.start();
        while (true) {
            // receive input
            String command = this.scanner.nextLine();
            System.out.println(Duke.horizontalLine);

            // exit
            if (command.equals("bye")) {
                break;
            }

            // show list
            if (command.equals("list")) {
                this.showList();
            }

            // add to list
            else {
                this.addToList(command);
            }

            System.out.println(Duke.horizontalLine);
        }
        this.exit();
    }

    private void addToList(String text) {
        Task newTask = new Task(text);
        this.taskList.add(newTask);
        System.out.println("added: " + newTask);
    }

    private void showList() {
        for (int i = 0; i < this.taskList.size(); i++) {
            System.out.println((i + 1) + ". " + this.taskList.get(i));
        }
    }

    public static void main(String[] args) {
        new Duke();
    }
}
