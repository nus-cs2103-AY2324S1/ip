import java.util.ArrayList;
import java.util.Scanner;

public class Duke {

    String name = "Meg";
    boolean isRunning = true;
    ArrayList<String> tasks = new ArrayList<>();
    int numOfTasks = 0;
    public Duke() {
        String intro1 = String.format("I'm %s. You called me?" +
                "\n", this.name);
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

    private void printHorizontalLine() {
        for (int i = 0; i < 20; i++) {
            System.out.print("-");
        }
        System.out.println();
    }

    private void exit() {
        System.out.println("Don't let me see you again!");
        printHorizontalLine();
        System.exit(0);
    }

    private void readInput(String message) {
        if (message.equals("bye")) {
            this.isRunning = false;
        } else if (message.equals("list")) {
            this.list();
        } else {
            tasks.add(message);
            numOfTasks++;
            String output = String.format("Don't expect me to %s for you!", message);
            System.out.println(output + "\n");
            printHorizontalLine();
        }
    }

    private void list() {
        String output = String.format("You have %d tasks.", numOfTasks);
        System.out.println(output);
        for (int i = 0; i < numOfTasks; i++) {
            System.out.printf("%d. " + tasks.get(i) + "%n", i+1);
        }
        System.out.println("Don't expect me to remember them for you!");
        printHorizontalLine();
    }

    public static void main(String[] args) {
        new Duke();
    }
}
