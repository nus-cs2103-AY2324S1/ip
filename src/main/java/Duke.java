
import java.util.ArrayList;
import java.util.Scanner;
public class Duke {

    private ArrayList<String> l;

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
            int count = 1;
            for(String ss : this.l) {
                String res = String.format("%d. %s", count++, ss);
                System.out.println(res);
            }
        } else if (s.equals("bye")) {
            end();
        } else {
            this.l.add(s);
            String res = String.format("added: %s", s);
            System.out.println(res);
        }
    }

    public static void main(String[] args) {
        new Duke().start();
    }
}
