import java.util.Scanner;

public class Duke {
    private static final String HORIZONTAL_LINE = "____________________________________________________________";
    private Scanner sc = new Scanner(System.in);
    public static void main(String[] args) {
        Duke duke = new Duke();
        duke.run();
    }

    public void printWithLines(String s){
        System.out.println(HORIZONTAL_LINE + "\n" + s + "\n" + HORIZONTAL_LINE);
    }

    public void run() {
        printWithLines("Hello, I'm Je-O" + "\n"
                + "What can I do for you?");
        while (sc.hasNextLine()) {
            String input = sc.nextLine();
            if (input.equals("bye")) {
                printWithLines("Bye. Hope to see you again soon!");
                break;
            } else {
                printWithLines(input);
            }
        }
    }
}
