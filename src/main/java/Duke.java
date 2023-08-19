import java.util.ArrayList;
import java.util.Scanner;

public class Duke {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        ArrayList<Task> lst = new ArrayList<>();
        String divider = "_____________________________________\n";
        String opening = "Hello: I'm TY's slave\nWhat can I do for you?\n";
        String closing = "Bye. Hope to see you again soon!";
        System.out.println(divider + opening + divider);
        while (true) {
            String input = sc.nextLine();
            if (input.equals("bye")) {
                System.out.println(divider + closing + "\n" + divider);
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
                System.out.println("Here are the tasks in your list");
                    for (int i = 0; i < lst.size(); i++) {
                        System.out.println((i + 1) + ". " + lst.get(i));
                    }
            } else {
                lst.add(new Task(input));
                System.out.println("added: " + input);
            }
        }
    }
}
