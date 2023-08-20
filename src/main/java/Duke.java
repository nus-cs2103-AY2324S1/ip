import java.util.ArrayList;
import java.util.Scanner;

public class Duke {

    private boolean isRunning = true;
    private final ArrayList<Task> tasks = new ArrayList<>();
    private int numOfTasks = 0;
    public Duke() {
        String name = "Meg";
        String intro1 = String.format("I'm %s. You called me?" +
                "\n", name);
        String intro2 = "Make it quick, thanks.";
        System.out.println(intro1);
        System.out.println(intro2 + "\n");
        printHorizontalLine();
        Scanner sc = new Scanner(System.in);
        while (this.isRunning) {
            String msg = sc.nextLine();
            readInput(msg);
        }
        exit();
    }

    public void printHorizontalLine() {
        for (int i = 0; i < 20; i++) {
            System.out.print("-");
        }
        System.out.println();
    }

    public void exit() {
        System.out.println("Don't let me see you again!");
        printHorizontalLine();
        System.exit(0);
    }

    public void readInput(String message) {
        if (message.equals("bye")) {
            this.isRunning = false;
        } else if (message.equals("list")) {
            this.list();
        } else {
            tasks.add(new Task(message));
            numOfTasks++;
            String output = String.format("Don't expect me to %s for you!", message);
            System.out.println(output + "\n");
            printHorizontalLine();
        }
    }

    public void list() {
        String output = String.format("You have %d tasks.", numOfTasks);
        System.out.println(output);
        for (int i = 0; i < numOfTasks; i++) {
            System.out.printf("%d. " + tasks.get(i).toString()
                    + "%n", i+1);
        }
        System.out.println("Don't expect me to remember them for you!");
        printHorizontalLine();
    }

    public static void main(String[] args) {
        new Duke();
    }
}
