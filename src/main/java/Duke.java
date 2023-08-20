import java.util.ArrayList;
import java.util.Scanner;

public class Duke {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        ArrayList<Task> lst = new ArrayList<>();
        String divider = "____________________________________________________________";
        String opening = "Hello: I'm TY's slave\nWhat can I do for you?\n";
        String closing = "Bye. Hope to see you again soon!";
        System.out.println(divider + "\n" + opening + divider + "\n");
        while (true) {
            String input = sc.nextLine();
            if (input.equals("bye")) {
                System.out.println(divider + "\n" + closing + "\n" + divider);
                break;
            } else if (input.startsWith("mark ")) {
                int idx = Integer.parseInt(input.substring(5));
                lst.get(idx - 1).markDone();
                System.out.println("Nice! I've marked this task as done");
                System.out.println("\t" + lst.get(idx - 1));
            } else if (input.startsWith("unmark ")) {
                int idx = Integer.parseInt(input.substring(7));
                lst.get(idx - 1).unMarkDone();
                System.out.println("OK, I've marked this task as not done yet:");
                System.out.println("\t" + lst.get(idx - 1));
            } else if (input.equals("list")) {
                System.out.println(divider + "\n" + "Here are the tasks in your list:");
                    for (int i = 0; i < lst.size(); i++) {
                        System.out.println((i + 1) + "." + lst.get(i));
                    }
                System.out.println(divider + "\n");
            } else {
                String[] arr = input.split(" ", 2);
                Task task;
                switch (arr[0]) {
                    case "todo":
                        task = new ToDo(arr[1]);
                        lst.add(task);
                        break;
                    case "deadline":
                        String[] arr2 = arr[1].split(" /by ");
                        task = new Deadline(arr2[0], arr2[1]);
                        lst.add(task);
                        break;
                    default:
                        String[] arr3 = arr[1].split(" /");
                        String taskName = arr3[0];
                        String from = arr3[1].substring(5);
                        String to = arr3[2].substring(3);
                        task = new Event(taskName, from, to);
                        lst.add(task);
                        break;
                }
                String output = divider + "\n"
                        + "Got it. I've added this task:\n"
                        + "    " + task.toString()
                        + "\nNow you have " + Integer.toString(lst.size())
                        + " tasks in the list.\n"
                        + divider + "\n";
                System.out.println(output);
            }
        }
    }
}
