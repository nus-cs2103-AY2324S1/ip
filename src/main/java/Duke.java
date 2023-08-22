import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Duke {

    private Scanner sc = new Scanner(System.in);
    private List<String> list = new ArrayList<String>(100);
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
            } else {
                list.add(input);
                printLine();
                printAdded(input);
                printLine();
            }
        }
    }
    public void printEcho(String input) {
        System.out.println(input);
    }

    public void printAdded(String input) {
        System.out.println("added: " + input);
    }

    public void printList() {
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
