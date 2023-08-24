import java.util.Objects;
import java.util.Scanner;
import java.util.ArrayList;
import java.util.List;
public class Duke {
    public static void main(String[] args) {
        System.out.println("Hello! I'm lippy the wombat\n" + "What can I do for you?\n");
        Scanner scanner = new Scanner(System.in);
        List<String> tasks = new ArrayList<>();
        while (true) {
            String input = scanner.nextLine();
            if (Objects.equals(input, "bye")) {
                break;
            }
            if (Objects.equals(input, "list")) {
                for (int i = 0; i < tasks.size(); i ++) {
                    int num = i + 1;
                    System.out.println(num + ". " + tasks.get(i));
                }
            } else {
                tasks.add(input);
                System.out.println("added: " + input);
            }
        }
        System.out.println("Bye. Hope to see you again soon!");
    }
}
