import java.util.ArrayList;
import java.util.Scanner;

public class Duke {
    private static final String name = "Bot";
    private static final String greeting = "Hello I'm " + name + "\nWhat can I do for you?";
    private static final String end = "Bye bye";

    private ArrayList<String> todolist;

    private Duke() {
        this.todolist = new ArrayList<>();
        greet();
    }

    private static void greet() {
        System.out.println(greeting);
    }
    private static void echo(String s) {
        System.out.println(s);
    }

    private static void exit() {
        System.out.println(end);
    }

    private static boolean respond(String s) {
        if (s.equals("bye")) {
            exit();
            return false;
        } else {
            echo(s);
            return true;
        }
    }
    public static void main(String[] args) {
        Scanner s = new Scanner(System.in);
        Duke d = new Duke();
        while (s.hasNextLine()) {
            String t = s.nextLine();
            if (!respond(t)) {
                break;
            }
        }
    }
}
