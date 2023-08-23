import java.util.Scanner;

public class Duke {
    public static void main(String[] args) {
        String HORIZONTAL_LINE = "____________________________________________________________ \n";

        String entryMessage = HORIZONTAL_LINE
                + "Hello! I'm Chad \n"
                + "What can I do for you? \n"
                + HORIZONTAL_LINE;

        String exitMessage = HORIZONTAL_LINE
                + "Bye. Hope to see you again soon! \n"
                + HORIZONTAL_LINE;

        Scanner scanner = new Scanner(System.in);
        String input = "";

        System.out.println(entryMessage);
        while(!input.equals("bye")) {
            input = scanner.nextLine();
            if(!input.equals("bye")) {
                System.out.println(HORIZONTAL_LINE + input + "\n" + HORIZONTAL_LINE);
            }
        }
        scanner.close();
        System.out.println(exitMessage);
    }
}
