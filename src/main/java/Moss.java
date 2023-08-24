import java.util.ArrayList;
import java.util.Scanner;

public class Moss {
    static ArrayList<Task> things = new ArrayList<>();
    public static void main(String[] args) {

        String greet = "____________________________________________________________\n"
                + "Hello! I'm Moss \n"
                + "What can I do for you? \n"
                + "____________________________________________________________\n";
        System.out.println(greet);
        Scanner sc = new Scanner(System.in);
        String message = sc.nextLine();
        while (!message.equals("bye")) {
            if (message.equals("list")){
                System.out.println("____________________________________________________________");
                System.out.println("Here are the tasks in your list:");
                for (int i = 0; i < things.size(); i++) {
                    System.out.println(i+1 + "." + things.get(i).toString());
                }
                System.out.println("____________________________________________________________");
            }
            else if (message.startsWith("mark")) {
                String indexSubstring = message.substring(5);
                int index = Integer.parseInt(indexSubstring) - 1;
                things.get(index).markDone();
            }
            else if (message.startsWith("unmark")) {
                String indexSubstring = message.substring(7);
                int index = Integer.parseInt(indexSubstring) - 1;
                things.get(index).markUndone();
            }
            else {
                add(message);
            }
            message = sc.nextLine();
        }
        System.out.println("____________________________________________________________");
        System.out.println("Bye. Hope to see you again soon!");
        System.out.println("____________________________________________________________");
    }

    /**
     * Adds a new task with the given description to the list of tasks.
     *
     * @param message The description of the task to be added.
     */
    public static void add(String message) {
        things.add(new Task(message));
        System.out.println("____________________________________________________________");
        System.out.println("added: " + message);
        System.out.println("____________________________________________________________");
    }
}
