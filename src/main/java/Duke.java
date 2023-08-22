import java.util.Scanner;

public class Duke {

    private Scanner sc = new Scanner(System.in);
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
            } else {
                printLine();
                printEcho(input);
                printLine();
            }
        }
    }
    public void printEcho(String input) {
        System.out.println(input);
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
