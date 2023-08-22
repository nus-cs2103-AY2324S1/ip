import java.util.Scanner;

public class Duke {
    public static void main(String[] args) {
        // String logo = " ____ _ \n"
        // + "| _ \\ _ _| | _____ \n"
        // + "| | | | | | | |/ / _ \\\n"
        // + "| |_| | |_| | < __/\n"
        // + "|____/ \\__,_|_|\\_\\___|\n";
        // System.out.println("Hello from\n" + logo);

        // Print greeting
        String lnspace = "____________________________________________________________";
        String greeting = lnspace + "\n"
                + "Hello! I'm Lorem\n"
                + "What can I do for you?\n"
                + lnspace + "\n";

        System.out.println(greeting);

        // initialise scanner to detect user input
        Scanner sc = new Scanner(System.in);
        String userInput = sc.next();

        // initialise array to store user input
        TaskList arr = new TaskList();

        // while loop to continuously take in inputs until user types bye
        while (!userInput.equals("bye")) {

            // if user asks for list
            if (userInput.equals("list")) {
                System.out.println(lnspace);
                System.out.println("Here are the tasks in your list:");
                for (int i = 0; i < arr.length(); i++) {
                    System.out.printf("%d. %s\n", i + 1, arr.taskToString(i));
                }
                System.out.println(lnspace);
                userInput = sc.next();
                continue;
            }

            // if user wants to mark task
            if (userInput.equals("mark")) {
                int markTask = Integer.parseInt(sc.next());
                arr.markTaskAsDone(markTask - 1);
                System.out.println(lnspace);
                System.out.println("Nice! I've marked this task as done:");
                System.out.println(arr.taskToString(markTask - 1));
                System.out.println(lnspace);
                userInput = sc.next();
                continue;
            }

            // if user wants to unmark task
            if (userInput.equals("unmark")) {
                int unmarkTask = Integer.parseInt(sc.next());
                arr.markTaskAsNotDone(unmarkTask - 1);
                System.out.println(lnspace);
                System.out.println("OK, I've marked this task as not done yet:");
                System.out.println(arr.taskToString(unmarkTask - 1));
                System.out.println(lnspace);
                userInput = sc.next();
                continue;
            }

            // if not continue to listen for further user inputs
            arr.addTask(userInput);
            System.out.println(lnspace);
            System.out.println("added: " + userInput);
            System.out.println(lnspace);
            userInput = sc.next();
        }

        // terminate program: close scanner, print ending message
        sc.close();
        String ending = lnspace + "\n"
                + "Bye. Hope to see you again soon!\n"
                + lnspace;
        System.out.println(ending);
    }
}
