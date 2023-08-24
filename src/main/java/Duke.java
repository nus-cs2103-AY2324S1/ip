import java.util.Scanner;

public class Duke {
    private static final String name = "Bot";
    private static final String greeting = "Hello I'm " + name + "\nWhat can I do for you?";
    private static final String end = "Bye bye";

    public static void main(String[] args) {
        System.out.println(greeting);
        Scanner s = new Scanner(System.in);
        while (s.hasNextLine()) {
            String t = s.nextLine();
            if (t.equals("bye")) {
                exit();
                return;
            } else {
                echo(t);
            }
        }
    }

    private static void echo(String s) {
        System.out.println(s);
    }

    private static void exit() {
        System.out.println(end);
    }
}
