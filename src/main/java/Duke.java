import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Duke {
    public static void main(String[] args) {
        System.out.println("\t-----------------------------------------------");
        System.out.println("\tSup bro! I'm Brobot");
        System.out.println("\tWhat can I do for you?");
        System.out.println("\t-----------------------------------------------");
        Scanner scanner = new Scanner(System.in);
        List<String> list = new ArrayList<>();

        while (true) {
            String input = scanner.nextLine();

            if (input.equals("bye")) {
                break;
            } else if (input.equals("list")) {
                System.out.println("\t-----------------------------------------------");
                for (int i = 0; i < list.size(); i++) {
                    System.out.println("\t" + (i + 1) + ". " + list.get(i));
                }
                System.out.println("\t-----------------------------------------------");

            } else {
                list.add(input);
                System.out.println("\t-----------------------------------------------");
                System.out.println("\tadded: " + input);
                System.out.println("\t-----------------------------------------------");
            }
        }

        System.out.println("\t-----------------------------------------------");
        System.out.println("\tBye. Hope to see you again soon bro!");
        System.out.println("\t-----------------------------------------------");
    }
}
