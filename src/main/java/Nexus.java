import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.Scanner;

public class Nexus {
    public static void main(String[] args) {
        System.out.println("Hello! I'm NEXUS");
        System.out.println("What can I do for you?");
        Scanner scanner = new Scanner(System.in);
        List<Task> list = new ArrayList<>();
        int index;
        boolean isBye = false;

        while (!isBye) {
            String input = scanner.next();
            switch (input) {
                case "bye":
                    System.out.println("Bye. Hope to see you again soon!");
                    isBye = true;
                    break;
                case "list":
                    for (int i = 0; i < list.size(); i++) {
                        System.out.print(i + 1);
                        System.out.println("." + list.get(i));
                    }
                    break;
                case "mark":
                    index = Integer.parseInt(scanner.next()) - 1;
                    list.get(index).setDone();
                    System.out.println("Nice! I've marked this task as done:");
                    System.out.println(list.get(index));
                    break;
                case "unmark":
                    index = Integer.parseInt(scanner.next()) - 1;
                    list.get(index).setUndone();
                    System.out.println("OK, I've marked this task as not done yet:");
                    System.out.println(list.get(index));
                    break;
                default:
                    list.add(new Task(input));
                    System.out.println("added: " + input);
            }
        }
    }
}
