import java.util.ArrayList;
import java.util.HashMap;
import java.util.Scanner;

public class Duke {
    private static final String name = "Bot";
    private static final String greeting = "Hello I'm " + name + "\nWhat can I do for you?";
    private static final String end = "Bye bye";
    private static final String mark = "mark";
    private static final String unmark = "unmark";
    private ArrayList<String> todolist;

    private HashMap<String, Boolean> map;

    private Duke() {
        this.todolist = new ArrayList<>();
        this.map = new HashMap<>();
        greet();
    }

    private static void greet() {
        System.out.println(greeting);
    }

    private static void exit() {
        System.out.println(end);
    }

    private boolean respond(String s) {
        StringBuffer str = new StringBuffer(s);
        if (s.equals("bye")) {
            exit();
            return false;
        } else if (s.equals("list")) {
            printlist();
            return true;
        } else if (s.length() < 4) {
            addtolist(s);
            return true;
        } else if (str.substring(0, 4).toString().equals(mark)) {
            System.out.println(str.substring(5, str.length() - 1));
            mark(todolist.get(Integer.parseInt(str.substring(5, str.length())) - 1));
            return true;
        } else if (s.length() < 6) {
            addtolist(s);
            return true;
        } else if (s.substring(0, 6).toString().equals(unmark)) {
            unmark(todolist.get(Integer.parseInt(str.substring(7, str.length())) - 1));
            return true;
        } else {
            addtolist(s);
            return true;
        }
    }

    private void mark(String s) {
        map.put(s, true);
        System.out.println("Nice! I've marked this task as done:");
        System.out.println("   [X] " + s);
    }

    private void unmark(String s) {
        map.put(s, false);
        System.out.println("OK, I've marked this task as not done yet:");
        System.out.println("   [ ] " + s);
    }

    private void addtolist(String s) {
        this.todolist.add(s);
        map.put(s, false);
        System.out.println("added: " + s);
    }

    private void printlist() {
        for (int i = 1; i <= this.todolist.size(); ++i) {
            String s =  this.todolist.get(i - 1);
            boolean isdone = map.get(s);
            char c;
            if (isdone) {
                c = 'X';
            } else {
                c = ' ';
            }
            System.out.println(i + ". [" + c + ']' + ' ' + s);
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
