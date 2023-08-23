import java.util.Objects;
import java.util.Scanner;

public class Nexus {
    public static void main(String[] args) {
        System.out.println("Hello! I'm NEXUS");
        System.out.println("What can I do for you?");
        Scanner scanner = new Scanner(System.in);
        while (true) {
            String input = scanner.nextLine();
            if (Objects.equals(input, "bye")) break;
            System.out.println(input);
        }
        System.out.println("Bye. Hope to see you again soon!");
    }
}
