import java.util.Scanner;
import java.util.ArrayList;
public class Duke {

    static boolean allowNext = true;
    static boolean addStr = true;
    static ArrayList<Task> storedTasks = new ArrayList<>();

    public static void handleExit(String s) {
        String exit = "Bye. See you soon! :)\n";

        if (s.equals("bye")) {
            System.out.println(exit);
            allowNext = false;
        }
    }

    public static void handleList(String s) {
        if (s.equals("list")) {
            addStr = false;
            int len = storedTasks.size();
            if (len > 0) {
                System.out.println("Your added tasks:");

                for (int i = 1; i < len + 1; i++) {
                    System.out.println(i + ". " + storedTasks.get(i - 1));
                }
            } else {
                System.out.println("No tasks found!");
            }
        }
    }

    public static void handleMarking(String s) {
        if (s.startsWith("mark ")) {
            int index;
            int len = storedTasks.size();
            String indexStr = s.substring(5);

            try {
                index = Integer.parseInt(indexStr);
            } catch (NumberFormatException nfe) {
                return;
            }

            if (index < len + 1) {
                addStr = false;
                storedTasks.get(index - 1).markAsDone();
            }

        } else if (s.startsWith("unmark ")) {
            int index;
            int len = storedTasks.size();
            String indexStr = s.substring(7);

            try {
                index = Integer.parseInt(indexStr);
            } catch (NumberFormatException nfe) {
                return;
            }

            if (index < len + 1) {
                addStr = false;
                storedTasks.get(index - 1).markAsUndone();
            }
        }
    }


    public static void main(String[] args) {
        String greet = "Hi! I'm Uke\n" + "What can I do for you?\n";
        System.out.println(greet);

        Scanner input = new Scanner(System.in);

        while (allowNext) {
            String str = input.nextLine();
            handleExit(str);
            handleList(str);
            handleMarking(str);

            if (allowNext && addStr) {
                Task t = new Task(str);
                storedTasks.add(t);
                System.out.println("Task added: " + str);
            } else if (!addStr) {
                addStr = true;
            }
        }
    }
}
