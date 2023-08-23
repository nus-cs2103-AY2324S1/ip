import java.util.ArrayList;
import java.util.Scanner;

public class Duke {

    public static ArrayList<Task> tasks = new ArrayList<>();

    public static void addTodo(String message) {
        Task newTask = new Todo(message);
        tasks.add(newTask);
        System.out.println("\t____________________________________________________________");
        System.out.println("\t Got it. I've added this task:");
        System.out.println("\t   " + newTask.toString());
        System.out.println("\t Now you have " + tasks.size() + " tasks in the list.");
        System.out.println("\t____________________________________________________________");
    }

    public static void addDeadline(String message, String deadline) {
        Task newTask = new Deadline(message, deadline);
        tasks.add(newTask);
        System.out.println("\t____________________________________________________________");
        System.out.println("\t Got it. I've added this task:");
        System.out.println("\t   " + newTask.toString());
        System.out.println("\t Now you have " + tasks.size() + " tasks in the list.");
        System.out.println("\t____________________________________________________________");
    }

    public static void addEvent(String message, String from, String to) {
        Task newTask = new Event(message, from, to);
        tasks.add(newTask);
        System.out.println("\t____________________________________________________________");
        System.out.println("\t Got it. I've added this task:");
        System.out.println("\t   " + newTask.toString());
        System.out.println("\t Now you have " + tasks.size() + " tasks in the list.");
        System.out.println("\t____________________________________________________________");
    }

    public static void printList() {
        System.out.println("\t____________________________________________________________");
        System.out.println("\t Here are the tasks in your list:");
        for (int i = 0; i < tasks.size(); i++) {
            System.out.println("\t " + (i + 1) + "." + tasks.get(i).toString());
        }
        System.out.println("\t____________________________________________________________");
    }

    public static void markAsDone(int i) {
        if (i >= 1 && i <= tasks.size()) {
            tasks.get(i - 1).markAsDone();
            System.out.println("\t____________________________________________________________");
            System.out.println("\t Nice! I've marked this task as done:");
            System.out.println("\t   " + tasks.get(i - 1).toString());
            System.out.println("\t____________________________________________________________");
        }
    }

    public static void unmarkAsDone(int i) {
        if (i >= 1 && i <= tasks.size()) {
            tasks.get(i - 1).unmarkAsDone();
            System.out.println("\t____________________________________________________________");
            System.out.println("\t OK, I've marked this task as not done yet:");
            System.out.println("\t   " + tasks.get(i - 1).toString());
            System.out.println("\t____________________________________________________________");
        }
    }

    public static void main(String[] args) {
        String intro_message = "\t____________________________________________________________\n"
                + "\t Hello! I'm Bob the Chatbot!\n"
                + "\t What can I do for you?\n"
                + "\t____________________________________________________________\n";

        String bye_message = "\t____________________________________________________________\n"
                + "\t Bye. Hope to see you again soon!\n"
                + "\t____________________________________________________________";

        System.out.println(intro_message);
        Scanner sc = new Scanner(System.in);
        String message = "";

        while (true) {
            message = sc.nextLine();
            if (message.equals("bye")) break;
            if (message.equals("list")) {
                printList();
            } else if (message.startsWith("mark ")) {
                markAsDone(Integer.parseInt(message.substring(5)));
            } else if (message.startsWith("unmark ")) {
                unmarkAsDone(Integer.parseInt(message.substring(7)));
            } else if (message.startsWith("todo ")) {
                addTodo(message.substring(5));
            } else if (message.startsWith("deadline ")) {
                String[] deadline = message.substring(9).split(" /by ");
                addDeadline(deadline[0], deadline[1]);
            } else if (message.startsWith("event ")) {
                String[] event = message.substring(6).split(" /to | /from ");
                addEvent(event[0], event[1], event[2]);
            }
        }

        System.out.println(bye_message);
    }
}
