import java.util.ArrayList;
import java.util.Scanner;

public class Duke {

    public static void inputChecker(Task task) throws DukeException {
        if (task.toString().trim().isEmpty()) {
            throw new DukeException("☹ OOPS!!! The description of a " + task + " cannot be empty.");
        }
    }

    public static void cansplit(String input, String splitter) throws DukeException {
        if (input.split(splitter).length == 1) {
            throw new DukeException("☹ OOPS!!!");
        }
    }

    public static void run(String userinput, Scanner scan, ArrayList<Task> list) throws DukeException {
        if (userinput.equalsIgnoreCase("list")) {
            for (int i = 0; i < list.size(); i++) {
                System.out.println(i + 1 + "." + list.get(i));
            }

        } else if (userinput.length() >= 6 && userinput.substring(0, 5).equalsIgnoreCase("mark ")) {
            try {
                int taskno = Integer.parseInt(userinput.substring(5));
                if (taskno <= list.size()) {
                    Task task = list.get(taskno - 1);
                    task.setdone();
                    System.out.println("Nice! I've marked this task as done:");
                    System.out.println("  " + task);
                } else {
                    System.out.println("Enter a valid number to mark");
                }
            } catch (NumberFormatException e) {
                System.out.println("Enter a valid number to mark");
            }

        } else if (userinput.length() >= 4 && userinput.substring(0, 4).equalsIgnoreCase("todo")) {
            ToDo todo = new ToDo(userinput.substring(4));
            list.add(todo);
            System.out.println("Got it. I've added this task:");
            System.out.println("  " + todo);
            System.out.println("Now you have " + list.size() + " tasks in the list.");


        } else if (userinput.length() >= 8 && userinput.substring(0, 8).equalsIgnoreCase("deadline")) {
            String[] segments = userinput.split(" /by ");
            Deadline deadline = new Deadline(segments[0].substring(8), segments[1]);
            list.add(deadline);
            System.out.println("Got it. I've added this task:");
            System.out.println("  " + deadline);
            System.out.println("Now you have " + list.size() + " tasks in the list.");

        } else if (userinput.length() >= 7 && userinput.substring(0, 6).equalsIgnoreCase("event ")) {
            String[] segments1 = userinput.split(" /from ");
            String from = segments1[1].split(" /to ")[0];
            String[] segments2 = segments1[1].split(" /to ");
            Event event = new Event(segments1[0].substring(5), from, segments2[1]);
            list.add(event);
            System.out.println("Got it. I've added this task:");
            System.out.println("  " + event);
            System.out.println("Now you have " + list.size() + " tasks in the list.");
        } else if (userinput.length() >= 8 && userinput.substring(0, 7).equalsIgnoreCase("delete ")) {
            try {
                int taskno = Integer.parseInt(userinput.substring(7));
                if (taskno <= list.size()) {
                    Task task = list.get(taskno - 1);
                    list.remove(taskno - 1);
                    System.out.println("Noted. I've removed this task:");
                    System.out.println("  " + task);
                    System.out.println("Now you have " + list.size() + " tasks in the list.");
                } else {
                    System.out.println("Enter a valid number to mark");
                }
            } catch (NumberFormatException e) {
                System.out.println("Enter a valid number to mark");
            }
        } else {
            throw new DukeException("☹ OOPS!!! I'm sorry, but I don't know what that means :-(");
        }
    }
    public static void main(String[] args) {
        System.out.println("Hello! I'm IPSVIJAYKUMARAAKOODAIRRUKALAM");
        System.out.println("What can I do for you?");
        String userinput;
        ArrayList<Task> list = new ArrayList<>();
        Scanner scan = new Scanner( System.in );

        userinput = scan.nextLine();
        while (!userinput.equalsIgnoreCase("bye")) {
            try {
                run(userinput, scan, list);
            } catch (DukeException e) {
                System.out.println(e);
            } finally {
                userinput = scan.nextLine();
            }
        }
        System.out.println("Bye. Hope to see you again soon!");
    }
}
