
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

    private void respond(String s) {
        if (s.equals("list")) {
            System.out.println("Here are the tasks in your list:");
            int count = 1;
            for(Task ss : this.l) {
                String res = String.format("%d. [%s] %s", count++, ss.getStatusIcon(), ss.toString());
                System.out.println(res);
            }
        } else if (s.equals("bye")) {
            end();
        } else if (s.substring(0,4).equals("mark")) {
            int idx = Integer.parseInt(s.substring(5));
            this.l.get(idx-1).mark();
        } else if (s.substring(0,6).equals("unmark")) {
            int idx = Integer.parseInt(s.substring(7));
            this.l.get(idx-1).unmark();
        }
        else {
            this.l.add(new Task(s));
            String res = String.format("added: %s", s.toString());
            System.out.println(res);
        }
    }

    public static void main(String[] args) {
        new Duke().start();
    }
}
