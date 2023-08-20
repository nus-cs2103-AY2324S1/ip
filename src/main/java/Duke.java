import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Duke {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        String line = "____________________________________________________________";
        String logo = " ___  __    __   __  ____   ____    ___\n"
                    + "|   | | |   \\ \\ / / |  __| |  __|  |   |\n"
                    + "|   | | |    \\   /   \\ \\    \\ \\    |   |\n"
                    + "|___| | |     | |     \\ \\    \\ \\   |___|\n"
                    + "|   | | |___  | |     _\\ \\   _\\ \\  |   |\n"
                    + "|   | |_____| |_|    |____| |____| |   |\n";
        System.out.println(logo);
        System.out.println(line);
        System.out.println("Hello! I'm Alyssa!");
        System.out.println("What can I do for you?");
        System.out.println(line);
        List<String> items = new ArrayList<>();
        while (true) {
            String nextInput = sc.nextLine();
            if (nextInput.equals("bye")) {
                System.out.println(line);
                System.out.println("Bye. Hope to see you again soon!");
                System.out.println(line);
                return;
            } else if (nextInput.equals("list")) {
                int counter = 1;
                System.out.println(line);
                for (String listItem : items) {
                    System.out.println(counter + ". " + listItem);
                    counter++;
                }
                System.out.println(line);
            } else {
                items.add(nextInput);
                System.out.println(line);
                System.out.println("added: " + nextInput);
                System.out.println(line);
            }
        }
    }
}
