import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Duke {
    static final String HORIZONTAL_LINE = "____________________________________________________________\n";
    static List<String> tasks = new ArrayList<>();

    public static void main(String[] args) {
        String greeting = HORIZONTAL_LINE + " Hello! I'm Pixel\n What can I do for you?\n" + HORIZONTAL_LINE;
        System.out.println(greeting);

        Scanner scanner = new Scanner(System.in);
        while (scanner.hasNext()) {
            String input = scanner.nextLine();
            if (input.equals("bye")) {
                System.out.println(HORIZONTAL_LINE + " Bye. Hope to see you again soon!\n" + HORIZONTAL_LINE);
            } else if (input.equals("list")) {
                System.out.println(HORIZONTAL_LINE);
                for (int i = 0; i < tasks.size(); i++) {
                    System.out.println((i + 1) + ". " + tasks.get(i));
                }
                System.out.println(HORIZONTAL_LINE);
            } else {
                tasks.add(input);
                System.out.println(HORIZONTAL_LINE + "added: " + input + "\n" + HORIZONTAL_LINE);
            }
        }
    }
}
