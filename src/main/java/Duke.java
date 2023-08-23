import java.util.ArrayList;
import java.util.Scanner;

class Task {
    protected String message;
    protected boolean isDone;

    Task(String message) {
        this.message = message;
        this.isDone = false;
    }

    public String getStatusIcon() {
        return isDone ? "[X]" : "[ ]";
    }

    public void markAsDone() {
        this.isDone = true;
    }

    public void unmarkAsDone() {
        this.isDone = false;
    }

    public String toString() {
        return this.getStatusIcon() + " " + this.message;
    }
}

public class Duke {

    public static ArrayList<Task> tasks = new ArrayList<>();

    public static void addToList(String message) {
        String addMessage = "\t____________________________________________________________\n"
                + "\t Added: " + message + "\n"
                + "\t____________________________________________________________\n";
        tasks.add(new Task(message));
        System.out.println(addMessage);
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
            } else {
                addToList(message);
            }
        }

        System.out.println(bye_message);
    }
}
