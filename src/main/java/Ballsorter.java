import java.util.Scanner;

public class Ballsorter {

    public static void main(String[] args) {

        String line = "____________________________________________________________";
        Task[] tasks = new Task[100];
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

                System.out.println("Here are the tasks in your list:");
                for (int i = 0; i < numberOfTasks; i++) {
                    int temp = i + 1;
                    System.out.println(temp + ". " + tasks[i].toString());
                }
                System.out.println(line);

            } else if (input.startsWith("mark")) {

                int target = Integer.parseInt(input.substring(5)) - 1;
                System.out.println("Nice! I've marked this task as done:");
                System.out.println(tasks[target].markDone());
                System.out.println(line);

            } else if (input.startsWith("unmark")) {

                int target = Integer.parseInt(input.substring(7)) - 1;
                System.out.println("OK, I've marked this task as not done yet:");
                System.out.println(tasks[target].markNotDone());
                System.out.println(line);

            } else {

                tasks[numberOfTasks] = new Task(input);
                numberOfTasks++;

                System.out.println("added: " + input);
                System.out.println(line);

            }
        }

        System.out.println("Bye. Hope to see you again soon!");
        System.out.println(line);
    }
}
