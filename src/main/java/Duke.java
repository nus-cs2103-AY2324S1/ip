import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Duke {
    public static void main(String[] args) {
        List<String> lst = new ArrayList<>();
        System.out.println(greet());
        while (true) {
            Scanner sc = new Scanner(System.in);
            String input = sc.nextLine();
            if (input.equalsIgnoreCase("list")) {
                //print out list
                for (int i = 0; i < lst.size(); i++) {
                    System.out.println(String.valueOf(i + 1) + ". " + lst.get(i));
                }
            } else if (input.equalsIgnoreCase("bye")) {
                sc.close();
                break;
            } else {
                //add the item into the list
                lst.add(input);
                System.out.println("added: " + input);
            }
        }
        System.out.println(Duke.byeGreetings);
    }

    public static String byeGreetings = "Bye. Hope to see you again soon!";
    public static String name = " ____    __        __       \n"
            + "|  _ \\   | |  ____ | | __\n"
            + "| | | |  | | |     | |/ /\n"
            + "| |_| |  | | |     |   < \n"
            + "|____/   ___  ____ |_|\\_\\\n";
    public static String greet() {
        return "Hello from \n " + Duke.name + "What can I do for you? ";
    }
}
