import java.util.Scanner;
import java.util.ArrayList;

public class James {
    public static void main(String[] args) {
        String line = "____________________________________________________________";
        System.out.println(line + "\n" + "Hello! I'm James\n" + "What can I do for you?\n" + line);

        // User Input
        Scanner in = new Scanner(System.in);

        ArrayList<Task> items = new ArrayList<Task>();

        String input = in.nextLine();
        while (!input.equals("bye")) {
            if (input.equals("list")) {
                System.out.println(line);
                for (int i = 0; i < items.size(); i++) {
                    System.out.println(i + 1 + "." + items.get(i));
                }
            } else if (input.contains("unmark")) {
                Integer taskIdx = Integer.parseInt(input.split(" ")[1]);
                Task task = items.get(taskIdx - 1);
                task.unmark();
                System.out.println(line + "\n" + "OK! I've marked this task as not done yet:");
                System.out.println(task + "\n" + line);
            } else if (input.contains("mark")) {
                Integer taskIdx = Integer.parseInt(input.split(" ")[1]);
                Task task = items.get(taskIdx - 1);
                task.mark();
                System.out.println(line + "\n" + "Nice! I've marked this task as done:");
                System.out.println(task + "\n" + line);
            } else {
                System.out.println(line + "\n" + "added: " + input + "\n" + line);
                items.add(new Task(input));
            }
            input = in.nextLine();
        }

        System.out.println(line + "\nBye. Hope to see you again soon!\n" + line);

    }
}
