import java.util.Scanner;

public class Tired {
    public static void main(String[] args) throws DukeException {

        Task[] list = new Task[100];
        int pos = 0;

        String name = "Tired";
        String horizontalLine = "____________________________________________________________";

        System.out.println(horizontalLine);
        System.out.println("Hello! I'm " + name);
        System.out.println("What can I do for you?");
        System.out.println(horizontalLine + "\n");

        Scanner sc = new Scanner(System.in);
        String input = sc.nextLine();

        while (!input.equals("bye")) {

            System.out.println(horizontalLine);
            try {
                if (input.equals("list")) {
                    System.out.println("Here are the tasks in your list: ");
                    for (int i = 0; i < pos; i++) {
                        System.out.println((i + 1) + "." + list[i].toString());
                    }
                } else if (input.startsWith("mark")) {
                    int taskIndex = Integer.parseInt(input.substring(5).trim()) - 1;
                    if (taskIndex >= 0 && taskIndex < pos) {
                        list[taskIndex].markAsDone();
                        System.out.println("Nice! I've marked this task as done:");
                        System.out.println(list[taskIndex].toString());
                    }
                } else if (input.startsWith("unmark")) {
                    int taskIndex = Integer.parseInt(input.substring(7).trim()) - 1;
                    if (taskIndex >= 0 && taskIndex < pos) {
                        list[taskIndex].markAsUndone();
                        System.out.println("OK, I've marked this task as not done yet:");
                        System.out.println(list[taskIndex].toString());
                    }
                } else {
                    if (input.startsWith("todo")) {
                        String s = input.substring(4).trim();
                        Task t = new ToDo(s);
                        list[pos] = t;
                        pos += 1;
                        System.out.println("Got it. I've added this task:");
                        System.out.println(t.toString());
                    } else if (input.startsWith("deadline")) {
                        String[] parts = input.split("/");
                        if (parts.length != 2 || parts[0].length() < 8
                                || parts[1].length() < 2 ) {
                            // prevent java.lang.StringIndexOutOfBoundsException
                            throw new DukeException("Invalid input for a task with deadline. " +
                                    "Please input 'deadline <task name> /by <end>'");
                        }
                        String task = parts[0].substring(8).trim();
                        String date = parts[1].substring(2).trim();
                        Task t = new Deadline(task, date);
                        list[pos] = t;
                        pos += 1;
                        System.out.println("Got it. I've added this task:");
                        System.out.println(t.toString());
                    } else if ((input.startsWith("event"))) {
                        String[] parts = input.split("/");
                        if (parts.length != 3 || parts[0].length() < 6
                                || parts[1].length() < 5 || parts[2].length() < 3) {
                            // prevent java.lang.StringIndexOutOfBoundsException
                            throw new DukeException("Invalid input for an event. " +
                                    "Please input 'event <event name> /from <start> /to <end>'");
                        }
                        String task = parts[0].substring(5).trim();
                        String start = parts[1].substring(5).trim();
                        String end = parts[2].substring(3).trim();
                        Task t = new Event(task, start, end);
                        list[pos] = t;
                        pos += 1;
                        System.out.println("Got it. I've added this task:");
                        System.out.println(t.toString());
                    } else {
                        throw new DukeException("I'm sorry, but I don't know what that means :-(");
                    }
                }
                System.out.println("Now you have " + (pos) + " task(s) in the list.");
                System.out.println(horizontalLine + "\n");
            } catch (DukeException e) {
                System.out.println(e.getMessage());
                System.out.println(horizontalLine + "\n");
            }
            input = sc.nextLine();
        }
        System.out.println(horizontalLine);
        System.out.println("Bye. Hope to see you again soon!");
        System.out.println(horizontalLine);
    }
}