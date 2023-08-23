import java.util.ArrayList;
import java.util.Scanner;

public class Duke {
    public static void main(String[] args) {
        Scanner scan = new Scanner(System.in);
        ArrayList<Task> list = new ArrayList<Task>();
        System.out.println("    ____________________________________________________________");
        System.out.println("     " + "Hello! I'm ChatGP0");
        System.out.println("     " + "What can I do for you?");
        System.out.println("    ____________________________________________________________\n");
        String input = scan.nextLine();
        while (!input.equals("bye")) {
            if (input.equals("list")) {
                System.out.println("    ____________________________________________________________");
                System.out.println("     " + "Here are the tasks in your list:");
                for (int i = 0; i < list.size(); i++) {
                    Task task = list.get(i);
                    System.out.println("     " + (i + 1) + ".[" + task.getStatusIcon() + "] " + task.getDescription());
                }
                System.out.println("    ____________________________________________________________");
            } else if (input.startsWith("mark ") && input.length() > 5 && input.substring(5).matches("\\d+")) {
                int number = Integer.parseInt(input.substring(5));
                if (number <= list.size() && number > 0) {
                    Task task = list.get(number - 1);
                    task.mark();
                    System.out.println("    ____________________________________________________________");
                    System.out.println("     " + "Nice! I've marked this task as done:");
                    System.out.println("       [X] " + task.getDescription());
                    System.out.println("    ____________________________________________________________");
                } else {
                    System.out.println("    ____________________________________________________________");
                    System.out.println("     " + "added: " + input);
                    System.out.println("    ____________________________________________________________\n");
                    list.add(new Task(input));
                }
            } else if (input.startsWith("unmark ") && input.length() > 7 && input.substring(7).matches("\\d+")) {
                int number = Integer.parseInt(input.substring(7));
                if (number <= list.size() && number > 0) {
                    Task task = list.get(number - 1);
                    task.unmark();
                    System.out.println("    ____________________________________________________________");
                    System.out.println("     " + "OK, I've marked this task as not done yet:");
                    System.out.println("       [ ] " + task.getDescription());
                    System.out.println("    ____________________________________________________________");
                } else {
                    System.out.println("    ____________________________________________________________");
                    System.out.println("     " + "added: " + input);
                    System.out.println("    ____________________________________________________________\n");
                    list.add(new Task(input));
                }
            } else {
                System.out.println("    ____________________________________________________________");
                System.out.println("     " + "added: " + input);
                System.out.println("    ____________________________________________________________\n");
                list.add(new Task(input));
            }
            input = scan.nextLine();
        }
        System.out.println("    ____________________________________________________________");
        System.out.println("     " + "Bye. Hope to see you again soon!");
        System.out.println("    ____________________________________________________________");
    }
}