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

    public static void deleteTask(int i) throws DukeException {
        if (i >= 1 && i <= tasks.size()) {
            Task deletedTask = tasks.remove(i - 1);
            System.out.println("\t____________________________________________________________");
            System.out.println("\t Noted. I've removed this task:");
            System.out.println("\t   " + deletedTask.toString());
            System.out.println("\t Now you have " + tasks.size() + " tasks in the list.");
            System.out.println("\t____________________________________________________________");
        } else {
            throw new DukeException("Cannot delete a task that is out of range!");
        }
    }

    public static void printList() {
        System.out.println("\t____________________________________________________________");
        System.out.println("\t Here are the tasks in your list:");
        for (int i = 0; i < tasks.size(); i++) {
            System.out.println("\t " + (i + 1) + "." + tasks.get(i).toString());
        }
        System.out.println("\t____________________________________________________________");
    }

    public static void markAsDone(int i) throws DukeException {
        if (i >= 1 && i <= tasks.size()) {
            tasks.get(i - 1).markAsDone();
            System.out.println("\t____________________________________________________________");
            System.out.println("\t Nice! I've marked this task as done:");
            System.out.println("\t   " + tasks.get(i - 1).toString());
            System.out.println("\t____________________________________________________________");
        } else {
            throw new DukeException("Cannot mark a task that is out of range!");
        }
    }

    public static void unmarkAsDone(int i) throws DukeException {
        if (i >= 1 && i <= tasks.size()) {
            tasks.get(i - 1).unmarkAsDone();
            System.out.println("\t____________________________________________________________");
            System.out.println("\t OK, I've marked this task as not done yet:");
            System.out.println("\t   " + tasks.get(i - 1).toString());
            System.out.println("\t____________________________________________________________");
        } else {
            throw new DukeException("Cannot unmark a task that is out of range!");
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
            try {
                message = sc.nextLine();
                if (message.equals("bye")) break;
                if (message.equals("list")) {
                    printList();
                } else if (message.startsWith("mark")) {
                    if (message.length() <= 5)
                        throw new DukeException("You need to specify the index of the task to mark.");
                    try {
                        markAsDone(Integer.parseInt(message.substring(5)));
                    } catch (NumberFormatException e) {
                        throw new DukeException("The index of the task to mark is not a valid integer.");
                    }
                } else if (message.startsWith("unmark")) {
                    if (message.length() <= 7)
                        throw new DukeException("You need to specify the index of the task to unmark.");
                    try {
                        unmarkAsDone(Integer.parseInt(message.substring(7)));
                    } catch (NumberFormatException e) {
                        throw new DukeException("The index of the task to unmark is not a valid integer.");
                    }
                } else if (message.startsWith("delete")) {
                    if (message.length() <= 7)
                        throw new DukeException("You need to specify the index of the task to delete.");
                    try {
                        deleteTask(Integer.parseInt(message.substring(7)));
                    } catch (NumberFormatException e) {
                        throw new DukeException("The index of the task to delete is not a valid integer.");
                    }
                } else if (message.startsWith("todo")) {
                    if (message.length() <= 5)
                        throw new DukeException("The description of a todo cannot be empty.");
                    addTodo(message.substring(5));
                } else if (message.startsWith("deadline")) {
                    if (message.length() <= 9)
                        throw new DukeException("The description of a deadline cannot be empty.");
                    String[] deadline = message.substring(9).split(" /by ");
                    if (deadline.length != 2)
                        throw new DukeException("A deadline requires exactly 1 due date.");
                    addDeadline(deadline[0], deadline[1]);
                } else if (message.startsWith("event")) {
                    if (message.length() <= 6)
                        throw new DukeException("The description of a deadline cannot be empty.");
                    String[] event = message.substring(6).split(" /to | /from ");
                    if (event.length != 3)
                        throw new DukeException("An event requires exactly 2 from/to dates.");
                    addEvent(event[0], event[1], event[2]);
                } else {
                    throw new DukeException("I'm sorry, but I don't know what that means :-(");
                }
            } catch (DukeException e) {
                System.out.println(e);
            }
        }

        System.out.println(bye_message);
    }
}
