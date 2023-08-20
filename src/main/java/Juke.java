import java.util.Scanner;
import java.util.ArrayList;

public class Juke {
    static void printLine() {
        System.out.println("_______________________________________________________");
    }
    public static void main(String[] args) {
        class Task {
            private final String type;
            private final String desc;

            public Task(String type, String desc) {
                this.type = type;
                this.desc = desc;
            }

            @Override
            public String toString() {
                return desc;
            }
        }
        ArrayList<Task> tasks = new ArrayList<Task>();

        //Introduce itself to the user
        System.out.println("Hello! I'm Juke!");
        System.out.println("What can I do for you?");
        printLine();
        while(true) {
            Scanner scanner = new Scanner(System.in);  // Create a Scanner object
            String input = scanner.nextLine();
            if (input.equalsIgnoreCase("bye")) {
                //Say goodbye
                System.out.println("Bye. Hope to see you again soon!");
                printLine();
                scanner.close();  // Close the scanner before exiting
                return;
            }

            if (input.equalsIgnoreCase("list")) {
                int count = 1;
                for (Task task : tasks) {
                    System.out.println(count + ": " + task.toString());
                    count++;
                }
                printLine();
            }
            else {
                tasks.add(new Task("todo", input));
                System.out.println("added: " + input);
                printLine();
            }
        }
    }
}
