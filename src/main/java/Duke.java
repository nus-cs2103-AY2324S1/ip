import java.util.Scanner;

public class Duke {
    private static final String HORIZONTAL_LINE = "____________________________________________________________";
    private Scanner sc = new Scanner(System.in);
    public static void main(String[] args) {
        Duke duke = new Duke();
        duke.run();
    }
    public void run() {
        System.out.println(HORIZONTAL_LINE + "\n"
                + "Hello, I'm Je-O" + "\n"
                + "What can I do for you?" + "\n"
                + HORIZONTAL_LINE + "\n"
                + "Bye. Hope to see you again soon!" + "\n"
                + HORIZONTAL_LINE);
    }
}
