import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class DaDaYuan {

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        List<String> tasks = new ArrayList<>();
        String line = "____________________________________________________________";

        System.out.println(line);
        System.out.println("Hello! I'm DaDaYuan");
        System.out.println("What can I do for you?");
        System.out.println(line);

        while (true) {
            String input = scanner.nextLine().trim();

            System.out.println(line);

            if (input.equals("bye")) {
                System.out.println("Bye. Hope to see you again soon!");
                System.out.println(line);
                break;
            } else if (input.equals("list")) {
                for (int i = 0; i < tasks.size(); i++) {
                    System.out.println((i + 1) + ". " + tasks.get(i));
                }
            } else {
                tasks.add(input);
                System.out.println("added: " + input);
            }

            System.out.println(line);
        }

        scanner.close();
    }
}


