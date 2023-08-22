import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Duke {
    public static void main(String[] args) {
        System.out.println("\t-----------------------------------------------");
        System.out.println("\tSup bro! I'm Brobot");
        System.out.println("\tWhat can I do for you?");
        System.out.println("\t-----------------------------------------------");
        Scanner scanner = new Scanner(System.in);
        List<Task> list = new ArrayList<>();

        while (true) {
            String input = scanner.nextLine();
            String[] words = input.split(" ");

            if (input.equals("bye")) {
                // exit program
                break;
            } else if (input.equals("list")) {
                // display current list
                System.out.println("\t-----------------------------------------------");
                System.out.println("\tHere are the tasks in your list:");
                for (int i = 0; i < list.size(); i++) {
                    System.out.println("\t" + (i + 1) + ".[" + list.get(i).getStatusIcon() + "] " + list.get(i));
                }
                System.out.println("\t-----------------------------------------------");

            } else if (words[0].equals("mark")) {
                // mark task as done
                int index = Integer.parseInt(words[1]) - 1;

                if (index < 0 || index >= list.size()) {
                    // handle invalid input
                    System.out.println("Invalid number ");
                } else {
                    list.get(index).markAsDone();
                    System.out.println("\t-----------------------------------------------");
                    System.out.println("\tNice! I've marked this task as done: ");
                    System.out.println("\t[" + list.get(index).getStatusIcon() + "] " + list.get(index));
                    System.out.println("\t-----------------------------------------------");
                }


            } else if (words[0].equals("unmark")) {
                // mark task as undone
                int index = Integer.parseInt(words[1]) - 1;

                if (index < 0 || index >= list.size()) {
                    // handle invalid input
                    System.out.println("Invalid number ");
                } else {
                    list.get(index).unMark();
                    System.out.println("\t-----------------------------------------------");
                    System.out.println("\tNice! I've marked this task as not done yet: ");
                    System.out.println("\t[" + list.get(index).getStatusIcon() + "] " + list.get(index));
                    System.out.println("\t-----------------------------------------------");
                }

            } else {
                // add task
                Task task = new Task(input);
                list.add(task);
                System.out.println("\t-----------------------------------------------");
                System.out.println("\tadded: " + input);
                System.out.println("\t-----------------------------------------------");
            }
        }

        System.out.println("\t-----------------------------------------------");
        System.out.println("\tBye. Hope to see you again soon bro!");
        System.out.println("\t-----------------------------------------------");
    }
}
