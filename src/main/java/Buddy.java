import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Buddy {
    private static String name = "Buddy";

    public static void main(String[] args) {
        String greeting = String.format("Hello! I'm %s\n", name);
        String inquiry = "What can I do for you?\n";
        String exit = "Bye. Hope to see you again soon!\n";

        Scanner scanner = new Scanner(System.in);
        List<String> list = new ArrayList();
        String command;

        System.out.println(greeting + inquiry);

        while (true) {
            command = scanner.nextLine();
            if (command.equals("bye")) {
                System.out.println(exit);
                break;
            }
            if (command.equals("list")) {
                printText(list);
            } else {
                list.add(command);
                System.out.println("added: " + command);
            }
        }
    }
    private static void printText(List<String> list) {
        for (int i = 0; i < list.size(); i++) {
            System.out.println((i + 1) + ". " + list.get(i));
        }
    }
}
