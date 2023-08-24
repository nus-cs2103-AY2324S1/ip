
import java.util.ArrayList;
import java.util.Scanner;
public class Duke {

    private ArrayList<Task> l;

    private Duke() {
        this.l = new ArrayList<>();
    }

    private static final String greet = "Hello! I'm ";
    private static final String name = "siri";
    private static final String msg = "What can I do for you?";

    private static final String bye = "Bye. Hope to see you again soon!";

    private void hello() {
        System.out.println(greet + name);
        System.out.println(msg);
    }

    private void start() {
        hello();
        Scanner sc = new Scanner(System.in);
        while(sc.hasNextLine()) {
            String s = sc.nextLine();
            respond(s);
        }
    }

    private void end() {
        System.out.println(bye);
    }

    private void added(Task t) {
        System.out.println("Got it. I've added this task:");
        System.out.println(t.toString());
        System.out.println("Now I have " + l.size() + " tasks in the list");
    }

    private void del(Task t) {
        System.out.println("Noted. I've removed this task:");
        System.out.println(t.toString());
        System.out.println("Now you have " + l.size() +  " tasks in the list");
    }

    private void respond(String s) {
        if (s.equals("list")) {
            System.out.println("Here are the tasks in your list:");
            int count = 1;
            for(Task ss : this.l) {
                String res = String.format("%d.%s", count++, ss.toString());
                System.out.println(res);
            }
        } else if (s.equals("bye")) {
            end();
        } else if (s.startsWith("mark")) {
            int idx = Integer.parseInt(s.substring(5));
            this.l.get(idx-1).mark();
        } else if (s.startsWith("unmark")) {
            int idx = Integer.parseInt(s.substring(7));
            this.l.get(idx-1).unmark();
        } else if (s.startsWith("deadline")) {
            if (s.trim().length() <=8) {
                throw new DukeException("OOPS!!! The description of a deadline cannot be empty.");
            }
            String t = s.substring(9);
            String[] temp = t.split("/by");
            if (temp.length < 2) {
                throw new DukeException("OOPS!!! The description of a deadline must have a deadline.");
            }
            Deadline d = new Deadline(temp[0], temp[1].trim());
            this.l.add(d);
            added(d);
        } else if (s.startsWith("todo")) {
            if (s.trim().length() <=4) {
                throw new DukeException("OOPS!!! The description of a todo cannot be empty.");
            }
            String t = s.substring(5);

            Todo temp = new Todo(t);
            this.l.add(temp);
            added(temp);
        } else if (s.startsWith("event")) {
            if (s.trim().length() <=5) {
                throw new DukeException("OOPS!!! The description of a event cannot be empty.");
            }
            String t = s.substring(6);
            String[] temp = t.split("/");
            if (temp.length < 3) {
                throw new DukeException("OOPS!!! The description of a event must have from and to.");
            }
            Events e = new Events(temp[0], temp[1].substring(4).trim(), temp[2].substring(2).trim());
            this.l.add(e);
            added(e);
        } else if (s.startsWith("delete")) {
            int idx = Integer.parseInt(s.substring(7));
            Task t = this.l.get(idx-1);
            this.l.remove(idx-1);
            del(t);

        }
        else {
            throw new DukeException("OOPS!!! I'm sorry, but I don't know what that means :-(");
        }
    }

    public static void main(String[] args) {
        new Duke().start();
    }
}
