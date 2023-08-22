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
                printLine();
                printBye();
                printLine();
            } else if (input.equals("list")) {
                printLine();
                printList();
                printLine();
            } else if (input.startsWith("mark ")) {
                int index = Integer.parseInt(input.substring(5));
                list.get(index - 1).markAsDone();
                printLine();
                printDone(index);
                printLine();
            } else if (input.startsWith("unmark ")) {
                int index = Integer.parseInt(input.substring(7));
                list.get(index - 1).markAsUndone();
                printLine();
                printUndone(index);
                printLine();
            } else {
                list.add(new Task(input));
                printLine();
                printAdded(input);
                printLine();
            }
        }
    }
    public void printEcho(String input) {
        System.out.println(input);
    }

    public void printDone(int index) {
        System.out.println("Nice! I've marked this task as done:");
        System.out.println(list.get(index - 1));
    }

    public void printUndone(int index) {
        System.out.println("OK, I've marked this task as not done yet:");
        System.out.println(list.get(index - 1));
    }

    public void printAdded(String input) {
        System.out.println("added: " + input);
    }

    public void printList() {
        System.out.println("Here are the tasks in your list:");
        for (int i = 0; i < list.size(); i++) {
            System.out.println(i + 1 + ". " + list.get(i));
        }
    }
    public void printGreeting() {
        System.out.println("Hello! I'm Max\n" + "What can I do for you?");
    }

    public void printBye() {
        System.out.println("Bye. Hope to see you again soon!");
    }

    public void printLine() {
        System.out.println("____________________________________________________________");
    }
}

