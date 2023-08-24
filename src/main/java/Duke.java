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

    public static void handleTaskAdd(String s) {
        if (s.startsWith("todo ")) {
            String todoName = s.substring(5);

            Todo todo = new Todo(todoName);
            storedTasks.add(todo);
            System.out.println("New task added: " + todo);

            int len = storedTasks.size();
            System.out.println("You have a total of " + len + " task(s) in your list.");
        } else if (s.startsWith("deadline ")) {
            int dlIndex = s.indexOf("/by ");

            if (dlIndex != -1 && dlIndex > 9) {
                String dlName = s.substring(9, dlIndex - 1);
                String dlTime = s.substring(dlIndex + 4);

                Deadline dl = new Deadline(dlName, dlTime);
                storedTasks.add(dl);
                System.out.println("New task with deadline added: " + dl);

                int len = storedTasks.size();
                System.out.println("You have a total of " + len + " task(s) in your list.");
            } else {
                System.out.println("Invalid task with deadline input!");
            }
        } else if (s.startsWith("event ")) {
            int fromIndex = s.indexOf("/from ");
            int toIndex = s.indexOf("/to ");

            if (fromIndex != -1 && fromIndex > 6 && toIndex != -1 && toIndex > fromIndex + 6) {
                String eName = s.substring(6, fromIndex - 1);
                String eFrom = s.substring(fromIndex + 6, toIndex - 1);
                String eTo = s.substring(toIndex + 4);

                Event e = new Event(eName, eFrom, eTo);
                storedTasks.add(e);
                System.out.println("New task added: " + e);

                int len = storedTasks.size();
                System.out.println("You have a total of " + len + " task(s) in your list.");
            } else {
                System.out.println("Invalid event input!");
            }
        } else {
            System.out.println("Invalid command!");
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
                handleTaskAdd(str);
            } else if (!addStr) {
                addStr = true;
            }
        }
    }
}
