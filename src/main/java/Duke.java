import java.util.Scanner;

public class Duke {

    public static void main(String[] args) {
        System.out.println(greet());
        while (true) {
            Scanner sc = new Scanner(System.in);
            String input = sc.nextLine();
            if (input.equalsIgnoreCase("bye")) {
                sc.close();
                break;
            } else {
                System.out.println(input);
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
