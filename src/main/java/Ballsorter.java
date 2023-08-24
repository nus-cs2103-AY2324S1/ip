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

                if (numberOfTasks == 0) {
                    System.out.println("You do not have any tasks yet ☹");
                }

                for (int i = 0; i < numberOfTasks; i++) {
                    int temp = i + 1;
                    System.out.println(temp + ". " + tasks[i].toString());
                }
                System.out.println(line);

            } else if (input.startsWith("mark")) {

                //error: already marked todos cant be marked again

                int target = Integer.parseInt(input.substring(5)) - 1;
                System.out.println("Nice! I've marked this task as done:");
                System.out.println(tasks[target].markDone());
                System.out.println(line);

            } else if (input.startsWith("unmark")) {

                //error: already unmarked todos cant be unmarked again

                int target = Integer.parseInt(input.substring(7)) - 1;
                System.out.println("OK, I've marked this task as not done yet:");
                System.out.println(tasks[target].markNotDone());
                System.out.println(line);

            } else {

                Task curr;
                StringBuilder description = new StringBuilder();
                StringBuilder start = new StringBuilder();

                if (input.startsWith("todo")) {

                    String des = input.substring(4).trim();
                    if (des.equals("")) {
                        System.out.println("☹ OOPS!!! The description of a todo cannot be empty.");
                        System.out.println(line);
                        curr = null;
                    } else {
                        curr = new Todo(des);
                    }

                } else if (input.startsWith("deadline")) {

                    int i = 9;
                    while (i < input.length() && input.charAt(i) != '/') {
                        description.append(input.charAt(i));
                        i++;
                    }
                    i += 4;
                    if (description.toString().equals("")) {
                        System.out.println("☹ OOPS!!! The description of a deadline cannot be empty.");
                        System.out.println(line);
                        curr = null;
                    } else if (i >= input.length() || input.substring(i).equals("")) {
                        System.out.println("☹ OOPS!!! The deadline of a deadline cannot be empty.");
                        System.out.println(line);
                        curr = null;
                    } else {
                        curr = new Deadline(description.toString(), input.substring(i));
                    }


                } else if (input.startsWith("event")) {

                    int i = 6;
                    while (i < input.length() && input.charAt(i) != '/') {
                        description.append(input.charAt(i));
                        i++;
                    }
                    i += 6;
                    while (i < input.length() && input.charAt(i) != '/') {
                        start.append(input.charAt(i));
                        i++;
                    }
                    i += 4;
                    if (description.toString().equals("")) {
                        System.out.println("☹ OOPS!!! The description of an event cannot be empty.");
                        System.out.println(line);
                        curr = null;
                    } else if (start.toString().equals("")) {
                        System.out.println("☹ OOPS!!! The start time of an event cannot be empty.");
                        System.out.println(line);
                        curr = null;
                    } else if (i >= input.length() || input.substring(i).equals("")) {
                        System.out.println("☹ OOPS!!! The end time of an event cannot be empty.");
                        System.out.println(line);
                        curr = null;
                    } else {
                        curr = new Event(description.toString(), start.toString(), input.substring(i));
                    }

                } else {
                    System.out.println("☹ OOPS!!! I'm sorry, but I don't know what that means :-(");
                    System.out.println(line);
                    curr = null;
                }

                if (curr != null) {
                    tasks[numberOfTasks] = curr;
                    numberOfTasks++;

                    System.out.println("Got it. I've added this task:");
                    System.out.println(curr.toString());
                    System.out.println("Now you have " + numberOfTasks + " tasks in the list.");
                    System.out.println(line);
                }
            }
        }

        System.out.println("Bye. Hope to see you again soon!");
        System.out.println(line);
    }
}
