import java.util.Scanner;

public class Ballsorter {

    public static void main(String[] args) {

        String line = "____________________________________________________________";
        Task[] tasks = new Task[100];
        int numberOfTasks = 0;

        System.out.println(line);
        System.out.println("Hello! I'm Ballsorter\nWhat can I do for you?");
        System.out.println(line);

        Scanner sc = new Scanner(System.in);

        while (sc.hasNextLine()) {

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

                Task curr;
                StringBuilder description = new StringBuilder();
                StringBuilder start = new StringBuilder();

                if (input.startsWith("todo")) {

                    curr = new Todo(input.substring(5));

                } else if (input.startsWith("deadline")) {

                    int i = 9;
                    while (input.charAt(i) != '/') {
                        description.append(input.charAt(i));
                        i++;
                    }
                    i += 4;
                    curr = new Deadline(description.toString(), input.substring(i));

                } else if (input.startsWith("event")) {

                    int i = 6;
                    while (input.charAt(i) != '/') {
                        description.append(input.charAt(i));
                        i++;
                    }
                    i += 6;
                    while (input.charAt(i) != '/') {
                        start.append(input.charAt(i));
                        i++;
                    }
                    i += 4;
                    curr = new Event(description.toString(), start.toString(), input.substring(i));

                } else {
                    System.out.println("Command not recognised :(");
                    curr = null;
                }

                tasks[numberOfTasks] = curr;
                numberOfTasks++;

                System.out.println("Got it. I've added this task:");
                System.out.println(curr.toString());
                System.out.println("Now you have " + numberOfTasks + " tasks in the list.");
                System.out.println(line);

            }
        }

        System.out.println("Bye. Hope to see you again soon!");
        System.out.println(line);
    }
}
