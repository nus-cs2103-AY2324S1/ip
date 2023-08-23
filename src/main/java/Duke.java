import java.util.Locale;
import java.util.Scanner;

public class Duke {
    public static void main(String[] args) {

        Scanner scanner = new Scanner(System.in);
        String Start = "Hello! I'm Red \nWhat can I do for you?";
        System.out.println(Start);

        boolean run = true;
        while (run) {
            String input = scanner.nextLine();
            System.out.println(input);
            run = !input.equals("bye");
        }

        String End = "Bye. Hope to see you again soon!\n";
        System.out.println(End);
    }
}
