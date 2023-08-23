import java.util.Scanner;
import java.util.ArrayList;

public class Cupid {
    public static void main(String[] args) {
        String[] tasks = new String[100];
        int taskCounter = 0;

        System.out.println("____________________________________________________________");
        Scanner scanner = new Scanner(System.in);
        System.out.println("Hello, I'm Cupid.");
        System.out.println("What can I do for you?");

        while (true) {
            String input = scanner.nextLine();
            if (input.toLowerCase().equals("bye")) {
                break;
            }

            if (input.toLowerCase().equals("list")) {
                for (int i=1; i<taskCounter+1; i++) {
                    System.out.println(i + ". " +  tasks[i-1]);
                }
                continue;
            }

            tasks[taskCounter] = input;
            System.out.println("Added: " + input);
            taskCounter++;
        }



        System.out.println("____________________________________________________________");
        System.out.println("Bye. Hope to see you again soon!");
        System.out.println("____________________________________________________________");
    }
}
