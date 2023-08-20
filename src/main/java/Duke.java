import java.util.ArrayList;
import java.util.Scanner;

public class Duke {
    public static void main(String[] args) {
        ArrayList<Task> list = new ArrayList<>();
        boolean exit = false;
        String line = "\t____________________________________________________________";
        System.out.println(line);
        System.out.println("\t Hello! I'm Violet");
        System.out.println("\t What can I do for you?");
        System.out.println(line);

        while (!exit) {
            Scanner sc = new Scanner(System.in);
            String command = sc.nextLine();
            Task task = new Task(command);
            System.out.println(line);
            if (command.equals("bye")) {
                System.out.println("\t Bye. Hope to see you again soon!");
                exit = true;
            } else if (command.equals("list")) {
                System.out.println("\t Here are the tasks in your list:");
                for (int i = 0; i < list.size(); i++) {
                    System.out.println("\t " + (i + 1) + "." + list.get(i).toString());
                }
            } else if (command.startsWith("mark")) {
                int idx = Character.getNumericValue(command.charAt(5));
                Task t = list.get(idx - 1);
                t.markAsDone();
                System.out.println("\t Nice! I've marked this task as done:");
                System.out.println("\t\t" + t.toString());
            } else if (command.startsWith("unmark")) {
                int idx = Character.getNumericValue(command.charAt(7));
                Task t = list.get(idx - 1);
                t.markAsUndone();
                System.out.println("\t OK, I've marked this task as not done yet:");
                System.out.println("\t\t" + t.toString());
            } else {
                list.add(task);
                System.out.println("\t added: " + command);
            }
            System.out.println(line);
        }
    }
}
