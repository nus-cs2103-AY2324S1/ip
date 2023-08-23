
import Tasks.Task;
import java.util.ArrayList;
import java.util.Scanner;
public class Duke {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        ArrayList<Task> tasks = new ArrayList<Task>(100);
        int counter = 0;
        String logo =
                  "\n" +
                          "                                                     \n" +
                          "     / /                                             \n" +
                          "    / /         ___      _   __      ___       __    \n" +
                          "   / /        //___) ) // ) )  ) ) //   ) ) //   ) ) \n" +
                          "  / /        //       // / /  / / //   / / //   / /  \n" +
                          " / /____/ / ((____   // / /  / / ((___/ / //   / /   \n";
        System.out.println("Hello! I'm " + logo + "! \nWhat can I do for you?");
        String input = scanner.nextLine();
        while (!input.equals("bye")) {
            if (input.equals("list")) {
                System.out.println("Here are the tasks in your list: \n");
                for (int i = 0; i < counter; i++ ) {
                    Task nextTask = tasks.get(i);
                    System.out.println(i + 1 + ". [" + nextTask.getStatusIcon() + "] " + nextTask.getTaskDesc());
                }
            } else if (input.split(" ")[0].equals("mark")) {
                int indexToMark = Integer.valueOf(input.split(" ")[1]);
                Task markedTask = tasks.get(indexToMark - 1);
                markedTask.markAsDone();
                System.out.println("Nice! I've marked this task as done: \n [" + markedTask.getStatusIcon() + "] " + markedTask.getTaskDesc() + "\n");
            }  else if (input.split(" ")[0].equals("unmark")) {
                int indexToMark = Integer.valueOf(input.split(" ")[1]);
                Task markedTask = tasks.get(indexToMark - 1);
                markedTask.markAsUndone();
                System.out.println("OK, I've marked this task as not done yet: \n [" + markedTask.getStatusIcon() + "] " + markedTask.getTaskDesc() + "\n");
            }
            else {
                System.out.println("added: " + input);
                tasks.add(new Task(input));
                counter++;
            }
            input = scanner.nextLine();
        }
        System.out.println("Bye. Hope to see you again soon!");
    }
}
