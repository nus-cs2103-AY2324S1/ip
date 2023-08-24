
import java.util.Scanner;
public class Duke {

    private static final String greet = "Hello! I'm ";
    private static final String name = "siri";
    private static final String msg = "What can I do for you?";

    private static final String bye = "Bye. Hope to see you again soon!";

    private static void respond(String s) {
        if (s.equals("bye")) {
            System.out.println(bye);
        } else {
            System.out.println(s);
        }
        
    }

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        String s = sc.next();
        System.out.println(greet +  name);
        System.out.println(msg);

        respond(s);

    }
}
