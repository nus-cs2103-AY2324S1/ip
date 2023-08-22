import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;


public class Duke {

    private Scanner sc = new Scanner(System.in);
    private List<Task> list = new ArrayList<Task>(100);
    public static void main(String[] args) {
        Duke duke = new Duke();
        duke.run();
    }

    public void run() {
        boolean isExit = false;
        printLine();
        printGreeting();
        printLine();
        while(!isExit) {
            String input = sc.nextLine();
            if (input.equals("bye")) {
                isExit = true;
                printBye();
            } else if (input.equals("list")) {
                printList();
            } else if (input.startsWith("mark ")) {
                int index = Integer.parseInt(input.substring(5));
                list.get(index - 1).markAsDone();
                printDone(index);
            } else if (input.startsWith("unmark ")) {
                int index = Integer.parseInt(input.substring(7));
                list.get(index - 1).markAsUndone();
                printUndone(index);
            } else {
                addTask(input);
            }
        }
    }

    public void printDone(int index) {
        printLine();
        System.out.println("Nice! I've marked this task as done:");
        System.out.println(list.get(index - 1));
        printLine();
    }

    public void printUndone(int index) {
        printLine();
        System.out.println("OK, I've marked this task as not done yet:");
        System.out.println(list.get(index - 1));
        printLine();
    }

    public void printAdded(Task task) {
        System.out.println("Got it. I've added this task:");
        System.out.println(task);
        System.out.println("Now you have " + list.size() + " tasks in the list.");
    }

    public void addTask(String input) {
        printLine();
        if (input.startsWith("todo ")) {
            Task task = new Todo (input.substring(5));
            list.add(task);
            printAdded(task);
        } else if (input.startsWith("deadline ")) {
            String[] arr = input.substring(9).split(" /by ");
            Task task = new Deadline(arr[0], arr[1]);
            list.add(task);
            printAdded(task);
        } else if (input.startsWith("event ")) {
            String[] arr = input.split("\\s*/from\\s*|\\s*/to\\s*");
            Task task = new Event(arr[0], arr[1], arr[2]);
            list.add(task);
            printAdded(task);
        }
        printLine();
    }

    public void printList() {
        printLine();
        System.out.println("Here are the tasks in your list:");
        for (int i = 0; i < list.size(); i++) {
            System.out.println(i + 1 + ". " + list.get(i));
        }
        printLine();
    }
    public void printGreeting() {
        System.out.println("Hello! I'm Max\n" + "What can I do for you?");
    }

    public void printBye() {
        printLine();
        System.out.println("Bye. Hope to see you again soon!");
        printLine();
    }

    public void printLine() {
        System.out.println("____________________________________________________________");
    }
}

