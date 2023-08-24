import java.util.ArrayList;
import java.util.HashMap;
import java.util.InputMismatchException;
import java.util.Scanner;

public class Duke {
    private static final String name = "Bot";
    private static final String greeting = "Hello I'm " + name + "\nWhat can I do for you?";
    private static final String end = "Bye bye";
    private static final String mark = "mark";
    private static final String unmark = "unmark";
    private ArrayList<Task> todolist;

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
        StringBuffer str = new StringBuffer(s);
        if (s.equals("bye")) {
            exit();
            return false;
        } else if (s.equals("list")) {
            printlist();
            return true;
        } else if (str.substring(0, 4).toString().equals(mark)) {
            mark(Integer.parseInt(str.substring(5, str.length())));
            return true;
        } else if (s.substring(0, 6).toString().equals(unmark)) {
            unmark(Integer.parseInt(str.substring(7, str.length())));
            return true;
        } else {
            addtolist(s);
            return true;
        }
    }

    private void mark(int i) {
        todolist.get(i - 1).mark();
        System.out.println("Nice! I've marked this task as done:");
        System.out.println(todolist.get(i - 1).toString());
    }

    private void unmark(int i) {
        todolist.get(i - 1).unmark();
        System.out.println("OK, I've marked this task as not done yet:");
        System.out.println(todolist.get(i - 1).toString());
    }

    private void addtolist(String s) {
        StringBuffer str = new StringBuffer(s);
        System.out.println("Got it. I've added this task:");
        if (str.substring(0, 4).toString().equals("todo")) {
            Todo t = new Todo(str.substring(5, str.length()).toString());
            todolist.add(t);
            System.out.println(t.toString());
        } else if (str.substring(0, 8).toString().equals("deadline")) {
            String t = str.substring(9, str.length()).toString();
            String[] arr = t.split("/by ");
            Deadline d = new Deadline(arr[0], arr[1]);
            todolist.add(d);
            System.out.println(d.toString());
        } else if (str.substring(0, 5).toString().equals("event")) {
            String t = str.substring(6, str.length()).toString();
            String[] arr = t.split("/from ");
            String[] arrr = arr[1].split("/to ");
            Event e = new Event(arr[0], arrr[0], arrr[1]);
            todolist.add(e);
            System.out.println(e.toString());
        } else {
            throw new InputMismatchException("invalid input");
        }
        System.out.println("Now you have " + todolist.size() + " tasks in the list.");

    }

    private void printlist() {
        for (int i = 1; i <= this.todolist.size(); ++i) {
            Task t =  this.todolist.get(i - 1);
            System.out.println(i + ". " + t.toString());
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
