import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.Scanner;

public class Nexus {
    public static void main(String[] args) {
        System.out.println("Hello! I'm NEXUS");
        System.out.println("What can I do for you?");
        Scanner scanner = new Scanner(System.in);
        List<String> list = new ArrayList<>();
        while (true) {
            String input = scanner.nextLine();
            if (Objects.equals(input, "bye")) break;
            if (Objects.equals(input, "list")) {
                for (int i = 0; i < list.size(); i++) {
                    System.out.print(i + 1);
                    System.out.println(". " + list.get(i));
                }
            } else {
                list.add(input);
                System.out.println("added: " + input);
            }
        }
        System.out.println("Bye. Hope to see you again soon!");
    }
}
