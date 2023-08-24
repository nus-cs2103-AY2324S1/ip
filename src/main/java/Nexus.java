import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Nexus {
    public static void main(String[] args) {
        System.out.println("Hello! I'm NEXUS");
        System.out.println("What can I do for you?");
        Scanner scanner = new Scanner(System.in);
        List<Task> list = new ArrayList<>();
        int index;
        String desc;
        boolean isBye = false;

        while (!isBye) {
            String input = scanner.next();
            switch (input) {
                case "bye":
                    System.out.println("Bye. Hope to see you again soon!");
                    isBye = true;
                    break;
                case "list":
                    System.out.println("Here are the tasks in your list:");
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
                case "todo":
                    desc = scanner.nextLine();
                    list.add(new Todo(desc));
                    System.out.println("Got it. I've added this task:");
                    System.out.println(list.get(list.size() - 1));
                    System.out.println("Now you have " + list.size() + " tasks in the list.");
                    break;
                case "deadline":
                    scanner.useDelimiter("/");
                    desc = scanner.next();
                    String by = scanner.nextLine().replace("/by", "").trim();
                    list.add(new Deadline(desc, by));
                    System.out.println("Got it. I've added this task:");
                    System.out.println(list.get(list.size() - 1));
                    System.out.println("Now you have " + list.size() + " tasks in the list.");
                    break;
                case "event":
                    scanner.useDelimiter("/");
                    desc = scanner.next();
                    String start = scanner.next().replace("from", "").trim();
                    String end = scanner.nextLine().replace("/to", "").trim();
                    list.add(new Event(desc, start, end));
                    System.out.println("Got it. I've added this task:");
                    System.out.println(list.get(list.size() - 1));
                    System.out.println("Now you have " + list.size() + " tasks in the list.");
                    break;
                default:
                    System.out.println("Invalid input");
            }
            scanner.reset();
        }
    }
}
