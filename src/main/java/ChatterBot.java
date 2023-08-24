import java.util.ArrayList;
import java.util.Scanner;

public class ChatterBot {
    public static void main(String[] args) {

        String logo = "ChatterBot";
        System.out.println("Hello! I'm " + logo + "\nWhat can I do for you?");

        ArrayList<Task> list = new ArrayList<>();

        while (true) {
            Scanner scanner = new Scanner(System.in);
            String userMessage = scanner.nextLine();

            if (userMessage.toLowerCase().equals("bye")) {
                System.out.println("Bye. Hope to see you again soon!");
                break;
            } else if (userMessage.toLowerCase().equals("list")) {
                System.out.println("Here are the tasks in your list:");
                for (Task t : list) {
                    System.out.println((list.indexOf(t) + 1) + ". "
                            + "[" + t.getStatusIcon() + "] " + t.description);
                }
            } else if (userMessage.startsWith("mark") && isInteger(userMessage.substring(5))) {
                String toMark = userMessage.substring(5);
                list.get(Integer.parseInt(toMark) - 1).markAsDone();
                System.out.println("Nice! I've marked this task as done:\n" + "[X] " + list.get(Integer.parseInt(toMark) - 1).description);
            } else if (userMessage.startsWith("unmark")) {
                String toUnmark = userMessage.substring(7);
                list.get(Integer.parseInt(toUnmark) - 1).markAsUndone();
                System.out.println("OK, I've marked this task as not done yet:\n" + "[ ] " + list.get(Integer.parseInt(toUnmark) - 1).description);
            } else {
                Task t = new Task(userMessage);
                list.add(t);
                System.out.println("added " + userMessage);
            }
        }
    }

    public static boolean isInteger(String str) {
        try {
            Integer.parseInt(str);
            return true;
        } catch (NumberFormatException e) {
            return false;
        }
    }
}