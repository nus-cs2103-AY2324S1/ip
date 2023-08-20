import java.util.Scanner;
import java.util.ArrayList;

public class Juke {
    static void printLine() {
        System.out.println("_______________________________________________________");
    }
    public static void main(String[] args) {
        class Task {
            protected final String type;
            protected final String desc;
            protected boolean isDone;

            public Task(String type, String desc) {
                this.type = type;
                this.desc = desc;
                this.isDone = false;
            }

            public String getStatusIcon() {
                return (isDone ? "[X] " : "[ ] "); // mark done task with X
            }

            public void markAsDone() {
                this.isDone = true;
            }

            public void markAsUndone() {
                this.isDone = false;
            }

            @Override
            public String toString() {
                return this.getStatusIcon() + desc;
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
                continue;
            }

            if (input.contains("unmark")) {
                int index = Integer.parseInt(input.substring(7));
                Task currTask = tasks.get(index - 1);
                currTask.markAsUndone();
                System.out.println("OK, I've marked this task as not done yet: \n" + currTask.toString());
                printLine();
                continue;
            }

            if (input.contains("mark")) {
                int index = Integer.parseInt(input.substring(5));
                Task currTask = tasks.get(index - 1);
                currTask.markAsDone();
                System.out.println("Nice! I've marked this task as done: \n" + currTask.toString());
                printLine();
                continue;
            }

            else {
                tasks.add(new Task("todo", input));
                System.out.println("added: " + input);
                printLine();
            }
        }
    }
}
