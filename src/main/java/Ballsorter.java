import java.util.Scanner;

public class Ballsorter {
    public static void main(String[] args) {
        String line = "____________________________________________________________";
        String[] tasks = new String[100];
        int numberOfTasks = 0;

        System.out.println(line);
        System.out.println("Hello! I'm Ballsorter\nWhat can I do for you?");
        System.out.println(line);

        while (true) {
            Scanner sc = new Scanner(System.in);
            String input = sc.nextLine();
            if (input.equals("bye")) {
                break;
            } else if (input.equals("list")) {
                for (int i = 0; i < numberOfTasks; i++) {
                    int temp = i + 1;
                    System.out.println(temp + ". " + tasks[i]);
                }
                System.out.println(line);
            } else {
                tasks[numberOfTasks] = input;
                numberOfTasks++;

                System.out.println("added: " + input);
                System.out.println(line);
            }
        }

        System.out.println("Bye. Hope to see you again soon!");
        System.out.println(line);
    }
}
