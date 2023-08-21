import java.util.Scanner;

public class Duke {
    static final String HORIZONTAL_LINE = "____________________________________________________________\n";
    public static void main(String[] args) {
        String greeting = HORIZONTAL_LINE + " Hello! I'm Pixel\n What can I do for you?\n" + HORIZONTAL_LINE;
        System.out.println(greeting);

        Scanner scanner = new Scanner(System.in);
        while (scanner.hasNext()) {
            String input = scanner.nextLine();
            if (input.equals("bye")) {
                System.out.println(HORIZONTAL_LINE + " Bye. Hope to see you again soon!\n" + HORIZONTAL_LINE);
                break;
            } else {
                System.out.println(HORIZONTAL_LINE + input + "\n" + HORIZONTAL_LINE);
            }
        }
    }
}
