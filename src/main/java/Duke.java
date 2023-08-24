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

    private static void exit() {
        System.out.println(end);
    }

    private boolean respond(String s) {
        if (s.equals("bye")) {
            exit();
            return false;
        } else if (s.equals("list")) {
            printlist();
            return true;
        } else {
            addtolist(s);
            return true;
        }
    }

    private void addtolist(String s) {
        this.todolist.add(s);
        System.out.println("added: " + s);
    }

    private void printlist() {
        for (int i = 1; i <= this.todolist.size(); ++i) {
            System.out.println(i + ". " + this.todolist.get(i - 1));
        }
    }

    public static void main(String[] args) {
        Scanner s = new Scanner(System.in);
        Duke d = new Duke();
        while (s.hasNextLine()) {
            String t = s.nextLine();
            if (!d.respond(t)) {
                break;
            }
        }
    }
}
