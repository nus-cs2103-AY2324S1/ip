import java.util.Scanner;

public class Dukduk {
    public static void main(String[] args) {
        printLine();
        System.out.println(" Hello! I'm Dukduk");
        System.out.println(" What can I do for you?");
        printLine();

        String[] tasks = new String[100];
        int taskCount = 0;
        Scanner scanner = new Scanner(System.in);
        while (true) {
            System.out.print(" ");
            String input = scanner.nextLine();
            printLine();

            if (input.equalsIgnoreCase("bye")) {
                System.out.println(" Bye. Hope to see you again soon!");
                printLine();
                break;
            } else if (input.equalsIgnoreCase("list")) {
                if (taskCount == 0) {
                    System.out.println(" No tasks added yet.");
                } else {
                    for (int i = 0; i < taskCount; i++) {
                        System.out.println(" " + (i + 1) + ". " + tasks[i]);
                    }
                }
            } else {
                tasks[taskCount] = input;
                taskCount++;
                System.out.println(" added: " + input);
            }
            printLine();
        }
        scanner.close();
    }

    public static void printLine() {
        System.out.println("____________________________________________________________");
    }
}
