import java.util.Objects;
import java.util.Scanner;
public class Duke {
    public static void main(String[] args) {
        System.out.println("Hello! I'm lippy the wombat\n" + "What can I do for you?\n");
        Scanner scanner = new Scanner(System.in);
        while (true) {
            String input = scanner.nextLine();
            if (Objects.equals(input, "bye")) {
                break;
            } else {
                System.out.println(input);
            }
        }
        System.out.println("Bye. Hope to see you again soon!");
    }
}
